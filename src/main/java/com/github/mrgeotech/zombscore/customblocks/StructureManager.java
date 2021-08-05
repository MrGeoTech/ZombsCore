package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * The manager for all custom blocks/structures.
 * It has a list of all block locations for testing for block location purposes.
 */
public class StructureManager {

    private static Map<ItemStack, StructureBuilder> builders;
    private static Map<Location, Structure> structures;

    public static void init() {
        builders = new HashMap<>();
        structures = new HashMap<>();
        builders.put(new ItemStack(Material.STONE), new WallBuilder());
    }

    public static boolean isPartOfStructure(Location location) {
        return structures.containsKey(location);
    }

    public static void removeStructure(Location location) {
        structures.get(location).deleteStructure();
        structures.remove(location);
    }

    public static void damageStructure(Location location) {
        structures.get(location).damageStructure();
    }

    public static void addLocation(Location location, Structure structure) {
        structures.put(location, structure);
    }

    public static void createStructure(ItemStack item, Location origin) {
        builders.get(item).addLocation(origin);
    }

}
