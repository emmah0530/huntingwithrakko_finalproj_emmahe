package edu.guilford;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HuntingWithRakko {
    public static void main(String[] args) {
        JFrame homePageWindow = new JFrame("Hunting With Rakko");
        homePageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel primary = new JPanel();
        homePageWindow.getContentPane().add(primary);


        HomePagePanel homePage = new HomePagePanel();
        primary.add(homePage);


        homePageWindow.pack();
        homePageWindow.setVisible(true);
    }
}