package edu.guilford;

public class HuntingWithRakko {
    public static void main(String[] args) {
        HomePageFrame homePageWindow = new HomePageFrame("Hunting With Rakko!");
        homePageWindow.setDefaultCloseOperation(HomePageFrame.EXIT_ON_CLOSE);
        HomePagePanel homePage = new HomePagePanel();
        homePageWindow.add(homePage);
        homePageWindow.pack();


        Boss1Frame boss1Window = new Boss1Frame("Fight the Boss!");
        boss1Window.setDefaultCloseOperation(Boss1Frame.EXIT_ON_CLOSE);
        Boss1Panel boss1Page = new Boss1Panel();
        boss1Window.add(boss1Page);
        boss1Window.pack();


        Boss2Frame boss2Window = new Boss2Frame("Fight the Boss!");
        boss2Window.setDefaultCloseOperation(Boss2Frame.EXIT_ON_CLOSE);
        Boss2Panel boss2Page = new Boss2Panel();
        boss2Window.add(boss2Page);
        boss2Window.pack();


        PlayerStats playerStats = new PlayerStats();

        homePage.setBoss1Panel(boss1Page);
        homePage.setBoss1Frame(boss1Window);
        homePage.setBoss2Panel(boss2Page);
        homePage.setBoss2Frame(boss2Window);
        homePage.setHpFrame(homePageWindow);
        homePage.setPlayerStats(playerStats);        
        
        boss1Page.setHpPanel(homePage);
        boss1Page.setHpFrame(homePageWindow);
        boss1Page.setBoss1Frame(boss1Window);
        boss1Page.setPlayerStats(playerStats);
        

        boss2Page.setHpPanel(homePage);
        boss2Page.setHpFrame(homePageWindow);
        boss2Page.setBoss2Frame(boss2Window);
        boss2Page.setPlayerStats(playerStats);
    }
}