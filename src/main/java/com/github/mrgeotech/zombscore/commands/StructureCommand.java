package com.github.mrgeotech.zombscore.commands;

import com.github.mrgeotech.zombscore.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StructureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Inventory inventory = Utils.createGlassInventory(27, "Structures");
        ItemStack item = new ItemStack(Material.STONE);
        inventory.setItem(9, item);
        ((Player) sender).openInventory(inventory);
        return true;
    }

}
