package com.github.mrgeotech.zombscore.commands;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.Utils;
import com.github.mrgeotech.zombscore.structures.StructureManager;
import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;
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
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Base Heart");
        List<String> lore = new ArrayList<>();
        Cost cost = StructureManager.getBaseCost(StructureType.BASE_HEART);
        lore.add(ChatColor.DARK_GRAY + "You have " + ChatColor.GOLD  + "" + PlayerData.getStructuresLeft(((Player) sender).getUniqueId(), StructureType.BASE_HEART) + "" + ChatColor.DARK_GRAY + " left!");
        lore.add(ChatColor.DARK_GRAY + "Wood:" + cost.getWood() + " Stone:" + cost.getStone() + " Gold:" + cost.getGold());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(9, item);
        item = new ItemStack(Material.GOLD_BLOCK);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Gold Mine");
        lore.clear();
        cost = StructureManager.getBaseCost(StructureType.GOLD_MINE);
        lore.add(ChatColor.DARK_GRAY + "You have " + ChatColor.GOLD  + "" + PlayerData.getStructuresLeft(((Player) sender).getUniqueId(), StructureType.GOLD_MINE) + "" + ChatColor.DARK_GRAY + " left!");
        lore.add(ChatColor.DARK_GRAY + "Wood:" + cost.getWood() + " Stone:" + cost.getStone() + " Gold:" + cost.getGold());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(10, item);
        item = new ItemStack(Material.STONE);
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&#80461BArcher Tower"));
        lore.clear();
        cost = StructureManager.getBaseCost(StructureType.ARCHER_TOWER);
        lore.add(ChatColor.DARK_GRAY + "You have " + ChatColor.GOLD  + "" + PlayerData.getStructuresLeft(((Player) sender).getUniqueId(), StructureType.ARCHER_TOWER) + "" + ChatColor.DARK_GRAY + " left!");
        lore.add(ChatColor.DARK_GRAY + "Wood:" + cost.getWood() + " Stone:" + cost.getStone() + " Gold:" + cost.getGold());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(11, item);
        ((Player) sender).openInventory(inventory);
        return true;
    }

}
