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
    private FightPageFrame fpFrame;
    private PlayerStats playerStats;

    private JLabel fightRakko1Label;
    private ImageIcon fightRakko1;
    private JLabel fightRakko2Label;
    private ImageIcon fightRakko2;
    private JLabel boss1Label;
    private ImageIcon boss1;

    private boolean boss1Status;

    private Font chiruFont;

    public HomePageFrame getHpFrame() {
        return hpFrame;
    }

    public void setHpFrame(HomePageFrame hpFrame) {
        this.hpFrame = hpFrame;
    }

    public FightPageFrame getFpFrame() {
        return fpFrame;
    }

    public void setFpFrame(FightPageFrame fpFrame) {
        this.fpFrame = fpFrame;
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

        attackButton = new JButton("Attack");
        attackButton.setFont(this.chiruFont);
        add(attackButton);
        AttackListener atkListener = new AttackListener();
        attackButton.addActionListener(atkListener);

        JPanel topPanel = new JPanel();
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
        add(playerStatsPanel, BorderLayout.LINE_START);
        //playerStatsPanel.setLayout(new BorderLayout());
        playerAtkLabel = new JLabel("Your Attack: " + 0);
        playerAtkLabel.setFont(this.chiruFont);
        playerStatsPanel.add(playerAtkLabel);

        playerHealthLabel = new JLabel("Your Health: " + 0);
        playerHealthLabel.setFont(this.chiruFont);
        playerStatsPanel.add(playerHealthLabel);

        
        JPanel bossStatsPanel = new JPanel();
        add(bossStatsPanel, BorderLayout.LINE_END);
        boss1HealthLabel = new JLabel("Boss Health: " + boss1Health);
        boss1HealthLabel.setFont(this.chiruFont);
        bossStatsPanel.add(boss1HealthLabel);

        

        fightRakko1Label = new JLabel("");
        add(fightRakko1Label);
        fightRakko1 = new ImageIcon(getClass().getResource("/rakko1.png"));
        fightRakko1Label.setIcon(fightRakko1);
        fightRakko2Label = new JLabel("");
        add(fightRakko2Label);
        fightRakko2 = new ImageIcon(getClass().getResource("/rakko2.png"));
        fightRakko2Label.setIcon(fightRakko2);

        boss1Label = new JLabel("");
        add(boss1Label);
        boss1 = new ImageIcon(getClass().getResource("/boss1.png"));
        boss1Label.setIcon(boss1);

    }

    public void checkPlayerHealth() {
        if (playerStats.getHealth() <= 0) {
            returnHome();
        }
    }

    public void refreshDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        Random rand = new Random();
        fightOrder = rand.nextInt(1, 101);
        checkPlayerHealth();
        fightOrderDecision();

    }

    public void fightOrderDecision() {
        if (fightOrder < 50) {
            attackButton.setEnabled(true);
            fightOrderLabel.setText("Your Turn!");
            fightRakko1Label.setVisible(false);
            fightRakko2Label.setVisible(true);
            updateDisplay();
            // checkPlayerHealth();
        } else if (fightOrder > 50) {
            attackButton.setEnabled(false);
            fightOrderLabel.setText("Boss's Turn!");
            fightRakko1Label.setVisible(true);
            fightRakko2Label.setVisible(false);
            int playerHealth = playerStats.getHealth();
            playerStats.setHealth(playerHealth - boss1Atk);
            updateDisplay();
            // checkPlayerHealth();
            fightOrder = 1;
            fightOrderDecision();
        }
    }

    public void updateDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        boss1HealthLabel.setText("Boss Health: " + boss1Health);
    }

    public void returnHome() {
        hpFrame.setHomePageVisibility(true);
        fpFrame.setFightPageVisibility(false);
        hpFrame.updateFrame();
        fpFrame.updateFrame();
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

}
