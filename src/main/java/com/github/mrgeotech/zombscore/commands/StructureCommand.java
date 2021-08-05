package com.github.mrgeotech.zombscore.commands;

import com.github.mrgeotech.zombscore.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StructureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Inventory inventory = Utils.createGlassInventory(27, "Structures");
        // This is where to add the items that will represent the structures
        ItemStack item = new ItemStack(Material.STONE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Wall");
        // TODO: Add lore that displays how many more they can place using PlayerData
        item.setItemMeta(meta);
        inventory.setItem(9, item);
        ((Player) sender).openInventory(inventory);
        return true;
    }

}
