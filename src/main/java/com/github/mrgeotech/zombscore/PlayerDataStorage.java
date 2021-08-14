package com.github.mrgeotech.zombscore;

import com.github.mrgeotech.zombscore.utils.Cost;

public class PlayerDataStorage {

    private int wood;
    private int stone;
    private int food;
    private int gold;
    private long lastEvent;
    private int wallsLeft;

    public PlayerDataStorage() {
        wood = 0;
        stone = 0;
        food = 0;
        lastEvent = System.currentTimeMillis();
        wallsLeft = 100;
        gold = 100000;
    }

    public void addToWood(int i) {
        wood += i;
    }

    public void addToStone(int i) {
        stone += i;
    }

    public void addToFood(int i) {
        food += i;
    }

    public void addToGold(int i) {
        gold += i;
    }

    public void setLastEvent() {
        lastEvent = System.currentTimeMillis();
    }

    public void addToWalls() {
        wallsLeft++;
    }

    public void removeFromWalls() {
        wallsLeft--;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public long getLastEvent() {
        return lastEvent;
    }

    public int getWallsLeft() {
        return wallsLeft;
    }

    public boolean canRemove(Cost cost) {
        return (this.wood >= cost.getWood() && this.stone >= cost.getStone() && this.gold >= cost.getGold());
    }
}
