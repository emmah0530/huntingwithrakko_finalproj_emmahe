package edu.guilford;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FightPagePanel extends JPanel {
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

    public FightPagePanel() {
        setPreferredSize(new Dimension(1000, 600));
        Color backgroundColor = Color.decode("#FCF9FB");
        setBackground(backgroundColor);

        attackButton = new JButton("Attack");
        add(attackButton);

        fightOrderLabel = new JLabel();
        add(fightOrderLabel);

        Random rand = new Random();
        fightOrder = rand.nextInt(1, 101);
        if (fightOrder < 50) {
            attackButton.setEnabled(true);
            fightOrderLabel.setText("Your Turn!");
        } else if (fightOrder > 50) {
            attackButton.setEnabled(false);
            fightOrderLabel.setText("Boss's Turn!");
        }

        // playerStats may not be provided yet (set via setter after construction).
        // initialize with a safe default and update later in setPlayerStats(...)
        //playerAtk = 0;
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

    }

    public void updateDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        Random rand = new Random();
        fightOrder = rand.nextInt(1, 101);
        if (fightOrder < 50) {
            attackButton.setEnabled(true);
            fightOrderLabel.setText("Your Turn!");
        } else if (fightOrder > 50) {
            attackButton.setEnabled(false);
            fightOrderLabel.setText("Boss's Turn!");
        }
        
    }

    public class HomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hpFrame.setHomePageVisibility(true);
            fpFrame.setFightPageVisibility(false);
            hpFrame.updateFrame();
            fpFrame.updateFrame();
        }

    }

}
