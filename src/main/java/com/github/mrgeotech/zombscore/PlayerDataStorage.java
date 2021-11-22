package com.github.mrgeotech.zombscore;

import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataStorage {

    private int wood;
    private int stone;
    private int food;
    private int gold;
    private long lastEvent;
    private Map<StructureType, Short> structuresLeft;

    public PlayerDataStorage() {
        wood = 100;
        stone = 100;
        food = 100;
        lastEvent = System.currentTimeMillis();
        structuresLeft = new HashMap<>();
        structuresLeft.put(StructureType.BASE_HEART, (short) 1);
        structuresLeft.put(StructureType.GOLD_MINE, (short) 8);
        structuresLeft.put(StructureType.ARCHER_TOWER, (short) 6);
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

    public void addStructure(StructureType type) {
        structuresLeft.put(type, (short) (structuresLeft.get(type) + 1));
    }

    public void removeStructure(StructureType type) {
        structuresLeft.put(type, (short) (structuresLeft.get(type) - 1));
    }

    public short getStructuresLeft(StructureType type) {
        return structuresLeft.get(type);
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

    public boolean canRemove(Cost cost) {
        return (this.wood >= cost.getWood() && this.stone >= cost.getStone() && this.gold >= cost.getGold());
    }
}
