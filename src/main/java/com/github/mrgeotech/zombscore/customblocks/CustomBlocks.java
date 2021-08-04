package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CustomBlocks {

    private Map<ItemStack,CustomBlockHandler> customBlockHandlers;
    private static List<Location> locations;

    public CustomBlocks() {
        customBlockHandlers = new HashMap<>();
        locations = new ArrayList<>();
    }

    public static void addLocations(Location... locations) {
        CustomBlocks.locations.addAll(Arrays.asList(locations));
    }

}
