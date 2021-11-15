package com.github.mrgeotech.zombscore.commands;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.Utils;
import com.github.mrgeotech.zombscore.structures.StructureManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StructureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Inventory inventory = Utils.createGlassInventory(27, "Structures");
        // This is where to add the items that will represent the structures
        ItemStack item = new ItemStack(Material.STONE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Wall");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "You have " + ChatColor.GOLD  + "" + PlayerData.getStructuresLeft(((Player) sender), (short) 0) + "" + ChatColor.DARK_GRAY + " left!");
        lore.add(ChatColor.DARK_GRAY + "Wood:" + StructureManager.getBaseCost((short) 0).getWood() + " Stone:" + StructureManager.getBaseCost((short) 0).getStone() + " Gold:" + StructureManager.getBaseCost((short) 0).getGold());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(9, item);
        item = new ItemStack(Material.REDSTONE_BLOCK);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Base Heart");
        lore.clear();
        lore.add(ChatColor.DARK_GRAY + "You have " + ChatColor.GOLD  + "" + PlayerData.getStructuresLeft(((Player) sender), (short) 1) + "" + ChatColor.DARK_GRAY + " left!");
        lore.add(ChatColor.DARK_GRAY + "Wood:" + StructureManager.getBaseCost((short) 1).getWood() + " Stone:" + StructureManager.getBaseCost((short) 1).getStone() + " Gold:" + StructureManager.getBaseCost((short) 1).getGold());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(10, item);
        item = new ItemStack(Material.GOLD_BLOCK);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Gold Mine");
        lore.clear();
        lore.add(ChatColor.DARK_GRAY + "You have " + ChatColor.GOLD  + "" + PlayerData.getStructuresLeft(((Player) sender), (short) 2) + "" + ChatColor.DARK_GRAY + " left!");
        lore.add(ChatColor.DARK_GRAY + "Wood:" + StructureManager.getBaseCost((short) 2).getWood() + " Stone:" + StructureManager.getBaseCost((short) 2).getStone() + " Gold:" + StructureManager.getBaseCost((short) 2).getGold());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(11, item);
        ((Player) sender).openInventory(inventory);
        return true;
    }

}
