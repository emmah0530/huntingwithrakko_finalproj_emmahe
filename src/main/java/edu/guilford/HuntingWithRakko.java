package edu.guilford;

public class HuntingWithRakko {
    public static void main(String[] args) {
        HomePageFrame homePageWindow = new HomePageFrame("Hunting With Rakko!");
        homePageWindow.setDefaultCloseOperation(HomePageFrame.EXIT_ON_CLOSE);
        //JPanel primary = new JPanel();
        //homePageWindow.getContentPane().add(primary);


        HomePagePanel homePage = new HomePagePanel();
        homePageWindow.add(homePage);


        homePageWindow.pack();
        //homePageWindow.setVisible(true);

        

        FightPageFrame fightPageWindow = new FightPageFrame("Fight the Boss!");
        fightPageWindow.setDefaultCloseOperation(FightPageFrame.EXIT_ON_CLOSE);
        //JPanel secondary = new JPanel();
        //fightPageWindow.getContentPane().add(secondary);

        FightPagePanel fightPage = new FightPagePanel();
        fightPageWindow.add(fightPage);

        fightPageWindow.pack();
        //fightPageWindow.setVisible(false);

        homePage.setFpPanel(fightPage);
        homePage.setFpFrame(fightPageWindow);
        homePage.setHpFrame(homePageWindow);
        
        fightPage.setHpPanel(homePage);
        fightPage.setHpFrame(homePageWindow);
        fightPage.setFpFrame(fightPageWindow);
        
    }
}