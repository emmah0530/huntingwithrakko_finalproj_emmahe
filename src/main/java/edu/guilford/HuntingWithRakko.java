package edu.guilford;

public class HuntingWithRakko {
    public static void main(String[] args) {
        // Creates home page frame and panel
        HomePageFrame homePageWindow = new HomePageFrame("Hunting With Rakko!");
        homePageWindow.setDefaultCloseOperation(HomePageFrame.EXIT_ON_CLOSE);
        HomePagePanel homePage = new HomePagePanel();
        homePageWindow.add(homePage);
        homePageWindow.pack();

        // Creates boss 1 frame and panel
        Boss1Frame boss1Window = new Boss1Frame("Fight the Boss!");
        boss1Window.setDefaultCloseOperation(Boss1Frame.EXIT_ON_CLOSE);
        Boss1Panel boss1Page = new Boss1Panel();
        boss1Window.add(boss1Page);
        boss1Window.pack();

        // Creates boss 2 frame and panel
        Boss2Frame boss2Window = new Boss2Frame("Fight the Boss!");
        boss2Window.setDefaultCloseOperation(Boss2Frame.EXIT_ON_CLOSE);
        Boss2Panel boss2Page = new Boss2Panel();
        boss2Window.add(boss2Page);
        boss2Window.pack();

        // Creates boss 3 frame and panel
        Boss3Frame boss3Window = new Boss3Frame("Fight the Boss!");
        boss3Window.setDefaultCloseOperation(Boss3Frame.EXIT_ON_CLOSE);
        Boss3Panel boss3Page = new Boss3Panel();
        boss3Window.add(boss3Page);
        boss3Window.pack();

        // Creates an instance of the PlayerStats class
        PlayerStats playerStats = new PlayerStats();

        // Connects all the panels and frames together appropriately
        homePage.setBoss1Panel(boss1Page);
        homePage.setBoss1Frame(boss1Window);
        homePage.setBoss2Panel(boss2Page);
        homePage.setBoss2Frame(boss2Window);
        homePage.setBoss3Panel(boss3Page);
        homePage.setBoss3Frame(boss3Window);
        homePage.setHpFrame(homePageWindow);
        homePage.setPlayerStats(playerStats);        
        
        boss1Page.setHpPanel(homePage);
        boss1Page.setHpFrame(homePageWindow);
        boss1Page.setBoss1Frame(boss1Window);
        boss1Page.setPlayerStats(playerStats);
        boss1Page.setBoss2Panel(boss2Page);
        boss1Page.setBoss3Panel(boss3Page);

        boss2Page.setHpPanel(homePage);
        boss2Page.setHpFrame(homePageWindow);
        boss2Page.setBoss2Frame(boss2Window);
        boss2Page.setPlayerStats(playerStats);
        boss2Page.setBoss1Panel(boss1Page);
        boss2Page.setBoss3Panel(boss3Page);

        boss3Page.setHpPanel(homePage);
        boss3Page.setHpFrame(homePageWindow);
        boss3Page.setBoss3Frame(boss3Window);
        boss3Page.setPlayerStats(playerStats);
        boss3Page.setBoss1Panel(boss1Page);
        boss3Page.setBoss2Panel(boss2Page);
    }
}