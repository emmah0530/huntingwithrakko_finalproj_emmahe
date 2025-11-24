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
        // If the home panel is provided after construction, update the displayed attack
        // value (following code with try-catch block created by GPT-5 mini built into VS Code)
        if (this.hpPanel != null && playerAtkLabel != null) {
            try {
                playerAtk = this.hpPanel.getAtk();
                playerAtkLabel.setText("Your Attack: " + playerAtk);
            } catch (Exception ex) {
                // fallback: leave label as-is if anything goes wrong
            }
        }
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

        playerAtkLabel = new JLabel("Your Attack: " + playerAtk);
        add(playerAtkLabel);
        System.out.println("playerAtk = " + playerAtk);

        playerHealthLabel = new JLabel("Your Health: ");
        add(playerHealthLabel);

        boss1Health = 100;
        boss1HealthLabel = new JLabel("Boss Health: " + boss1Health);
        add(boss1HealthLabel);

        returnHome = new JButton("Return Home");
        add(returnHome);
        HomeListener homeListener = new HomeListener();
        returnHome.addActionListener(homeListener);

        updateDisplay();

    }

    public void updateDisplay() {
        playerAtkLabel.setText("Your Attack: " + playerAtk);
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
