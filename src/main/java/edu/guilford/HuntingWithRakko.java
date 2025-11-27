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

        FightPagePanel fightPage = new FightPagePanel();
        fightPageWindow.add(fightPage);

        fightPageWindow.pack();

        PlayerStats playerStats = new PlayerStats();

        homePage.setFpPanel(fightPage);
        homePage.setFpFrame(fightPageWindow);
        homePage.setHpFrame(homePageWindow);
        homePage.setPlayerStats(playerStats);        
        
        fightPage.setHpPanel(homePage);
        fightPage.setHpFrame(homePageWindow);
        fightPage.setFpFrame(fightPageWindow);
        fightPage.setPlayerStats(playerStats);
        
    }
}