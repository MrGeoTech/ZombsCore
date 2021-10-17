package com.github.mrgeotech.zombscore.customblocks;

import com.github.mrgeotech.zombscore.PlayerData;
import org.bukkit.Bukkit;

public class GoldMiner implements Runnable {

    private Structure structure;
    private int id;

    public GoldMiner(Structure structure) {
        this.structure = structure;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(1);
        if (StructureManager.getStructures().contains(this)) {
            PlayerData.addGold(structure.getOwner(), 10 * (structure.getLevel() + 1));
        } else {
            Bukkit.getScheduler().cancelTask(id);
        }
    }

}
