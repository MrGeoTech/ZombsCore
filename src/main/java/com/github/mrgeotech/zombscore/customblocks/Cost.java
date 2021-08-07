package com.github.mrgeotech.zombscore.customblocks;

public class Cost {

    private int wood;
    private int stone;
    private int gold;

    public Cost(int wood, int stone, int gold) {
        this.wood = wood;
        this.stone = stone;
        this.gold = gold;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getGold() {
        return gold;
    }

}