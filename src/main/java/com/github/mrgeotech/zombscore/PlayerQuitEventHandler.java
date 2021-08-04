package com.github.mrgeotech.zombscore;

import org.bukkit.entity.Player;

public class PlayerQuitEventHandler implements Runnable {

    private Player player;

    @Override
    public void run() {
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            PlayerData.removePlayer(player);
        }
        PlayerData.removePlayer(player);
    }

    public PlayerQuitEventHandler setPlayer(Player player) {
        this.player = player;
        return this;
    }

}
