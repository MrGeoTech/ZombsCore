package com.github.mrgeotech.zombscore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Inventory createGlassInventory(int size, String name) {
        // Creating the inventory for the player
        Inventory inventory = Bukkit.createInventory(null, size, name);
        // Adding items to the inventory
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.RESET.toString());
        meta.setLore(lore);
        item.setItemMeta(meta);
        // The background glass
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, item);
        }
        return inventory;
    }

    public static boolean sendAndQuit(CommandSender sender, String text) {
        // A way to make sending a message to a player and returning true easier
        sender.sendMessage(ChatColor.RED + text);
        return true;
    }

}
