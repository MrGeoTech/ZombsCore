package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WallBlockHandler implements CustomBlockHandler {

    private ItemStack invetoryItem;
    private List<Structure> structures;

    public WallBlockHandler() {
        structures = new ArrayList<>();
        invetoryItem = new ItemStack(Material.STONE);
    }

    @Override
    public void addLocation(Location origin) {
        // Creating a new wall
        Map<Location,Material> locations = new HashMap<>();
        locations.put(origin.add(0, 1, 0), Material.STONE);
        locations.put(origin.add(0, 2, 0), Material.STONE);
        structures.add(new Structure(origin.toString(), "wall", locations));
    }

    @Override
    public ItemStack getInventoryItem() {
        return invetoryItem;
    }

    @Override
    public void setItemStack(ItemStack item) {
        this.invetoryItem = item;
    }

}
