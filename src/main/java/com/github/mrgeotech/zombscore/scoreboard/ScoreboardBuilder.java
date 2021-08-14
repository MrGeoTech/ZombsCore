package com.github.mrgeotech.zombscore.scoreboard;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.ZombsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.w3c.dom.css.Counter;

public class ScoreboardBuilder {

    public static void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(player.getName(), "dummy", "Zombs Stats");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score spacer = objective.getScore("                ");
        spacer.setScore(8);

        Team woodCounter = scoreboard.registerNewTeam("woodCounter");
        woodCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        woodCounter.setPrefix(ChatColor.GOLD + "Wood   | " + PlayerData.getWood(player));
        objective.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(7);

        spacer = objective.getScore(ChatColor.GRAY + "                ");
        spacer.setScore(6);

        Team stoneCounter = scoreboard.registerNewTeam("stoneCounter");
        stoneCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        stoneCounter.setPrefix(ChatColor.GOLD + "Stone  | " + PlayerData.getStone(player));
        objective.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(5);

        spacer = objective.getScore(ChatColor.DARK_AQUA + "                ");
        spacer.setScore(4);

        Team foodCounter = scoreboard.registerNewTeam("foodCounter");
        foodCounter.addEntry(ChatColor.BLUE + "" + ChatColor.WHITE);
        foodCounter.setPrefix(ChatColor.GOLD + "Food   | " + PlayerData.getFood(player));
        objective.getScore(ChatColor.BLUE + "" + ChatColor.WHITE).setScore(3);

        spacer = objective.getScore(ChatColor.DARK_RED + "                ");
        spacer.setScore(2);

        Team goldCounter = scoreboard.registerNewTeam("goldCounter");
        goldCounter.addEntry(ChatColor.YELLOW + "" + ChatColor.WHITE);
        goldCounter.setPrefix(ChatColor.GOLD + "Gold   | " + PlayerData.getGold(player));
        objective.getScore(ChatColor.YELLOW + "" + ChatColor.WHITE).setScore(1);

        spacer = objective.getScore(ChatColor.DARK_BLUE + "                ");
        spacer.setScore(0);

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreboard(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        scoreboard.getTeam("woodCounter").setPrefix(ChatColor.GOLD + "Wood   | " + PlayerData.getWood(player));
        scoreboard.getTeam("stoneCounter").setPrefix(ChatColor.GOLD + "Stone  | " + PlayerData.getStone(player));
        scoreboard.getTeam("foodCounter").setPrefix(ChatColor.GOLD + "Food   | " + PlayerData.getFood(player));
        scoreboard.getTeam("goldCounter").setPrefix(ChatColor.GOLD + "Gold   | " + PlayerData.getGold(player));
    }

}
