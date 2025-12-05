package edu.guilford;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class HomePageFrame extends JFrame {
    private boolean homePageVisibility;

    public HomePageFrame(String title) throws HeadlessException {
        super(title);
        setVisible(true);
    }

    public boolean isHomePageVisibility() {
        return homePageVisibility;
    }

    public void setHomePageVisibility(boolean homePageVisibility) {
        this.homePageVisibility = homePageVisibility;
    }

    // method used to open and close frame
    public void updateFrame() {
        if (homePageVisibility) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }
    
}
