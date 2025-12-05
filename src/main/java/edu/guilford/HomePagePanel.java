package edu.guilford;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class HomePagePanel extends JPanel {

    // Instantiate all variables and GUI components
    private JLabel cakeLabel;
    private JButton cakeClicker;
    private JButton tutorialButton;

    private JLabel atkLabel;
    private JButton atkButtonIncrease;
    private JButton atkButtonDecrease;
    private JButton atkButtonIncrease10;
    private JButton atkButtonDecrease10;

    private JLabel healthLabel;
    private JButton healthButtonIncrease;
    private JButton healthButtonDecrease;
    private JButton healthButtonIncrease10;
    private JButton healthButtonDecrease10;

    private JButton statsInfo;
    private JLabel statDeficient;

    private JTextArea gambleText;
    private JButton gambleButton;
    private int gambleNumber;

    private JRadioButton boss1;
    private boolean fightBoss1;
    private JRadioButton boss2;
    private boolean fightBoss2;
    private JRadioButton boss3;
    private boolean fightBoss3;
    private ButtonGroup bossButtonGroup;
    private JButton bossInfo;
    private JButton fightButton;

    private JLabel rakko1;
    private ImageIcon rakko1Icon;

    private HomePageFrame hpFrame;
    private Boss1Frame boss1Frame;
    private Boss1Panel boss1Panel;
    private Boss2Frame boss2Frame;
    private Boss2Panel boss2Panel;
    private Boss3Frame boss3Frame;
    private Boss3Panel boss3Panel;

    private PlayerStats playerStats;

    private Font chiruFont;
    private Font chiruFont15;
    private Font chiruFont20;
    private Font chiruFont25;

    // getters and setters (to connect panels and frames)
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

    public Boss2Frame getBoss2Frame() {
        return boss2Frame;
    }

    public void setBoss2Frame(Boss2Frame boss2Frame) {
        this.boss2Frame = boss2Frame;
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

    public Boss3Frame getBoss3Frame() {
        return boss3Frame;
    }

    public void setBoss3Frame(Boss3Frame boss3Frame) {
        this.boss3Frame = boss3Frame;
    }

    public Boss3Panel getBoss3Panel() {
        return boss3Panel;
    }

    public void setBoss3Panel(Boss3Panel boss3Panel) {
        this.boss3Panel = boss3Panel;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    // default constructor
    public HomePagePanel() {
        // sets basic traits of the whole panel
        setPreferredSize(new Dimension(1000, 600));
        Color backgroundColor = Color.decode("#FCF9FB");
        setBackground(backgroundColor);
        setLayout(new BorderLayout());

        // custom colors
        Color usagiBeige = Color.decode("#FEEEC8");
        Color hachiBlue = Color.decode("#A1CBDC");
        Color chiikawaPink = Color.decode("#EEC2C7");
        Color grassGreen = Color.decode("#CFE6B0");
        Color atkGreen = Color.decode("#70CE64");
        Color healthRed = Color.decode("#F04265");

        // The following try catch block was created by the ChatGPT embedded in VS Code
        // by modifying what I had tried already, since I couldn't figure out how the
        // font worked

        // Attempt to load the bundled TTF font from resources. Use an absolute path
        // (root of classpath)
        // and guard against a null InputStream so Font.createFont doesn't throw an
        // IOException.
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

        // different sized fonts
        chiruFont15 = this.chiruFont.deriveFont(Font.BOLD, 15);
        chiruFont20 = this.chiruFont.deriveFont(Font.BOLD, 20);
        chiruFont25 = this.chiruFont.deriveFont(Font.BOLD, 25);

        // Clicker Aspect
        JPanel clickerPanel = new JPanel();
        clickerPanel.setPreferredSize(new Dimension(200, 50));
        clickerPanel.setBackground(usagiBeige);
        clickerPanel.setLayout(new BorderLayout());
        add(clickerPanel, BorderLayout.PAGE_START);

        // cake label
        cakeLabel = new JLabel("Cake: " + 0);
        cakeLabel.setFont(chiruFont25);
        // cake button
        cakeClicker = new JButton("Click Here For Cake!");
        cakeClicker.setFont(chiruFont20);
        ClickerListener clickListener = new ClickerListener();
        cakeClicker.addActionListener(clickListener);
        clickerPanel.add(cakeLabel, BorderLayout.PAGE_START);
        clickerPanel.add(cakeClicker, BorderLayout.CENTER);

        // tutorial button
        tutorialButton = new JButton("How To Play");
        tutorialButton.setFont(this.chiruFont);
        TutorialListener tutorialListener = new TutorialListener();
        tutorialButton.addActionListener(tutorialListener);
        clickerPanel.add(tutorialButton, BorderLayout.LINE_END);

        // Stats aspect
        JPanel statsPanel = new JPanel();
        add(statsPanel, BorderLayout.LINE_START);
        statsPanel.setBackground(hachiBlue);
        statsPanel.setPreferredSize(new Dimension(240, 100));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.PAGE_AXIS));

        statsPanel.add(Box.createRigidArea(new Dimension(0, 120)));

        // Attack aspect, created subpanel of statsPanel
        JPanel atkPanel = new JPanel();
        statsPanel.add(atkPanel);
        atkPanel.setPreferredSize(new Dimension(240, 50));
        atkPanel.setBackground(atkGreen);
        atkPanel.setLayout(new BoxLayout(atkPanel, BoxLayout.PAGE_AXIS));
        atkPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // attack label
        atkLabel = new JLabel("Attack: " + 0);
        atkLabel.setFont(chiruFont25);
        atkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        atkPanel.add(atkLabel);

        // created subpanel of attackPanel
        JPanel atkButtonPanel = new JPanel();
        atkPanel.add(atkButtonPanel);
        atkButtonPanel.setBackground(atkGreen);
        atkButtonPanel.setLayout(new BoxLayout(atkButtonPanel, BoxLayout.LINE_AXIS));
        atkButtonPanel.add(Box.createRigidArea(new Dimension(20, 10)));

        // increase attack +1 button
        atkButtonIncrease = new JButton("+1");
        atkButtonIncrease.setFont(this.chiruFont);
        atkButtonPanel.add(atkButtonIncrease);
        AttackIncreaseListener atkIListener = new AttackIncreaseListener();
        atkButtonIncrease.addActionListener(atkIListener);

        // decrease attack -1 button
        atkButtonDecrease = new JButton("-1");
        atkButtonDecrease.setFont(this.chiruFont);
        atkButtonPanel.add(atkButtonDecrease);
        AttackDecreaseListener atkDListener = new AttackDecreaseListener();
        atkButtonDecrease.addActionListener(atkDListener);

        atkButtonPanel.add(Box.createRigidArea(new Dimension(20, 10)));

        // created subpanel of attackPanel
        JPanel atkButtonPanel10 = new JPanel();
        atkPanel.add(atkButtonPanel10);
        atkButtonPanel10.setBackground(atkGreen);
        atkButtonPanel10.setLayout(new BoxLayout(atkButtonPanel10, BoxLayout.LINE_AXIS));
        atkButtonPanel10.add(Box.createRigidArea(new Dimension(20, 10)));

        // increase attack +10 button
        atkButtonIncrease10 = new JButton("+10");
        atkButtonIncrease10.setFont(this.chiruFont);
        atkButtonPanel10.add(atkButtonIncrease10);
        AttackIncrease10Listener atkI10Listener = new AttackIncrease10Listener();
        atkButtonIncrease10.addActionListener(atkI10Listener);

        // decrease attack -10 button
        atkButtonDecrease10 = new JButton("-10");
        atkButtonDecrease10.setFont(this.chiruFont);
        atkButtonPanel10.add(atkButtonDecrease10);
        AttackDecrease10Listener atkD10Listener = new AttackDecrease10Listener();
        atkButtonDecrease10.addActionListener(atkD10Listener);

        atkButtonPanel10.add(Box.createRigidArea(new Dimension(20, 10)));

        atkPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        // Health aspect
        JPanel healthPanel = new JPanel();
        statsPanel.add(healthPanel);
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.PAGE_AXIS));
        healthPanel.setPreferredSize(new Dimension(250, 50));
        healthPanel.setBackground(healthRed);

        // health label
        healthLabel = new JLabel("Health: " + 0);
        healthLabel.setFont(chiruFont25);
        healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        healthPanel.add(healthLabel);

        // created subpanel of healthPanel
        JPanel healthButtonPanel = new JPanel();
        healthPanel.add(healthButtonPanel);
        healthButtonPanel.setBackground(healthRed);
        healthButtonPanel.setLayout(new BoxLayout(healthButtonPanel, BoxLayout.LINE_AXIS));
        healthButtonPanel.add(Box.createRigidArea(new Dimension(20, 10)));

        // increase health +1 button
        healthButtonIncrease = new JButton("+1");
        healthButtonIncrease.setFont(this.chiruFont);
        healthButtonPanel.add(healthButtonIncrease);
        HealthIncreaseListener healthIListener = new HealthIncreaseListener();
        healthButtonIncrease.addActionListener(healthIListener);

        // decrease health -1 button
        healthButtonDecrease = new JButton("-1");
        healthButtonDecrease.setFont(this.chiruFont);
        healthButtonPanel.add(healthButtonDecrease);
        HealthDecreaseListener healthDListener = new HealthDecreaseListener();
        healthButtonDecrease.addActionListener(healthDListener);

        healthButtonPanel.add(Box.createRigidArea(new Dimension(20, 10)));

        // created subpanel of healthPanel
        JPanel healthButtonPanel10 = new JPanel();
        healthPanel.add(healthButtonPanel10);
        healthButtonPanel10.setBackground(healthRed);
        healthButtonPanel10.setLayout(new BoxLayout(healthButtonPanel10, BoxLayout.LINE_AXIS));
        healthButtonPanel10.add(Box.createRigidArea(new Dimension(20, 10)));

        // increase health +10 button
        healthButtonIncrease10 = new JButton("+10");
        healthButtonIncrease10.setFont(this.chiruFont);
        healthButtonPanel10.add(healthButtonIncrease10);
        HealthIncrease10Listener healthI10Listener = new HealthIncrease10Listener();
        healthButtonIncrease10.addActionListener(healthI10Listener);

        // decrease health -10 button
        healthButtonDecrease10 = new JButton("-10");
        healthButtonDecrease10.setFont(this.chiruFont);
        healthButtonPanel10.add(healthButtonDecrease10);
        HealthDecrease10Listener healthD10Listener = new HealthDecrease10Listener();
        healthButtonDecrease10.addActionListener(healthD10Listener);

        healthButtonPanel10.add(Box.createRigidArea(new Dimension(20, 10)));

        healthPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        // info JOptionPane (to guide players through game)
        statsInfo = new JButton("info");
        statsInfo.setFont(this.chiruFont);
        statsInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsInfo.setMaximumSize(new Dimension(163, 30));
        statsPanel.add(statsInfo);
        StatsInfoListener statsInfoListener = new StatsInfoListener();
        statsInfo.addActionListener(statsInfoListener);

        statsPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        // label that appears when cake/attack/health are not enough to increase or
        // decrease stats
        statDeficient = new JLabel("");
        statDeficient.setAlignmentX(Component.CENTER_ALIGNMENT);
        statDeficient.setFont(chiruFont15);
        statsPanel.add(statDeficient);

        // Gambling aspect
        JPanel gamblePanel = new JPanel();
        add(gamblePanel, BorderLayout.LINE_END);
        gamblePanel.setPreferredSize(new Dimension(300, 100));
        gamblePanel.setLayout(new BoxLayout(gamblePanel, BoxLayout.PAGE_AXIS));
        gamblePanel.setBackground(chiikawaPink);

        gamblePanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Text to give instructions for gambling
        // Text changes to announce results of gambling
        JPanel gambleTextPanel = new JPanel();
        gamblePanel.add(gambleTextPanel);
        gambleTextPanel.setLayout(new BoxLayout(gambleTextPanel, BoxLayout.LINE_AXIS));
        gambleTextPanel.setBackground(chiikawaPink);

        // Chose JTextArea over JLabel to allow line wrapping
        gambleTextPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        gambleText = new JTextArea("Press the button to gamble for a chance to win up to 500 cakes!");
        gambleText.setFont(this.chiruFont);
        gambleText.setLineWrap(true);
        gambleText.setWrapStyleWord(true);
        gambleText.setEditable(false);
        gambleText.setFocusable(false);
        gambleText.setOpaque(false);
        gambleTextPanel.add(gambleText);
        gambleTextPanel.add(Box.createRigidArea(new Dimension(8, 0)));

        // Gambling button
        gambleButton = new JButton("Gamble!(costs 50 cakes)");
        gambleButton.setFont(this.chiruFont);
        gambleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamblePanel.add(gambleButton);
        GambleListener gambleListener = new GambleListener();
        gambleButton.addActionListener(gambleListener);
        gamblePanel.add(Box.createRigidArea(new Dimension(10, 10)));

        // Boss radio buttons
        JPanel bossPanel = new JPanel();
        add(bossPanel, BorderLayout.PAGE_END);
        bossPanel.setPreferredSize(new Dimension(300, 50));
        bossPanel.setBackground(grassGreen);

        boss1 = new JRadioButton("Boss 1");
        boss1.setFont(this.chiruFont);
        boss2 = new JRadioButton("Boss 2");
        boss2.setFont(this.chiruFont);
        boss3 = new JRadioButton("Boss 3");
        boss3.setFont(this.chiruFont);
        bossButtonGroup = new ButtonGroup();
        bossButtonGroup.add(boss1);
        bossButtonGroup.add(boss2);
        bossButtonGroup.add(boss3);
        bossPanel.add(boss1);
        bossPanel.add(boss2);
        bossPanel.add(boss3);

        // sets 1st one selected at beginning and locks boss 2 and 3
        boss1.setSelected(true);
        fightBoss1 = true;
        boss2.setEnabled(false);
        fightBoss2 = false;
        boss3.setEnabled(false);
        fightBoss3 = false;
        BossListener bossListener = new BossListener();
        boss1.addActionListener(bossListener);
        boss2.addActionListener(bossListener);
        boss3.addActionListener(bossListener);

        // JOptionPane to detail how fighting works and give recommended health stats
        bossInfo = new JButton("?");
        bossPanel.add(bossInfo);
        BossInfoListener bossInfoListener = new BossInfoListener();
        bossInfo.addActionListener(bossInfoListener);

        // button to trigger fighting the bosses
        fightButton = new JButton("Fight!");
        fightButton.setFont(this.chiruFont);
        bossPanel.add(fightButton);
        FightListener fightListener = new FightListener();
        fightButton.addActionListener(fightListener);

        // Character displayed on the screen
        JPanel rakkoPanel = new JPanel();
        add(rakkoPanel, BorderLayout.CENTER);
        rakkoPanel.setBackground(backgroundColor);
        rakko1 = new JLabel("");
        rakko1Icon = new ImageIcon(getClass().getResource("/rakko-chiikawa-1.gif"));
        rakko1.setIcon(rakko1Icon);
        rakkoPanel.add(rakko1);
    }

    // updates the labels when changes are made
    public void updateDisplay() {
        cakeLabel.setText("Cake: " + playerStats.getCake());
        atkLabel.setText("Attack: " + playerStats.getAtk());
        healthLabel.setText("Health: " + playerStats.getHealth());
        statDeficient.setText("");
    }

    // refreshes the display to prevent any negative health and to check the status
    // of boss (win/lose)
    public void refreshDisplay() {
        checkBossStatus();
        if (playerStats.getHealth() < 0) {
            playerStats.setHealth(0);
        }
    }

    // checks if you won against each boss, gives cake reward as necessary, and
    // unlocks next boss
    private void checkBossStatus() {
        int cake = playerStats.getCake();
        if (boss1Panel.isBoss1Status()) {
            boss2.setEnabled(true);
            playerStats.setCake(cake += 50);
            updateDisplay();
            JOptionPane.showMessageDialog(null, "You beat the first boss and earned 50 more cakes!",
                    "Boss 1 Complete!",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (boss1Panel.isBoss1Lost()) {
            updateDisplay();
            JOptionPane.showMessageDialog(null, "You lost against the first boss. Increase your stats and try again!",
                    "Boss 1 Failed :(",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (boss2Panel.isBoss2Status()) {
            boss3.setEnabled(true);
            playerStats.setCake(cake += 125);
            updateDisplay();
            JOptionPane.showMessageDialog(null, "You beat the second boss and earned 125 more cakes!",
                    "Boss 2 Complete!",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (boss2Panel.isBoss2Lost()) {
            updateDisplay();
            JOptionPane.showMessageDialog(null, "You lost against the second boss. Increase your stats and try again!",
                    "Boss 2 Failed :(",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (boss3Panel.isBoss3Status()) {
            playerStats.setCake(cake += 300);
            updateDisplay();
            JOptionPane.showMessageDialog(null,
                    "You beat the third boss and earned 300 more cakes! Congratulations on beating the game!",
                    "Boss 3 Complete!",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (boss3Panel.isBoss3Lost()) {
            updateDisplay();
            JOptionPane.showMessageDialog(null, "You lost against the third boss. Increase your stats and try again!",
                    "Boss 3 Failed :(",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    // action listener for cake clicker, increases cake by +1 after each click
    public class ClickerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cake = playerStats.getCake();
            playerStats.setCake(cake += 1);
            updateDisplay();
        }
    }

    // action listener for tutorial, creates JOptionPane to give tutorial
    public class TutorialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "Earn cakes through clicking, fighting bosses, and trying your luck through gambling!\nCakes can be used to upgrade Rakko's stats to fight harder bosses.\nBeat all three bosses to complete the game!",
                    "How To Play",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // action listener for attack increase +1
    public class AttackIncreaseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cake = playerStats.getCake();
            int atk = playerStats.getAtk();
            if (cake > 0) {
                playerStats.setAtk(atk += 1);
                playerStats.setCake(cake -= 1);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 1 cake!");
            }
        }
    }

    // action listener for attack decrease -1
    public class AttackDecreaseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int atk = playerStats.getAtk();
            int cake = playerStats.getCake();
            if (atk > 0) {
                playerStats.setAtk(atk -= 1);
                playerStats.setCake(cake += 1);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 1 attack!");
            }
        }
    }

    // action listener for attack increase +10
    public class AttackIncrease10Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cake = playerStats.getCake();
            int atk = playerStats.getAtk();
            if (cake >= 10) {
                playerStats.setAtk(atk += 10);
                playerStats.setCake(cake -= 10);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 10 cakes!");
            }
        }
    }

    // action listener for attack decrease -10
    public class AttackDecrease10Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int atk = playerStats.getAtk();
            int cake = playerStats.getCake();
            if (atk >= 10) {
                playerStats.setAtk(atk -= 10);
                playerStats.setCake(cake += 10);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 10 attack!");
            }
        }
    }

    // action listener for health increase +1
    public class HealthIncreaseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int health = playerStats.getHealth();
            int cake = playerStats.getCake();
            if (cake > 0) {
                playerStats.setHealth(health += 1);
                playerStats.setCake(cake -= 1);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 1 cake!");
            }
        }
    }

    // action listener for health decrease -1
    public class HealthDecreaseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int health = playerStats.getHealth();
            int cake = playerStats.getCake();
            if (health > 0) {
                playerStats.setHealth(health -= 1);
                playerStats.setCake(cake += 1);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 1 health!");
            }
        }
    }

    // action listener for health increase +10
    public class HealthIncrease10Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int health = playerStats.getHealth();
            int cake = playerStats.getCake();
            if (cake >= 10) {
                playerStats.setHealth(health += 10);
                playerStats.setCake(cake -= 10);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 10 cakes!");
            }
        }
    }

    // action listener for health decrease -10
    public class HealthDecrease10Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int health = playerStats.getHealth();
            int cake = playerStats.getCake();
            if (health >= 10) {
                playerStats.setHealth(health -= 10);
                playerStats.setCake(cake += 10);
                updateDisplay();
            } else {
                statDeficient.setText("You need at least 10 health!");
            }
        }
    }

    // action listener for tutorial about how to change stats
    public class StatsInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "Spend 1 cake for a +1 increase or 10 cakes for a +10 increase in attack or health! \n Decrease your attack or health get cakes back!",
                    "How to Change Your Stats",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // action listener for gambling system
    public class GambleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random rand = new Random();
            gambleNumber = rand.nextInt(1, 101);
            int cake = playerStats.getCake();
            // requires 50 cake to start
            if (cake >= 50) {
                playerStats.setCake(cake -= 50);
                // probabilities: 1% for 500, 3% for 200, 10% for 100, 25% for 75, 25% for 25,
                // 36% for nothing
                if (gambleNumber == 1) {
                    playerStats.setCake(cake += 500);
                    gambleText.setText("JACKPOT! You win 500 cakes! Play again?");
                }
                if (gambleNumber <= 4) {
                    playerStats.setCake(cake += 200);
                    gambleText.setText("You win 200 cakes! Play again?");
                } else if (gambleNumber <= 14) {
                    playerStats.setCake(cake += 100);
                    gambleText.setText("You win 100 cakes! Play again?");
                } else if (gambleNumber <= 39) {
                    playerStats.setCake(cake += 75);
                    gambleText.setText("You win 75 cakes! Play again?");
                } else if (gambleNumber <= 64) {
                    playerStats.setCake(cake += 25);
                    gambleText.setText("You win 25 cakes! Play again?");
                } else {
                    gambleText.setText("You didn't win any cakes. Play again?");
                }
            } else {
                gambleText.setText("You need at least 50 cakes to gamble!");
            }
            updateDisplay();
        }
    }

    // action listener for boss fight radio buttons
    public class BossListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton chosenButton = (JRadioButton) e.getSource();
            if (chosenButton == boss1) {
                // fightBoss booleans indicate which boss to fight in FightListener
                fightBoss1 = true;
                fightBoss2 = false;
                fightBoss3 = false;
            } else if (chosenButton == boss2) {
                fightBoss2 = true;
                fightBoss1 = false;
                fightBoss3 = false;
            } else if (chosenButton == boss3) {
                fightBoss3 = true;
                fightBoss1 = false;
                fightBoss2 = false;
            }
            updateDisplay();
        }
    }

    // action listener for tutorial about how fighting works and tips
    public class BossInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "Fight bosses to earn cake!\nWhen you enter battle, you have a 50% chance of attacking first, so prepare accordingly!\nRecommended (minimum): 20 Health for Boss 1, 50 Health for Boss 2, 150 Health for Boss 3",
                    "Tips for Fighting",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // action listener for fight button
    public class FightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (playerStats.getHealth() <= 0) {
                JOptionPane.showMessageDialog(null,
                        "You need at least 1 Health to Fight!",
                        "Health Too Low",
                        JOptionPane.PLAIN_MESSAGE);
            } else if (fightBoss1) {
                // connects to other panels and frames
                boss1Panel.checkPlayerHealth();
                boss1Panel.refreshDisplay();
                boss1Panel.updateDisplay();
                hpFrame.setHomePageVisibility(false);
                boss1Frame.setBoss1Visibility(true);
                hpFrame.updateFrame();
                boss1Frame.updateFrame();
            } else if (fightBoss2) {
                boss2Panel.checkPlayerHealth();
                boss2Panel.refreshDisplay();
                boss2Panel.updateDisplay();
                hpFrame.setHomePageVisibility(false);
                boss2Frame.setBoss2Visibility(true);
                hpFrame.updateFrame();
                boss2Frame.updateFrame();
            } else if (fightBoss3) {
                boss3Panel.checkPlayerHealth();
                boss3Panel.refreshDisplay();
                boss3Panel.updateDisplay();
                hpFrame.setHomePageVisibility(false);
                boss3Frame.setBoss3Visibility(true);
                hpFrame.updateFrame();
                boss3Frame.updateFrame();
            }
        }
    }

}
