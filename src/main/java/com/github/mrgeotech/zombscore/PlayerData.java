package com.github.mrgeotech.zombscore;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    private static Map<Player,Integer> playerWood;
    private static Map<Player,Integer> playerStone;
    private static Map<Player,Integer> playerFood;

    public static void init() {
        playerWood = new HashMap<>();
        playerStone = new HashMap<>();
        playerFood = new HashMap<>();
    }

    public static void addWood(Player player) {
        playerWood.put(player, playerWood.get(player) + 1);
    }

    public static void addStone(Player player) {
        playerStone.put(player, playerStone.get(player) + 1);
    }

    public static void addFood(Player player) {
        playerFood.put(player, playerFood.get(player) + 1);
    }

    public static void addWood(Player player, int amount) {
        playerWood.put(player, playerWood.get(player) + amount);
    }

    public static void addStone(Player player, int amount) {
        playerStone.put(player, playerStone.get(player) + amount);
    }

    public static void addFood(Player player, int amount) {
        playerFood.put(player, playerFood.get(player) + amount);
    }

    public static void removeWood(Player player) {
        playerWood.put(player, playerWood.get(player) - 1);
    }

    public static void removeStone(Player player) {
        playerStone.put(player, playerStone.get(player) - 1);
    }

    public static void removeFood(Player player) {
        playerFood.put(player, playerFood.get(player) - 1);
    }

    public static void removeWood(Player player, int amount) {
        playerWood.put(player, playerWood.get(player) - amount);
    }

    public static void removeStone(Player player, int amount) {
        playerStone.put(player, playerStone.get(player) - amount);
    }

    public static void removeFood(Player player, int amount) {
        playerFood.put(player, playerFood.get(player) - amount);
    }

    public static void addPlayer(Player player) {
        playerWood.put(player, 0);
        playerStone.put(player, 0);
        playerFood.put(player, 0);
    }

    public static boolean containsPlayer(Player player) {
        return playerWood.containsKey(player);
    }

    public static void removePlayer(Player player) {
        playerWood.remove(player);
        playerStone.remove(player);
        playerFood.remove(player);
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
