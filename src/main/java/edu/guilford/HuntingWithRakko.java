package edu.guilford;

public class HuntingWithRakko {
    public static void main(String[] args) {
        HomePageFrame homePageWindow = new HomePageFrame("Hunting With Rakko!");
        homePageWindow.setDefaultCloseOperation(HomePageFrame.EXIT_ON_CLOSE);

        HomePagePanel homePage = new HomePagePanel();
        homePageWindow.add(homePage);

        homePageWindow.pack();

        FightPageFrame fightPageWindow = new FightPageFrame("Fight the Boss!");
        fightPageWindow.setDefaultCloseOperation(FightPageFrame.EXIT_ON_CLOSE);

        Boss1Panel boss1Page = new Boss1Panel();
        fightPageWindow.add(boss1Page);

        fightPageWindow.pack();

        PlayerStats playerStats = new PlayerStats();

        homePage.setBoss1Panel(boss1Page);
        homePage.setFpFrame(fightPageWindow);
        homePage.setHpFrame(homePageWindow);
        homePage.setPlayerStats(playerStats);        
        
        boss1Page.setHpPanel(homePage);
        boss1Page.setHpFrame(homePageWindow);
        boss1Page.setFpFrame(fightPageWindow);
        boss1Page.setPlayerStats(playerStats);
        
    }
}