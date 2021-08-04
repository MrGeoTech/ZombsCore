package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * The manager for all custom blocks/structures.
 * It has a list of all block locations for testing for block location purposes.
 */
public class CustomBlocks {

    private List<CustomBlockHandler> customBlockHandlers;
    private static List<Location> locations;

    public CustomBlocks() {
        customBlockHandlers = new ArrayList<>();
        locations = new ArrayList<>();
    }

    public static void addLocations(Location... locations) {
        CustomBlocks.locations.addAll(Arrays.asList(locations));
    }

}
