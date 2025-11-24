package edu.guilford;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class FightPageFrame extends JFrame{
    private boolean fightPageVisibility;

    public FightPageFrame(String title) throws HeadlessException {
        super(title);
        setVisible(false);
    }

    public boolean isFightPageVisibility() {
        return fightPageVisibility;
    }

    public void setFightPageVisibility(boolean fightPageVisibility) {
        this.fightPageVisibility = fightPageVisibility;
    }

    public void updateFrame() {
        if (fightPageVisibility) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }
}
