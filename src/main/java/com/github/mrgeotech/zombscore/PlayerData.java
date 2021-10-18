package com.github.mrgeotech.zombscore;

import com.github.mrgeotech.zombscore.utils.Cost;
import com.github.mrgeotech.zombscore.customblocks.StructureManager;
import com.github.mrgeotech.zombscore.scoreboard.ScoreboardBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    private static Map<Player,PlayerDataStorage> playerData;

    public static void init() {
        playerData = new HashMap<>();
    }

    public static void addPlayer(Player player) {
        playerData.put(player, new PlayerDataStorage());
        ScoreboardBuilder.setScoreboard(player);
    }

    public static boolean containsPlayer(Player player) {
        return playerData.containsKey(player);
    }

    public static void removePlayer(Player player) {
        playerData.remove(player);
        StructureManager.deleteAllStructures(player);
    }

    public static void addWood(Player player) {
        playerData.get(player).addToWood(1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addWood(Player player, int i) {
        playerData.get(player).addToWood(i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeWood(Player player) {
        playerData.get(player).addToWood(-1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeWood(Player player, int i) {
        playerData.get(player).addToWood(-i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static int getWood(Player player) {
        return playerData.get(player).getWood();
    }

    public static void addStone(Player player) {
        playerData.get(player).addToStone(1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addStone(Player player, int i) {
        playerData.get(player).addToStone(i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeStone(Player player) {
        playerData.get(player).addToStone(-1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeStone(Player player, int i) {
        playerData.get(player).addToStone(-i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static int getStone(Player player) {
        return playerData.get(player).getStone();
    }

    public static void addFood(Player player) {
        playerData.get(player).addToFood(1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addFood(Player player, int i) {
        playerData.get(player).addToFood(i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeFood(Player player) {
        playerData.get(player).addToFood(-1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeFood(Player player, int i) {
        playerData.get(player).addToFood(-i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static int getFood(Player player) {
        return playerData.get(player).getFood();
    }

    public static int getGold(Player player) {
        return playerData.get(player).getGold();
    }

    public static void addGold(Player player, int i) {
        playerData.get(player).addToGold(i);
    }

    public static void removeGold(Player player, int i) {
        playerData.get(player).addToGold(-i);
    }

    public static void setPlayersLastEvent(Player player) {
        playerData.get(player).setLastEvent();
    }

    public static long getPlayersLastEvent(Player player) {
        return playerData.get(player).getLastEvent();
    }

    public static void addStructure(Player player, short id) {
        playerData.get(player).addStructure(id);
    }

    public static boolean hasAnotherStructure(Player player, short id) {
        return playerData.get(player).getStructuresLeft(id) < 0;
    }

    public static void removeStructure(Player player, short id) {
        playerData.get(player).removeStructure(id);
    }

    public static short getStructuresLeft(Player player, short id) {
        return playerData.get(player).getStructuresLeft(id);
    }

    public static void addCost(Player player, Cost cost) {
        playerData.get(player).addToWood(cost.getWood());
        playerData.get(player).addToStone(cost.getStone());
        playerData.get(player).addToGold(cost.getGold());
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static boolean removeCost(Player player, Cost cost) {
        if (playerData.get(player).canRemove(cost)) {
            playerData.get(player).addToWood(-cost.getWood());
            playerData.get(player).addToStone(-cost.getStone());
            playerData.get(player).addToGold(-cost.getGold());
            ScoreboardBuilder.updateScoreboard(player);
            return true;
        } else {
            return false;
        }
    }

}
