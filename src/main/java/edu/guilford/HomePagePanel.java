package edu.guilford;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class HomePagePanel extends JPanel {
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

    private JRadioButton boss1;
    private JRadioButton boss2;
    private JRadioButton boss3;
    private ButtonGroup bossButtonGroup;

    private JLabel rakko1;
    private ImageIcon rakko1Icon;


    public HomePagePanel() {
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new BorderLayout());

        // Clicker Aspect
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BorderLayout());
        
        clickerPanel.setPreferredSize(new Dimension(200,50));
        clickerPanel.setBackground(Color.cyan);
        clickerPanel.setLayout(new BorderLayout());

        add(clickerPanel, BorderLayout.PAGE_START);
        
        cakeLabel = new JLabel("Cake: " + cake);
        //add(cakeLabel);
        cakeClicker = new JButton("Click Here For Cake!");
        //add(cakeClicker);
        ClickerListener clickListener = new ClickerListener();
        cakeClicker.addActionListener(clickListener);
        clickerPanel.add(cakeLabel, BorderLayout.PAGE_START);
        clickerPanel.add(cakeClicker, BorderLayout.CENTER);
        
        // Attack aspect
        JPanel atkPanel = new JPanel();
        add(atkPanel, BorderLayout.LINE_START);
        atkPanel.setPreferredSize(new Dimension(240,50));
        atkPanel.setBackground(Color.green);
        atkPanel.setLayout(new BorderLayout());

        atkLabel = new JLabel("Attack: " + atk);
        atkPanel.add(atkLabel, BorderLayout.PAGE_START);
        atkButtonIncrease = new JButton("Increase Attack");
        atkPanel.add(atkButtonIncrease, BorderLayout.LINE_START);
        AttackIncreaseListener atkIListener = new AttackIncreaseListener();
        atkButtonIncrease.addActionListener(atkIListener);

        atkButtonDecrease = new JButton("Decrease Attack");
        atkPanel.add(atkButtonDecrease, BorderLayout.LINE_END);
        AttackDecreaseListener atkDListener = new AttackDecreaseListener();
        atkButtonDecrease.addActionListener(atkDListener);

        // Health aspect
        JPanel healthPanel = new JPanel();
        add(healthPanel, BorderLayout.LINE_END);
        healthPanel.setLayout(new BorderLayout());
        healthPanel.setPreferredSize(new Dimension(250,50));
        healthPanel.setBackground(Color.red);

        healthLabel = new JLabel("Health: " + health);
        healthPanel.add(healthLabel, BorderLayout.PAGE_START);

        healthButtonIncrease = new JButton("Increase Health");
        healthPanel.add(healthButtonIncrease, BorderLayout.LINE_START);
        HealthIncreaseListener healthIListener = new HealthIncreaseListener();
        healthButtonIncrease.addActionListener(healthIListener);

        healthButtonDecrease = new JButton("Decrease Health");
        healthPanel.add(healthButtonDecrease, BorderLayout.LINE_END);
        HealthDecreaseListener healthDListener = new HealthDecreaseListener();
        healthButtonDecrease.addActionListener(healthDListener);


        // Gambling aspect
        JPanel gamblePanel = new JPanel();
        add(gamblePanel, BorderLayout.PAGE_END);
        gamblePanel.setPreferredSize(new Dimension(300,100));
        gamblePanel.setLayout(new BorderLayout());
        gamblePanel.setBackground(Color.pink);

        gambleLabel = new JLabel("Press the button to gamble!");
        gamblePanel.add(gambleLabel, BorderLayout.PAGE_START);
        gambleButton = new JButton("Gamble! (costs 50 cakes)");
        gamblePanel.add(gambleButton, BorderLayout.PAGE_END);
        GambleListener gambleListener = new GambleListener();
        gambleButton.addActionListener(gambleListener);

        // Character displayed on the screen
        JPanel rakkoPanel = new JPanel();
        add(rakkoPanel, BorderLayout.CENTER);
        // rakkoPanel.setLayout(new BorderLayout());
        rakko1 = new JLabel("");
        rakkoPanel.add(rakko1);
        rakko1Icon = new ImageIcon(getClass().getResource("/rakko-chiikawa.gif"));
        rakko1.setIcon(rakko1Icon);
        
        

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
                gambleLabel.setText("You need at least 50 cakes to gamble!");
            }
        updateDisplay();

        }

    }

}
