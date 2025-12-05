package edu.guilford;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import javax.swing.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class Boss3Panel extends JPanel {
    private int boss3Atk;
    private int boss3Health;

    private JButton attackButton;
    private int playerAtk;
    private JLabel playerAtkLabel;
    private JLabel playerHealthLabel;
    private JLabel boss3HealthLabel;

    private int fightOrder;
    private JLabel fightOrderLabel;
    private JButton returnHome;

    private JLabel fightRakko1Label;
    private ImageIcon fightRakko1;
    private JLabel fightRakko2Label;
    private ImageIcon fightRakko2;
    private JLabel boss3Label;
    private ImageIcon boss3;

    private boolean boss3Status;
    private boolean boss3Lost;

    private Timer bossTimer;

    private Font chiruFont;
    private Font chiruFont25;

    private HomePagePanel hpPanel;
    private HomePageFrame hpFrame;
    private Boss3Frame boss3Frame;
    private PlayerStats playerStats;
    private Boss1Panel boss1Panel;
    private Boss2Panel boss2Panel;

    public int getPlayerAtk() {
        return playerAtk;
    }

    public void setPlayerAtk(int playerAtk) {
        this.playerAtk = playerAtk;
    }

    public HomePageFrame getHpFrame() {
        return hpFrame;
    }

    public void setHpFrame(HomePageFrame hpFrame) {
        this.hpFrame = hpFrame;
    }

    public Boss3Frame getBoss3Frame() {
        return boss3Frame;
    }

    public void setBoss3Frame(Boss3Frame fpFrame) {
        this.boss3Frame = fpFrame;
    }

    public HomePagePanel getHpPanel() {
        return hpPanel;
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

    public Boss1Panel getBoss1Panel() {
        return boss1Panel;
    }

    public void setBoss1Panel(Boss1Panel boss1Panel) {
        this.boss1Panel = boss1Panel;
    }

    public Boss2Panel getBoss2Panel() {
        return boss2Panel;
    }

    public void setBoss2Panel(Boss2Panel boss2Panel) {
        this.boss2Panel = boss2Panel;
    }

    public boolean isBoss3Status() {
        return boss3Status;
    }

    public void setBoss3Status(boolean boss3Status) {
        this.boss3Status = boss3Status;
    }

    public boolean isBoss3Lost() {
        return boss3Lost;
    }

    public void setBoss3Lost(boolean boss3Lost) {
        this.boss3Lost = boss3Lost;
    }

    public Boss3Panel() {
        setPreferredSize(new Dimension(1000, 600));
        Color backgroundColor = Color.decode("#FCF9FB");
        setBackground(backgroundColor);
        setLayout(new BorderLayout());

        Color usagiBeige = Color.decode("#FEEEC8");

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

        chiruFont25 = this.chiruFont.deriveFont(Font.BOLD, 25);

        boss3Atk = 150;
        boss3Health = 1250;
        //boss3Status = false;

        int delay = 2000;
        TimerListener timerListener = new TimerListener();
        bossTimer = new Timer(delay, timerListener);

        attackButton = new JButton("Attack");
        attackButton.setFont(chiruFont25);
        attackButton.setPreferredSize(new Dimension(1000, 50));
        add(attackButton, BorderLayout.PAGE_END);
        AttackListener atkListener = new AttackListener();
        attackButton.addActionListener(atkListener);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(usagiBeige);
        topPanel.setPreferredSize(new Dimension(1000, 50));
        add(topPanel, BorderLayout.PAGE_START);

        topPanel.add(Box.createRigidArea(new Dimension(1000, 5)));

        fightOrderLabel = new JLabel();
        fightOrderLabel.setFont(chiruFont25);
        topPanel.add(fightOrderLabel);

        topPanel.add(Box.createRigidArea(new Dimension(680, 0)));

        returnHome = new JButton("Return Home");
        returnHome.setFont(this.chiruFont);
        topPanel.add(returnHome);
        HomeListener homeListener = new HomeListener();
        returnHome.addActionListener(homeListener);

        JPanel playerStatsPanel = new JPanel();
        playerStatsPanel.setBackground(Color.white);
        add(playerStatsPanel, BorderLayout.LINE_END);
        playerStatsPanel.setLayout(new BoxLayout(playerStatsPanel, BoxLayout.LINE_AXIS));

        JPanel playerStatsSubPanel = new JPanel();
        playerStatsSubPanel.setBackground(Color.white);
        playerStatsSubPanel.setLayout(new BoxLayout(playerStatsSubPanel, BoxLayout.PAGE_AXIS));
        playerStatsPanel.add(playerStatsSubPanel);

        playerHealthLabel = new JLabel("Your Health: " + 0);
        playerHealthLabel.setFont(chiruFont25);
        playerStatsSubPanel.add(playerHealthLabel);

        playerStatsSubPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        playerAtkLabel = new JLabel("Your Attack: " + 0);
        playerAtkLabel.setFont(chiruFont25);
        playerStatsSubPanel.add(playerAtkLabel);
        playerStatsPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        JPanel bossStatsPanel = new JPanel();
        bossStatsPanel.setBackground(Color.white);
        add(bossStatsPanel, BorderLayout.LINE_START);
        bossStatsPanel.add(Box.createRigidArea(new Dimension(10, 500)));
        boss3HealthLabel = new JLabel("Boss Health: " + boss3Health);
        boss3HealthLabel.setFont(chiruFont25);
        bossStatsPanel.add(boss3HealthLabel);

        JPanel characterPanel = new JPanel();
        characterPanel.setBackground(Color.white);
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.LINE_AXIS));
        add(characterPanel, BorderLayout.CENTER);

        characterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        boss3Label = new JLabel("");
        characterPanel.add(boss3Label);
        boss3 = new ImageIcon(getClass().getResource("/boss3.png"));
        boss3Label.setIcon(boss3);

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
            boss3Lost = true;
        }
    }

    public void refreshDisplay() {
        boss3Lost = false;
        boss3Health = 1250;
        boss3Status = false;
        boss1Panel.setBoss1Status(false);
        boss1Panel.setBoss1Lost(false);
        boss2Panel.setBoss2Status(false);
        boss2Panel.setBoss2Lost(false);
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
            bossTimer.stop();

        } else if (fightOrder > 50) { // boss goes first
            attackButton.setEnabled(false);
            fightOrderLabel.setText("Boss's Turn!");
            fightRakko1Label.setVisible(true);
            fightRakko2Label.setVisible(false);

            bossTimer.restart();

        }
    }

    public void updateDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        boss3HealthLabel.setText("Boss Health: " + boss3Health);
    }

    public void returnHome() {
        bossTimer.stop();
        hpFrame.setHomePageVisibility(true);
        boss3Frame.setBoss3Visibility(false);
        hpFrame.updateFrame();
        boss3Frame.updateFrame();
        hpPanel.refreshDisplay();
        hpPanel.updateDisplay();
    }

    public class AttackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boss3Health -= playerStats.getAtk();
            updateDisplay();
            fightOrder = 51;
            fightOrderDecision();
            if (boss3Health <= 0) {
                boss3Status = true;
                boss3Lost = false;
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
            playerStats.setHealth(playerHealth - boss3Atk);
            updateDisplay();
            checkPlayerHealth();
            fightOrder = 1;
            fightOrderDecision();
        }

    }
}