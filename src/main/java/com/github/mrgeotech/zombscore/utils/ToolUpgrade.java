package com.github.mrgeotech.zombscore.utils;

import org.bukkit.inventory.ItemStack;

public class ToolUpgrade {

    ItemStack item;
    Cost cost;

    public ToolUpgrade(ItemStack item, Cost cost) {
        this.item = item;
        this.cost = cost;
    }

    public ItemStack getItem() {
        return item;
    }

    public Cost getCost() {
        return cost;
    }

}
