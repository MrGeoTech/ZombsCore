package com.github.mrgeotech.zombscore;

import com.github.mrgeotech.zombscore.scoreboard.ScoreboardBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    private static Map<Player,Integer> playerWood;
    private static Map<Player,Integer> playerStone;
    private static Map<Player,Integer> playerFood;
    private static Map<Player,Long> playerLastEvent;

    public static void init() {
        playerWood = new HashMap<>();
        playerStone = new HashMap<>();
        playerFood = new HashMap<>();
        playerLastEvent = new HashMap<>();
    }

    public static long getPlayersLastEvent(Player player) {
        return playerLastEvent.get(player);
    }

    public static void setPlayersLastEvent(Player player) {
        playerLastEvent.put(player, System.currentTimeMillis());
    }

    public static void addWood(Player player) {
        playerWood.put(player, playerWood.get(player) + 1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addStone(Player player) {
        playerStone.put(player, playerStone.get(player) + 1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addFood(Player player) {
        playerFood.put(player, playerFood.get(player) + 1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addWood(Player player, int amount) {
        playerWood.put(player, playerWood.get(player) + amount);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addStone(Player player, int amount) {
        playerStone.put(player, playerStone.get(player) + amount);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addFood(Player player, int amount) {
        playerFood.put(player, playerFood.get(player) + amount);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeWood(Player player) {
        playerWood.put(player, playerWood.get(player) - 1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeStone(Player player) {
        playerStone.put(player, playerStone.get(player) - 1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeFood(Player player) {
        playerFood.put(player, playerFood.get(player) - 1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeWood(Player player, int amount) {
        playerWood.put(player, playerWood.get(player) - amount);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeStone(Player player, int amount) {
        playerStone.put(player, playerStone.get(player) - amount);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeFood(Player player, int amount) {
        playerFood.put(player, playerFood.get(player) - amount);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addPlayer(Player player) {
        playerWood.put(player, 0);
        playerStone.put(player, 0);
        playerFood.put(player, 0);
        playerLastEvent.put(player, System.currentTimeMillis());
        ScoreboardBuilder.setScoreboard(player);
    }

    public static boolean containsPlayer(Player player) {
        return playerWood.containsKey(player);
    }

    public static void removePlayer(Player player) {
        playerWood.remove(player);
        playerStone.remove(player);
        playerFood.remove(player);
        playerLastEvent.remove(player);
    }

    public static int getWood(Player player) {
        return playerWood.get(player);
    }

    public static int getStone(Player player) {
        return playerStone.get(player);
    }

    public static int getFood(Player player) {
        return playerFood.get(player);
    }

}
