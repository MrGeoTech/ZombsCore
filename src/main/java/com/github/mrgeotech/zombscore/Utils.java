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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]){6}");

    public static String color(String message){
        Matcher matcher = hexPattern.matcher(message);
        while (matcher.find()) {
            final net.md_5.bungee.api.ChatColor hexColor = net.md_5.bungee.api.ChatColor.of(matcher.group().substring(1, matcher.group().length()));
            final String before = message.substring(0, matcher.start());
            final String after = message.substring(matcher.end());
            message = before + hexColor + after;
            matcher = hexPattern.matcher(message);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message);
    }

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
