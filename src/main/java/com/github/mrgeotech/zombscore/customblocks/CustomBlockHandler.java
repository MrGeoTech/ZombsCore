package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface CustomBlockHandler {
    public void addLocation(Location origin);
    public ItemStack getInventoryItem();
    public void setItemStack(ItemStack item);
}
