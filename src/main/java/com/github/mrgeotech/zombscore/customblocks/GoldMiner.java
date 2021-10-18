package com.github.mrgeotech.zombscore.customblocks;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.scoreboard.ScoreboardBuilder;
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
        if (StructureManager.getStructures().contains(structure)) {
            PlayerData.addGold(structure.getOwner(), 10 * (structure.getLevel() + 1));
            ScoreboardBuilder.updateScoreboard(structure.getOwner());
        } else {
            Bukkit.getScheduler().cancelTask(id);
        }
    }

}
