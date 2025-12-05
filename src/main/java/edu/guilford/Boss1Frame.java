package edu.guilford;


import javax.swing.JFrame;

public class Boss1Frame extends JFrame{
    private boolean boss1Visibility;

    public Boss1Frame(String title) {
        super(title);
        setVisible(false);
    }

    public boolean isBoss1Visibility() {
        return boss1Visibility;
    }

    public void setBoss1Visibility(boolean fightPageVisibility) {
        this.boss1Visibility = fightPageVisibility;
    }

    // method used to open and close frame
    public void updateFrame() {
        if (boss1Visibility) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }
}
