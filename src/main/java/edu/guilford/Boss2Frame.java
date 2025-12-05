package edu.guilford;

import javax.swing.JFrame;

public class Boss2Frame extends JFrame{
    private boolean boss2Visibility;

    public Boss2Frame(String title) {
        super(title);
        setVisible(false);
    }

    public boolean isBoss2Visibility() {
        return boss2Visibility;
    }

    public void setBoss2Visibility(boolean fightPageVisibility) {
        this.boss2Visibility = fightPageVisibility;
    }

    // method used to open and close frame
    public void updateFrame() {
        if (boss2Visibility) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }
}
