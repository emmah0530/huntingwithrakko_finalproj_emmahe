package edu.guilford;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HuntingWithRakko {
    public static void main(String[] args) {
        JFrame homePageWindow = new JFrame("Home");
        homePageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel primary = new JPanel();
        homePageWindow.getContentPane().add(primary);


        HomePage homePage = new HomePage();
        primary.add(homePage);


        homePageWindow.pack();
        homePageWindow.setVisible(true);
    }
}