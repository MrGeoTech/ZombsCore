package com.github.mrgeotech.zombscore.commands;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerDataCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("admin.playerdata")) return Utils.sendAndQuit(sender, ChatColor.RED + "Sorry but you don't have permission to execute this command!");
        if (args.length != 1) return Utils.sendAndQuit(sender, ChatColor.RED + "You must specify a player!");
        Player player = Bukkit.getPlayer(args[0]);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6---Player Data for " + player.getDisplayName() + "&6---\n" +
                "&6- Wood: " + PlayerData.getWood(player) + "\n" +
                "&6- Stone: " + PlayerData.getStone(player) + "\n" +
                "&6- Food: " + PlayerData.getFood(player)));
        return true;
    }

}
