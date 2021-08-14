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

    public Cost toSellPrice() {
        return new Cost((int)(wood * .75), (int)(stone * .75), (int)(gold * .75));
    }

    public boolean canRemove(Cost cost) {
        return (this.wood >= cost.getWood() && this.stone >= cost.getStone() && this.gold >= cost.getGold());
    }

}
