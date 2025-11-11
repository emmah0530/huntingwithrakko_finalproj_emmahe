package edu.guilford;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage extends JPanel {
    public int cake;
    public int atk;
    public int health;

    private JLabel cakeLabel;
    private JButton cakeClicker;

    private JLabel atkLabel;
    private JButton atkButtonIncrease;
    private JButton atkButtonDecrease;

    private JLabel healthLabel;
    private JButton healthButtonIncrease;
    private JButton healthButtonDecrease;

    private JLabel gambleLabel;
    private JButton gambleButton;
    private int gambleNumber;

    // public int cake;

    public HomePage() {
        setPreferredSize(new Dimension(1000, 600));
        cakeLabel = new JLabel("Cake: " + cake);
        add(cakeLabel);
        cakeClicker = new JButton("Click Here For Cake!");
        add(cakeClicker);
        ClickerListener clickListener = new ClickerListener();
        cakeClicker.addActionListener(clickListener);

        atkLabel = new JLabel("Attack: " + atk);
        add(atkLabel);
        atkButtonIncrease = new JButton("Increase Attack");
        add(atkButtonIncrease);
        AttackIncreaseListener atkIListener = new AttackIncreaseListener();
        atkButtonIncrease.addActionListener(atkIListener);

        atkButtonDecrease = new JButton("Decrease Attack");
        add(atkButtonDecrease);
        AttackDecreaseListener atkDListener = new AttackDecreaseListener();
        atkButtonDecrease.addActionListener(atkDListener);

        healthLabel = new JLabel("Health: " + health);
        add(healthLabel);
        healthButtonIncrease = new JButton("Increase Health");
        add(healthButtonIncrease);
        HealthIncreaseListener healthIListener = new HealthIncreaseListener();
        healthButtonIncrease.addActionListener(healthIListener);

        healthButtonDecrease = new JButton("Decrease Health");
        add(healthButtonDecrease);
        HealthDecreaseListener healthDListener = new HealthDecreaseListener();
        healthButtonDecrease.addActionListener(healthDListener);

        gambleLabel = new JLabel("Press the button to gamble!");
        add(gambleLabel);
        gambleButton = new JButton("Gamble!");
        add(gambleButton);
        GambleListener gambleListener = new GambleListener();
        gambleButton.addActionListener(gambleListener);

    }

    private void updateDisplay() {
        cakeLabel.setText("Cake: " + cake);
        atkLabel.setText("Attack: " + atk);
        healthLabel.setText("Health: " + health);
    }

    public class ClickerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cake += 1;
            updateDisplay();
        }

    }

    public class AttackIncreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cake > 0) {
                atk++;
                cake--;
            }
            updateDisplay();
        }

    }

    public class AttackDecreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (atk > 0) {
                atk--;
                cake++;
            }
            updateDisplay();
        }

    }

    public class HealthIncreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cake > 0) {
                health++;
                cake--;
            }
            updateDisplay();
        }

    }

    public class HealthDecreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (health > 0) {
                health--;
                cake++;
            }
            updateDisplay();
        }

    }

    public class GambleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Random rand = new Random();
            gambleNumber = rand.nextInt(1, 101);
            if (cake >= 50) {
                cake = cake - 50;
                if (gambleNumber <= 5) {
                    cake += 200;
                    gambleLabel.setText("You win 200 cakes! Play again?");
                } else if (gambleNumber <= 15) {
                    cake += 100;
                    gambleLabel.setText("You win 100 cakes! Play again?");
                } else if (gambleNumber <= 40) {
                    cake += 75;
                    gambleLabel.setText("You win 75 cakes! Play again?");
                } else if (gambleNumber <= 65) {
                    cake += 25;
                    gambleLabel.setText("You win 25 cakes! Play again?");
                } else {
                    cake += 0;
                    gambleLabel.setText("You didn't win any cakes. Play again?");
                }
            } else {
                gambleLabel.setText("You need at least 50 cake to gamble!");
            }
        updateDisplay();

        }

    }

}
