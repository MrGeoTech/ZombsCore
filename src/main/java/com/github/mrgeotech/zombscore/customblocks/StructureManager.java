package com.github.mrgeotech.zombscore.customblocks;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.utils.Cost;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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
        // Walls
        costMap.put("0:0", new Cost(2, 2, 0));
        costMap.put("0:1", new Cost(4, 4, 5));
        costMap.put("0:2", new Cost(0, 0, 20));
        costMap.put("0:3", new Cost(10, 10, 50));
        costMap.put("0:4", new Cost(15, 15, 100));
        costMap.put("0:5", new Cost(20, 20, 200));
        // Base heart
        costMap.put("1:0", new Cost(25, 25, 0));
        costMap.put("1:1", new Cost(50, 50, 500));
        costMap.put("1:2", new Cost(75, 75, 1000));
        costMap.put("1:3", new Cost(125, 125, 5000));
        costMap.put("1:4", new Cost(200, 200, 10000));
        costMap.put("1:5", new Cost(500, 500, 50000));
        // Gold Mine
        costMap.put("2:0", new Cost(15, 15, 0));
        costMap.put("2:1", new Cost(30, 30, 250));
        costMap.put("2:2", new Cost(50, 50, 500));
        costMap.put("2:3", new Cost(100, 100, 1000));
        costMap.put("2:4", new Cost(200, 200, 5000));
        costMap.put("2:5", new Cost(300, 300, 10000));
    }

    public static List<Structure> getStructures() {
        return structures;
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
        if (!PlayerData.hasAnotherStructure(player, 1)) {
            player.sendMessage(ChatColor.RED + "You must place a Base Heart to place other structures!");
            return;
        }
        if (!PlayerData.hasAnotherStructure(player, 0)) {
            player.sendMessage(ChatColor.RED + "You do not have any more walls!");
            return;
        }
        if (!PlayerData.removeCost(player, costMap.get("0:0"))) {
            player.sendMessage(ChatColor.RED + "You do not have enough resources!");
            return;
        }
        List<Location> tempLoc = new ArrayList<>();
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        createStructure(tempLoc, player, 0);
    }

    public static void createBaseHeart(Location origin, Player player) {
        if (!PlayerData.hasAnotherStructure(player, 1)) {
            player.sendMessage(ChatColor.RED + "You do not have another Base Heart!");
            return;
        }
        if (!PlayerData.removeCost(player, costMap.get("1:0"))) {
            player.sendMessage(ChatColor.RED + "You do not have enough resources!");
            return;
        }
        List<Location> tempLoc = new ArrayList<>();
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.WEST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH_EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH_WEST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH_EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH_WEST).getLocation());
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.WEST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.UP).getLocation());
        for (int i = 0; i < tempLoc.size(); i++) {
            if (!tempLoc.get(i).getBlock().getType().equals(Material.AIR)) {
                player.sendMessage(ChatColor.RED + "This can not overlap with another structure!");
                PlayerData.addCost(player, costMap.get("1:0"));
                return;
            }
        }
        createStructure(tempLoc, player, 1);
    }

    public static void createGoldMine(Location origin, Player player) {
        if (!PlayerData.hasAnotherStructure(player, 1)) {
            player.sendMessage(ChatColor.RED + "You must place a Base Heart to place other structures!");
            return;
        }
        if (!PlayerData.hasAnotherStructure(player, 0)) {
            player.sendMessage(ChatColor.RED + "You do not have any more gold mines!");
            return;
        }
        if (!PlayerData.removeCost(player, costMap.get("2:0"))) {
            player.sendMessage(ChatColor.RED + "You do not have enough resources!");
            return;
        }
        List<Location> tempLoc = new ArrayList<>();
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.WEST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH_EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH_WEST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH_EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH_WEST).getLocation());
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.WEST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH_EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.NORTH_WEST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH_EAST).getLocation());
        tempLoc.add(origin.getBlock().getRelative(BlockFace.SOUTH_WEST).getLocation());
        createStructure(tempLoc, player, 2);
    }

    public static boolean upgradeStructureAt(Location location, Player player) {
        for (Structure structure : structures) {
            if (structure.contains(location) && structure.isOwnedBy(player)) {
                try {
                    Cost cost = costMap.get(structure.getType() + ":" + (structure.getLevel() + 1));
                    if (PlayerData.removeCost(player, cost)) {
                        structure.upgrade();
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough resources!");
                    }
                    return true;
                } catch (NullPointerException e) {
                    player.sendMessage(ChatColor.RED + "You have upgraded this structure to the max level!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean sellStructureAt(Location location, Player owner) {
        for (int i = 0; i < structures.size(); i++) {
            if (structures.get(i).contains(location) && structures.get(i).isOwnedBy(owner)) {
                Structure temp = structures.get(i);
                PlayerData.addCost(temp.getOwner(), costMap.get(temp.getType() + ":" + temp.getLevel()).toSellPrice());
                temp.deleteStructure();
                structures.remove(i);
                return true;
            }
        }
        return false;
    }

}
