package com.github.mrgeotech.zombscore.structures;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.structures.structures.ArcherTower;
import com.github.mrgeotech.zombscore.structures.structures.BaseHeart;
import com.github.mrgeotech.zombscore.structures.structures.GoldMine;
import com.github.mrgeotech.zombscore.structures.structures.Structure;
import com.github.mrgeotech.zombscore.utils.Cost;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StructureManager {

    private static List<Structure> structures;

    public static void init() {
        structures = new ArrayList<>();
    }

    public static List<Structure> getStructures() {
        return structures;
    }

    public static void createStructure(StructureType type, Location location, UUID owner) {
        switch (type) {
            case BASE_HEART:
                structures.add(new BaseHeart(location, owner));
                break;
            case GOLD_MINE:
                structures.add(new GoldMine(location, owner));
                break;
            case ARCHER_TOWER:
                structures.add(new ArcherTower(location, owner));
                break;
        }
    }

    public static boolean clickingAtStructure(Location location, UUID player) {
        for (Structure structure : structures) {
            if (structure.contains(location) && structure.isOwnedBy(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean deleteStructure(Location location) {
        for (Structure structure : structures) {
            if (structure.contains(location)) {
                structure.delete();
                return true;
            }
        }
        return false;
    }

    public static void deleteAllStructures() {
        for (Structure structure : structures) {
            structure.delete();
        }
    }

    public static void deleteAllStructures(UUID player) {
        for (Structure structure : structures) {
            if (structure.isOwnedBy(player))
                structure.delete();
        }
    }

    public static boolean upgradeStructureAt(Location location, Player player) {
        for (Structure structure : structures) {
            if (structure.contains(location) && structure.isOwnedBy(player.getUniqueId())) {
                if (structure.getLevel() == 7) {
                    if (PlayerData.removeCost(player.getUniqueId(), structure.getCost())) {
                        structure.upgrade();
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough resources!");
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "You have upgraded this structure to the max level!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean sellStructureAt(Location location, UUID owner) {
        for (int i = 0; i < structures.size(); i++) {
            if (structures.get(i).contains(location) && structures.get(i).isOwnedBy(owner)) {
                Structure temp = structures.get(i);
                PlayerData.addCost(temp.getOwner(), temp.getCost().toSellPrice());
                temp.delete();
                structures.remove(i);
                return true;
            }
        }
        return false;
    }

    public static Cost getBaseCost(StructureType type) {
        switch (type) {
            case BASE_HEART:
                return new Cost(25, 25, 0);
            case GOLD_MINE:
                return new Cost(15, 15, 0);
            case ARCHER_TOWER:
                return new Cost(20, 20, 0);
            default:
                return new Cost(0, 0, 0);
        }
    }

}
