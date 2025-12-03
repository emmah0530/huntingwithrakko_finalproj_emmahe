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
//import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

//import javax.swing.BorderFactory;
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

    private JLabel cakeLabel;
    private JButton cakeClicker;

    private JLabel atkLabel;
    private JButton atkButtonIncrease;
    private JButton atkButtonDecrease;

    private JLabel healthLabel;
    private JButton healthButtonIncrease;
    private JButton healthButtonDecrease;

    private JButton statsInfo;

    // private JLabel gambleLabel;
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

    private JLabel rakko1;
    private ImageIcon rakko1Icon;

    private JButton fightButton;
    private HomePageFrame hpFrame;
    private Boss1Frame boss1Frame;
    private Boss1Panel boss1Panel;
    private Boss2Frame boss2Frame;
    private Boss2Panel boss2Panel;
    private Boss3Frame boss3Frame;
    private Boss3Panel boss3Panel;

    private PlayerStats playerStats;

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

    public HomePagePanel() {
        setPreferredSize(new Dimension(1000, 600));
        Color backgroundColor = Color.decode("#FCF9FB");
        setBackground(backgroundColor);
        setLayout(new BorderLayout());

        Color usagiBeige = Color.decode("#FEEEC8");
        Color hachiBlue = Color.decode("#A1CBDC");
        Color chiikawaPink = Color.decode("#EEC2C7");
        Color grassGreen = Color.decode("#CFE6B0");
        Color atkGreen = Color.decode("#70CE64");
        Color healthRed = Color.decode("#F04265");

        // The following try catch block was created by the ChatGPT embedded in VS Code
        // by modifying what I had
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

        // Clicker Aspect
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BorderLayout());

        clickerPanel.setPreferredSize(new Dimension(200, 50));
        clickerPanel.setBackground(usagiBeige);
        clickerPanel.setLayout(new BorderLayout());

        add(clickerPanel, BorderLayout.PAGE_START);

        cakeLabel = new JLabel("Cake: " + 0);
        cakeLabel.setFont(this.chiruFont);
        cakeClicker = new JButton("Click Here For Cake!");
        cakeClicker.setFont(this.chiruFont);
        ClickerListener clickListener = new ClickerListener();
        cakeClicker.addActionListener(clickListener);
        clickerPanel.add(cakeLabel, BorderLayout.PAGE_START);
        clickerPanel.add(cakeClicker, BorderLayout.CENTER);

        // Stats aspect
        JPanel statsPanel = new JPanel();
        add(statsPanel, BorderLayout.LINE_START);
        statsPanel.setBackground(hachiBlue);
        statsPanel.setPreferredSize(new Dimension(240, 100));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.PAGE_AXIS));
        statsPanel.add(Box.createRigidArea(new Dimension(10, 50)));

        // Attack aspect
        JPanel atkPanel = new JPanel();
        statsPanel.add(atkPanel);
        atkPanel.setPreferredSize(new Dimension(240, 50));
        atkPanel.setBackground(atkGreen);
        atkPanel.setLayout(new BoxLayout(atkPanel, BoxLayout.PAGE_AXIS));

        atkLabel = new JLabel("Attack: " + 0);
        atkLabel.setFont(this.chiruFont);
        // atkPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        atkPanel.add(atkLabel);


        atkPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        JPanel atkButtonPanel = new JPanel();
        atkPanel.add(atkButtonPanel);
        atkButtonPanel.setBackground(atkGreen);
        atkButtonPanel.setLayout(new BoxLayout(atkButtonPanel, BoxLayout.LINE_AXIS));
        atkButtonPanel.add(Box.createRigidArea(new Dimension(20, 10)));

        atkButtonIncrease = new JButton("+1");
        atkButtonIncrease.setFont(this.chiruFont);
        atkButtonPanel.add(atkButtonIncrease);
        AttackIncreaseListener atkIListener = new AttackIncreaseListener();
        atkButtonIncrease.addActionListener(atkIListener);

        atkButtonDecrease = new JButton("-1");
        atkButtonDecrease.setFont(this.chiruFont);
        atkButtonPanel.add(atkButtonDecrease);
        AttackDecreaseListener atkDListener = new AttackDecreaseListener();
        atkButtonDecrease.addActionListener(atkDListener);

        atkPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        // Health aspect
        JPanel healthPanel = new JPanel();
        statsPanel.add(healthPanel);
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.PAGE_AXIS));
        healthPanel.setPreferredSize(new Dimension(250, 50));
        healthPanel.setBackground(healthRed);

        healthLabel = new JLabel("Health: " + 0);
        healthLabel.setFont(this.chiruFont);
        healthPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        healthPanel.add(healthLabel);

        JPanel healthButtonPanel = new JPanel();
        healthPanel.add(healthButtonPanel);
        healthButtonPanel.setBackground(Color.red);
        healthButtonPanel.setLayout(new BoxLayout(healthButtonPanel, BoxLayout.LINE_AXIS));
        healthButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        healthButtonIncrease = new JButton("+1");
        healthButtonIncrease.setFont(this.chiruFont);
        healthButtonPanel.add(healthButtonIncrease);
        HealthIncreaseListener healthIListener = new HealthIncreaseListener();
        healthButtonIncrease.addActionListener(healthIListener);

        healthButtonDecrease = new JButton("-1");
        healthButtonDecrease.setFont(this.chiruFont);
        healthButtonPanel.add(healthButtonDecrease);
        HealthDecreaseListener healthDListener = new HealthDecreaseListener();
        healthButtonDecrease.addActionListener(healthDListener);

        statsInfo = new JButton("?");
        statsInfo.setFont(this.chiruFont);
        // statsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        statsPanel.add(statsInfo);
        StatsInfoListener statsInfoListener = new StatsInfoListener();
        statsInfo.addActionListener(statsInfoListener);

        // Gambling aspect
        JPanel gamblePanel = new JPanel();
        add(gamblePanel, BorderLayout.LINE_END);
        gamblePanel.setPreferredSize(new Dimension(300, 100));
        gamblePanel.setLayout(new BoxLayout(gamblePanel, BoxLayout.PAGE_AXIS));
        gamblePanel.setBackground(chiikawaPink);

        // gambleLabel = new JLabel("Press the button to gamble!");
        // gambleLabel.setFont(this.chiruFont);
        // gamblePanel.add(gambleLabel, BorderLayout.PAGE_START);
        gamblePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        gambleText = new JTextArea("Press the button to gamble!");
        gambleText.setFont(this.chiruFont);
        gambleText.setLineWrap(true);
        gambleText.setWrapStyleWord(true);
        gambleText.setOpaque(false);
        gamblePanel.add(gambleText);

        //gamblePanel.add(Box.createRigidArea(new Dimension(10, 10)));

        gambleButton = new JButton("Gamble!(costs 50 cakes)");
        gambleButton.setFont(this.chiruFont);
        gamblePanel.add(gambleButton, Component.CENTER_ALIGNMENT);
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

        bossInfo = new JButton("?");
        bossPanel.add(bossInfo);
        BossInfoListener bossInfoListener = new BossInfoListener();
        bossInfo.addActionListener(bossInfoListener);

        fightButton = new JButton("Fight!");
        fightButton.setFont(this.chiruFont);
        bossPanel.add(fightButton);
        FightListener fightListener = new FightListener();
        fightButton.addActionListener(fightListener);

        // Character displayed on the screen
        JPanel rakkoPanel = new JPanel();
        add(rakkoPanel, BorderLayout.CENTER);
        rakkoPanel.setBackground(backgroundColor);
        // rakkoPanel.setLayout(new BorderLayout());
        rakko1 = new JLabel("");
        // firstRakko1Icon = new
        // ImageIcon(getClass().getResource("/rakko-chiikawa.gif"));
        // Image tempRakko1 = firstRakko1Icon.getImage();
        // Image bigTempRakko1 = tempRakko1.getScaledInstance(120, 120,
        // java.awt.Image.SCALE_SMOOTH);
        // Image bigRakko1 = getScaledImage(tempRakko1,50,50);
        // ImageIcon rakko1Icon = new ImageIcon(bigRakko1);

        // rakko1.setIcon(new ImageIcon(new
        // ImageIcon(getClass().getResource("/rakko-chiikawa.gif")).getImage().getScaledInstance(200,
        // 50, Image.SCALE_SMOOTH)));

        rakko1Icon = new ImageIcon(getClass().getResource("/rakko-chiikawa.gif"));
        rakko1.setIcon(rakko1Icon);
        rakkoPanel.add(rakko1);

    }

    public Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public void updateDisplay() {
        cakeLabel.setText("Cake: " + playerStats.getCake());
        atkLabel.setText("Attack: " + playerStats.getAtk());
        healthLabel.setText("Health: " + playerStats.getHealth());
    }

    public void refreshDisplay() {
        checkBossStatus();
        if (playerStats.getHealth() < 0) {
            playerStats.setHealth(0);
        }
    }

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
    }

    public class ClickerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int cake = playerStats.getCake();
            playerStats.setCake(cake += 1);
            updateDisplay();
        }

    }

    public class AttackIncreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int cake = playerStats.getCake();
            int atk = playerStats.getAtk();
            if (cake > 0) {
                playerStats.setAtk(atk += 1);
                playerStats.setCake(cake -= 1);
                updateDisplay();
            }

        }

    }

    public class AttackDecreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int atk = playerStats.getAtk();
            int cake = playerStats.getCake();
            if (atk > 0) {
                playerStats.setAtk(atk -= 1);
                playerStats.setCake(cake += 1);
            }
            updateDisplay();
        }

    }

    public class HealthIncreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int health = playerStats.getHealth();
            int cake = playerStats.getCake();
            if (cake > 0) {
                playerStats.setHealth(health += 1);
                playerStats.setCake(cake -= 1);
            }
            updateDisplay();
        }

    }

    public class HealthDecreaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int health = playerStats.getHealth();
            int cake = playerStats.getCake();
            if (health > 0) {
                playerStats.setHealth(health -= 1);
                playerStats.setCake(cake += 1);
            }
            updateDisplay();
        }

    }

    public class StatsInfoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "Spend 1 cake for a +1 increase in attack or health! \n Decrease your attack or health by 1 for +1 cake!",
                    "How to Change Your Stats",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public class GambleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Random rand = new Random();
            gambleNumber = rand.nextInt(1, 101);
            int cake = playerStats.getCake();
            if (cake >= 50) {
                playerStats.setCake(cake -= 50);
                if (gambleNumber <= 5) {
                    playerStats.setCake(cake += 200);
                    gambleText.setText("You win 200 cakes! Play again?");
                } else if (gambleNumber <= 15) {
                    playerStats.setCake(cake += 100);
                    gambleText.setText("You win 100 cakes! Play again?");
                } else if (gambleNumber <= 40) {
                    playerStats.setCake(cake += 75);
                    gambleText.setText("You win 75 cakes! Play again?");
                } else if (gambleNumber <= 65) {
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

    public class BossListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton chosenButton = (JRadioButton) e.getSource();
            if (chosenButton == boss1) {
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

    public class BossInfoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "Recommended (minimum): 10 Health for Boss 1, 30 Health for Boss 2, 50 Health for Boss 3",
                    "Tips for Fighting",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public class FightListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (playerStats.getHealth() <= 0) {
                JOptionPane.showMessageDialog(null,
                        "You need at least 1 Health to Fight!",
                        "Health Too Low",
                        JOptionPane.PLAIN_MESSAGE);
            } else if (fightBoss1) {
                boss1Panel.checkPlayerHealth();
                boss1Panel.refreshDisplay();
                hpFrame.setHomePageVisibility(false);
                boss1Frame.setBoss1Visibility(true);
                hpFrame.updateFrame();
                boss1Frame.updateFrame();
            } else if (fightBoss2) {
                // boss2Panel.checkPlayerHealth();
                // boss2Panel.refreshDisplay();
                hpFrame.setHomePageVisibility(false);
                boss2Frame.setBoss2Visibility(true);
                hpFrame.updateFrame();
                boss2Frame.updateFrame();
            } else if (fightBoss3) {
                hpFrame.setHomePageVisibility(false);
                // boss3Frame.setBoss2Visibility(true);
                hpFrame.updateFrame();
                // boss3Frame.updateFrame();
            }

        }

    }
}
