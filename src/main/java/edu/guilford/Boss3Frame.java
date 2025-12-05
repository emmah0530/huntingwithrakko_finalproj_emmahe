package edu.guilford;

import javax.swing.JFrame;

public class Boss3Frame extends JFrame{
    private boolean boss3Visibility;

    public Boss3Frame(String title) {
        super(title);
        setVisible(false);
    }

    public boolean isBoss3Visibility() {
        return boss3Visibility;
    }

    public void setBoss3Visibility(boolean fightPageVisibility) {
        this.boss3Visibility = fightPageVisibility;
    }

    // method used to open and close frame
    public void updateFrame() {
        if (boss3Visibility) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }
}
