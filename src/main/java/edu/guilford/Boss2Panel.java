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

public class Boss2Panel extends JPanel {
    private int boss2Atk;
    private int boss2Health;

    private JButton attackButton;
    private int playerAtk;
    private JLabel playerAtkLabel;
    private JLabel playerHealthLabel;
    private JLabel boss2HealthLabel;

    private int fightOrder;
    private JLabel fightOrderLabel;
    private JButton returnHome; // temp button
    private HomePagePanel hpPanel;
    private HomePageFrame hpFrame;
    private Boss2Frame boss2Frame;
    private PlayerStats playerStats;

    private JLabel fightRakko1Label;
    private ImageIcon fightRakko1;
    private JLabel fightRakko2Label;
    private ImageIcon fightRakko2;
    private JLabel boss2Label;
    private ImageIcon boss2;

    private boolean boss2Status;

    public HomePageFrame getHpFrame() {
        return hpFrame;
    }

    public void setHpFrame(HomePageFrame hpFrame) {
        this.hpFrame = hpFrame;
    }

    public Boss2Frame getBoss2Frame() {
        return boss2Frame;
    }

    public void setBoss2Frame(Boss2Frame fpFrame) {
        this.boss2Frame = fpFrame;
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

    public boolean isBoss2Status() {
        return boss2Status;
    }

    public void setBoss2Status(boolean boss1Status) {
        this.boss2Status = boss1Status;
    }

    public Boss2Panel() {
        setPreferredSize(new Dimension(1000, 600));
        Color backgroundColor = Color.decode("#FCF9FB");
        setBackground(backgroundColor);

        boss2Atk = 1;
        boss2Health = 10;
        boss2Status = false;

        attackButton = new JButton("Attack");
        add(attackButton);
        AttackListener atkListener = new AttackListener();
        attackButton.addActionListener(atkListener);

        fightOrderLabel = new JLabel();
        add(fightOrderLabel);

        playerAtkLabel = new JLabel("Your Attack: " + 0);
        add(playerAtkLabel);

        playerHealthLabel = new JLabel("Your Health: " + 0);
        add(playerHealthLabel);

        
        boss2HealthLabel = new JLabel("Boss Health: " + boss2Health);
        add(boss2HealthLabel);

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

        boss2Label = new JLabel("");
        add(boss2Label);
        boss2 = new ImageIcon(getClass().getResource("/boss2.jpg"));
        boss2Label.setIcon(boss2);

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
            //checkPlayerHealth();
        } else if (fightOrder > 50) {
            attackButton.setEnabled(false);
            fightOrderLabel.setText("Boss's Turn!");
            fightRakko1Label.setVisible(true);
            fightRakko2Label.setVisible(false);
            int playerHealth = playerStats.getHealth();
            playerStats.setHealth(playerHealth - boss2Atk);
            updateDisplay();
            //checkPlayerHealth();
            fightOrder = 1;
            fightOrderDecision();
        }
    }

    public void checkPlayerHealth() {
        if (playerStats.getHealth() <= 0) {
                returnHome();
            }
    }
    public void updateDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerStats.getAtk());
        playerHealthLabel.setText("Your Health: " + playerStats.getHealth());
        boss2HealthLabel.setText("Boss Health: " + boss2Health);
    }

    public void returnHome() {
        hpFrame.setHomePageVisibility(true);
        boss2Frame.setBoss2Visibility(false);
        hpFrame.updateFrame();
        boss2Frame.updateFrame();
        hpPanel.refreshDisplay();
        hpPanel.updateDisplay();
    }

    public class AttackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boss2Health -= playerStats.getAtk();
            updateDisplay();
            fightOrder = 51;
            fightOrderDecision();
            if (boss2Health <= 0) {
                boss2Status = true;
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
