package com.github.mrgeotech.zombscore.customblocks;

import com.github.mrgeotech.zombscore.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureManager {

    private static List<Structure> structures;
    private static Map<String, Cost> costMap;

    public static void init() {
        structures = new ArrayList<>();
        costMap = new HashMap<>();
        costMap.put("0:0", new Cost(2, 2, 0));
        costMap.put("0:1", new Cost(4, 4, 5));
        costMap.put("0:2", new Cost(0, 0, 20));
        costMap.put("0:3", new Cost(10, 10, 50));
        costMap.put("0:4", new Cost(15, 15, 100));
        costMap.put("0:5", new Cost(20, 20, 200));
    }

    public static void createStructure(List<Location> locations, Player player, int id) {
        structures.add(new Structure(locations, player, id));
    }

    public static boolean clickingAtStructure(Location location, Player player) {
        for (Structure structure : structures) {
            if (structure.contains(location) && structure.isOwnedBy(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean deleteStructure(Location location) {
        for (Structure structure : structures) {
            if (structure.deleteIfContains(location)) return true;
        }
        return false;
    }

    public static void deleteAllStructures() {
        for (Structure structure : structures) {
            structure.deleteStructure();
        }
    }

    public static void deleteAllStructures(Player player) {
        for (Structure structure : structures) {
            structure.deleteIfOwnedBy(player);
        }
    }

    public static void createWall(Location origin, Player player) {
        if (!PlayerData.hasAnotherStructure(player, 0)) {
            player.sendMessage(ChatColor.RED + "You do not have any more walls!");
            return;
        }
        List<Location> tempLoc = new ArrayList<>();
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        createStructure(tempLoc, player, 0);
    }

    public static void upgradeStructureAt(Location location) {
        for (Structure structure : structures) {
            if (structure.contains(location)) {
                Cost cost = costMap.get(structure.getType() + ":" + structure.getLevel());
                Player player = structure.getOwner();
                if (cost.getWood() < PlayerData.getWood(player) && cost.getStone() < PlayerData.getStone(player) && cost.getGold() < PlayerData.getGold(player)) {
                    structure.upgrade();
                    PlayerData.removeCost(player, cost);
                }
                return;
            }
        }
    }

}
