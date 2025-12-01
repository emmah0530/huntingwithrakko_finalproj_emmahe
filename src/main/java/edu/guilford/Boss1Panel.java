package edu.guilford;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public Boss1Panel() {
        setPreferredSize(new Dimension(1000, 600));
        Color backgroundColor = Color.decode("#FCF9FB");
        setBackground(backgroundColor);

        boss1Atk = 10;

        attackButton = new JButton("Attack");
        add(attackButton);
        AttackListener atkListener = new AttackListener();
        attackButton.addActionListener(atkListener);

        fightOrderLabel = new JLabel();
        add(fightOrderLabel);

        // playerStats may not be provided yet (set via setter after construction).
        // initialize with a safe default and update later in setPlayerStats(...)
        // playerAtk = 0;
        playerAtkLabel = new JLabel("Your Attack: " + 0);
        add(playerAtkLabel);

        playerHealthLabel = new JLabel("Your Health: " + 0);
        add(playerHealthLabel);

        boss1Health = 100;
        boss1HealthLabel = new JLabel("Boss Health: " + boss1Health);
        add(boss1HealthLabel);

        returnHome = new JButton("Return Home");
        add(returnHome);
        HomeListener homeListener = new HomeListener();
        returnHome.addActionListener(homeListener);

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

    public void refreshDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        Random rand = new Random();
        fightOrder = rand.nextInt(1, 101);
        fightOrderDecision();
        
    }

    public void fightOrderDecision() {
        if (fightOrder < 50) {
            attackButton.setEnabled(true);
            fightOrderLabel.setText("Your Turn!");
            fightRakko1Label.setVisible(false);
            fightRakko2Label.setVisible(true);
            updateDisplay();
        } else if (fightOrder > 50) {
            attackButton.setEnabled(false);
            fightOrderLabel.setText("Boss's Turn!");
            fightRakko1Label.setVisible(true);
            fightRakko2Label.setVisible(false);
            int playerHealth = playerStats.getHealth();
            playerStats.setHealth(playerHealth - boss1Atk);
            updateDisplay();
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
