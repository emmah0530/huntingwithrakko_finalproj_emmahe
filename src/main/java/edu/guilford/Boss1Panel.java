package edu.guilford;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

//import java.util.Timer;
//import java.util.TimerTask;
import javax.swing.Timer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Boss1Panel extends JPanel {
    private int boss1Atk;
    private int boss1Health;
    private JButton attackButton;
    private int playerAtk;
    private JLabel playerAtkLabel;
    private JLabel playerHealthLabel;
    private JLabel boss1HealthLabel;
    private int fightOrder;
    private JLabel fightOrderLabel;
    private JButton returnHome; // temp button
    private HomePagePanel hpPanel;
    private HomePageFrame hpFrame;
    private Boss1Frame boss1Frame;
    private PlayerStats playerStats;

    private JLabel fightRakko1Label;
    private ImageIcon fightRakko1;
    private JLabel fightRakko2Label;
    private ImageIcon fightRakko2;
    private JLabel boss1Label;
    private ImageIcon boss1;

    private boolean boss1Status;
    private boolean boss1Lost;

    private Timer bossTimer;

    private Font chiruFont;

    public HomePageFrame getHpFrame() {
        return hpFrame;
    }

    public void setHpFrame(HomePageFrame hpFrame) {
        this.hpFrame = hpFrame;
    }

    public Boss1Frame getBoss1Frame() {
        return boss1Frame;
    }

    public void setBoss1Frame(Boss1Frame fpFrame) {
        this.boss1Frame = fpFrame;
    }

    public HomePagePanel getHpPanel() {
        return hpPanel;
    }

    public int getPlayerAtk() {
        return playerAtk;
    }

    public void setPlayerAtk(int playerAtk) {
        this.playerAtk = playerAtk;
    }

    public void setHpPanel(HomePagePanel hpPanel) {
        this.hpPanel = hpPanel;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    public boolean isBoss1Status() {
        return boss1Status;
    }

    public void setBoss1Status(boolean boss1Status) {
        this.boss1Status = boss1Status;
    }

    public boolean isBoss1Lost() {
        return boss1Lost;
    }

    public void setBoss1Lost(boolean boss1Lost) {
        this.boss1Lost = boss1Lost;
    }

    public Boss1Panel() {
        setPreferredSize(new Dimension(1000, 600));
        Color backgroundColor = Color.decode("#FCF9FB");
        setBackground(backgroundColor);
        setLayout(new BorderLayout());

        try {
            InputStream is = HomePagePanel.class.getResourceAsStream("/chirufont.ttf");
            Font loaded = Font.createFont(Font.TRUETYPE_FONT, is);
            // Register the font so it can be used by name elsewhere, and keep a derived
            // instance for convenience.
            java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loaded);
            // Keep a default-sized version available for UI labels
            this.chiruFont = loaded.deriveFont(Font.BOLD, 17f);
        } catch (FontFormatException | IOException e) {
            // If the font can't be read for any reason, print a message and continue using
            // system fonts.
            System.err.println("Warning: couldn't load chirufont.ttf - " + e.getMessage());
        }

        boss1Atk = 1;
        boss1Health = 10;
        boss1Status = false;

        // bossTimer = new Timer("Timer");
        int delay = 2000;
        TimerListener timerListener = new TimerListener();
        bossTimer = new Timer(delay, timerListener);

        attackButton = new JButton("Attack");
        attackButton.setFont(this.chiruFont);
        add(attackButton, BorderLayout.PAGE_END);
        AttackListener atkListener = new AttackListener();
        attackButton.addActionListener(atkListener);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        add(topPanel, BorderLayout.PAGE_START);

        fightOrderLabel = new JLabel();
        fightOrderLabel.setFont(this.chiruFont);
        topPanel.add(fightOrderLabel);

        returnHome = new JButton("Return Home");
        returnHome.setFont(this.chiruFont);
        topPanel.add(returnHome);
        HomeListener homeListener = new HomeListener();
        returnHome.addActionListener(homeListener);

        JPanel playerStatsPanel = new JPanel();
        playerStatsPanel.setBackground(Color.white);
        add(playerStatsPanel, BorderLayout.LINE_END);
        playerStatsPanel.setLayout(new BoxLayout(playerStatsPanel, BoxLayout.PAGE_AXIS));
        playerStatsPanel.add(Box.createRigidArea(new Dimension(100, 100)));
        playerAtkLabel = new JLabel("Your Attack: " + 0);
        playerAtkLabel.setFont(this.chiruFont);
        playerStatsPanel.add(playerAtkLabel);

        playerStatsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        playerHealthLabel = new JLabel("Your Health: " + 0);
        playerHealthLabel.setFont(this.chiruFont);
        playerStatsPanel.add(playerHealthLabel);

        JPanel bossStatsPanel = new JPanel();
        bossStatsPanel.setBackground(Color.white);
        add(bossStatsPanel, BorderLayout.LINE_START);
        boss1HealthLabel = new JLabel("Boss Health: " + boss1Health);
        boss1HealthLabel.setFont(this.chiruFont);
        bossStatsPanel.add(boss1HealthLabel);

        JPanel characterPanel = new JPanel();
        characterPanel.setBackground(Color.white);
        add(characterPanel, BorderLayout.CENTER);

        boss1Label = new JLabel("");
        characterPanel.add(boss1Label);
        boss1 = new ImageIcon(getClass().getResource("/boss1.png"));
        boss1Label.setIcon(boss1);

        fightRakko1Label = new JLabel("");
        characterPanel.add(fightRakko1Label);
        fightRakko1 = new ImageIcon(getClass().getResource("/rakko1.png"));
        fightRakko1Label.setIcon(fightRakko1);
        fightRakko2Label = new JLabel("");
        characterPanel.add(fightRakko2Label);
        fightRakko2 = new ImageIcon(getClass().getResource("/rakko2.png"));
        fightRakko2Label.setIcon(fightRakko2);

    }

    public void checkPlayerHealth() {
        if (playerStats.getHealth() <= 0) {
            returnHome();
            boss1Lost = true;
        }
    }

    public void refreshDisplay() {
        boss1Lost = false;
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        Random rand = new Random();
        fightOrder = rand.nextInt(1, 101);
        checkPlayerHealth();
        fightOrderDecision();

    }

    public void fightOrderDecision() {
        if (fightOrder < 50) { // Player goes first
            attackButton.setEnabled(true);
            fightOrderLabel.setText("Your Turn!");
            fightRakko1Label.setVisible(false);
            fightRakko2Label.setVisible(true);
            updateDisplay();
            checkPlayerHealth();

        } else if (fightOrder > 50) { // boss goes first
            attackButton.setEnabled(false);
            fightOrderLabel.setText("Boss's Turn!");
            fightRakko1Label.setVisible(true);
            fightRakko2Label.setVisible(false);
            // int playerHealth = playerStats.getHealth();

            /*
             * TimerTask task = new TimerTask() {
             * 
             * @Override
             * public void run() {
             * playerStats.setHealth(playerHealth - boss1Atk);
             * updateDisplay();
             * checkPlayerHealth();
             * fightOrder = 1;
             * fightOrderDecision();
             * }
             * };
             */

            // long delay = 2000;
            // bossTimer.schedule(task, delay);

            // if (boss1Frame.isBoss1Visibility() == false) {
            // bossTimer.cancel();
            // }


            bossTimer.restart();

            

        }
    }

    public void updateDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        boss1HealthLabel.setText("Boss Health: " + boss1Health);
    }

    public void returnHome() {
        bossTimer.stop();
        hpFrame.setHomePageVisibility(true);
        boss1Frame.setBoss1Visibility(false);
        hpFrame.updateFrame();
        boss1Frame.updateFrame();
        hpPanel.refreshDisplay();
        hpPanel.updateDisplay();
    }

    public class AttackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boss1Health -= playerStats.getAtk();
            updateDisplay();
            fightOrder = 51;
            fightOrderDecision();
            if (boss1Health <= 0) {
                boss1Status = true;
                boss1Lost = false;
                returnHome();

            }
        }

    }

    public class HomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            returnHome();

        }

    }

    public class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int playerHealth = playerStats.getHealth();
            playerStats.setHealth(playerHealth - boss1Atk);
            updateDisplay();
            checkPlayerHealth();
            fightOrder = 1;
            fightOrderDecision();
        }

    }

}
