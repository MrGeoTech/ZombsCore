package com.github.mrgeotech.zombscore;

import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;
import com.github.mrgeotech.zombscore.structures.StructureManager;
import com.github.mrgeotech.zombscore.scoreboard.ScoreboardBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    private static Map<UUID, PlayerDataStorage> playerData;

    public static void init() {
        playerData = new HashMap<>();
    }

    public static void addPlayer(UUID player) {
        playerData.put(player, new PlayerDataStorage());
        ScoreboardBuilder.setScoreboard(player);
    }

    public static boolean containsPlayer(UUID player) {
        return playerData.containsKey(player);
    }

    public static void removePlayer(UUID player) {
        playerData.remove(player);
        StructureManager.deleteAllStructures(player);
    }

    public static void addWood(UUID player) {
        playerData.get(player).addToWood(1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addWood(UUID player, int i) {
        playerData.get(player).addToWood(i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeWood(UUID player) {
        playerData.get(player).addToWood(-1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeWood(UUID player, int i) {
        playerData.get(player).addToWood(-i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static int getWood(UUID player) {
        return playerData.get(player).getWood();
    }

    public static void addStone(UUID player) {
        playerData.get(player).addToStone(1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addStone(UUID player, int i) {
        playerData.get(player).addToStone(i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeStone(UUID player) {
        playerData.get(player).addToStone(-1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeStone(UUID player, int i) {
        playerData.get(player).addToStone(-i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static int getStone(UUID player) {
        return playerData.get(player).getStone();
    }

    public static void addFood(UUID player) {
        playerData.get(player).addToFood(1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void addFood(UUID player, int i) {
        playerData.get(player).addToFood(i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeFood(UUID player) {
        playerData.get(player).addToFood(-1);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static void removeFood(UUID player, int i) {
        playerData.get(player).addToFood(-i);
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static int getFood(UUID player) {
        return playerData.get(player).getFood();
    }

    public static int getGold(UUID player) {
        return playerData.get(player).getGold();
    }

    public static void addGold(UUID player, int i) {
        playerData.get(player).addToGold(i);
    }

    public static void removeGold(UUID player, int i) {
        playerData.get(player).addToGold(-i);
    }

    public static void setPlayersLastEvent(UUID player) {
        playerData.get(player).setLastEvent();
    }

    public static long getPlayersLastEvent(UUID player) {
        return playerData.get(player).getLastEvent();
    }

    public static void addStructure(UUID player, StructureType id) {
        playerData.get(player).addStructure(id);
    }

    public static boolean hasAnotherStructure(UUID player, StructureType id) {
        return playerData.get(player).getStructuresLeft(id) < 0;
    }

    public static void removeStructure(UUID player, StructureType id) {
        playerData.get(player).removeStructure(id);
    }

    public static short getStructuresLeft(UUID player, StructureType id) {
        return playerData.get(player).getStructuresLeft(id);
    }

    public static void addCost(UUID player, Cost cost) {
        playerData.get(player).addToWood(cost.getWood());
        playerData.get(player).addToStone(cost.getStone());
        playerData.get(player).addToGold(cost.getGold());
        ScoreboardBuilder.updateScoreboard(player);
    }

    public static boolean removeCost(UUID player, Cost cost) {
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
