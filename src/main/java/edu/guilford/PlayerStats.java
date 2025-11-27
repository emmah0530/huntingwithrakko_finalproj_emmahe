package edu.guilford;

public class PlayerStats {
    private int cake;
    private int atk;
    private int health;

    public int getCake() {
        return cake;
    }

    public void setCake(int cake) {
        this.cake = cake;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public PlayerStats() {
        cake = 0;
        health = 0;
        atk = 0;
    }

    
}
