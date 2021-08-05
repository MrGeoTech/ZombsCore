package com.github.mrgeotech.zombscore;

import com.github.mrgeotech.zombscore.commands.PlayerDataCommand;
import com.github.mrgeotech.zombscore.commands.StructureCommand;
import com.github.mrgeotech.zombscore.commands.UpgradeCommand;
import com.github.mrgeotech.zombscore.customblocks.StructureManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class ZombsCore extends JavaPlugin implements Listener {

    UpgradeCommand upgradeCommand;
    List<ItemStack> basicInventory;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &fCreating items..."));
        upgradeCommand = new UpgradeCommand();

        StructureManager.init();
        basicInventory = new ArrayList<>();
        basicInventory.add(upgradeCommand.getSwordUpgrades().get("wood"));
        basicInventory.add(upgradeCommand.getAxeUpgrades().get("wood"));
        basicInventory.add(upgradeCommand.getPickaxeUpgrades().get("wood"));
        ItemStack item = new ItemStack(Material.BELL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Structures");
        item.setItemMeta(meta);
        basicInventory.add(item);
        item = new ItemStack(Material.SWEET_BERRIES, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Eat");
        item.setItemMeta(meta);
        basicInventory.add(item);
        item = new ItemStack(Material.NETHER_STAR, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Upgrades");
        item.setItemMeta(meta);
        basicInventory.add(item);

        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &fRegistering items..."));
        // Registering commands
        this.getCommand("upgrades").setExecutor(upgradeCommand);
        this.getCommand("playerdata").setExecutor(new PlayerDataCommand());
        this.getCommand("structures").setExecutor(new StructureCommand());
        // Registering events
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(upgradeCommand, this);

        PlayerData.init();
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &aComplete!"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &cDisabling..."));
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("ZombsCore");
    }

    @EventHandler
    public void onDamageEvent(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onCraftEvent(CraftItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Adding player to player data
        if (!PlayerData.containsPlayer(player)) PlayerData.addPlayer(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
        // Setting up player inventory
        player.getInventory().clear();
        player.getInventory().setItem(0, basicInventory.get(0));
        player.getInventory().setItem(1, basicInventory.get(1));
        player.getInventory().setItem(2, basicInventory.get(2));
        player.getInventory().setItem(3, basicInventory.get(3));
        player.getInventory().setItem(7, basicInventory.get(4));
        player.getInventory().setItem(8, basicInventory.get(5));
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.hasItem())) return;
        ItemStack item = event.getItem();
        if (item.getType().equals(Material.STONE)) {
            event.setCancelled(true);
            StructureManager.createWall(event.getClickedBlock().getLocation());
            event.getPlayer().getInventory().setItem(3, basicInventory.get(3));
        } else if (item.getType().equals(Material.SWEET_BERRIES)) {
            Player player = event.getPlayer();
            if (player.getFoodLevel() < 20) {
                PlayerData.removeFood(player);
                player.setFoodLevel(player.getFoodLevel() + 1);
                player.sendMessage(ChatColor.GREEN + "You have eaten!");
            } else {
                player.sendMessage(ChatColor.RED + "Your food bar is full!");
            }
        } else if (item.getType().equals(Material.NETHER_STAR)) {
            event.getPlayer().performCommand("updates");
        } else if (item.getType().equals(Material.BELL)) {
            event.getPlayer().performCommand("structures");
        }
        // TODO: Add in custom blocks
    }

    @EventHandler
    public void onPickupArrow(PlayerPickupArrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        // Creates a thread that will wait 5 mins and then deleted the player data
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                PlayerData.removePlayer(player);
            }
            PlayerData.removePlayer(player);
        });
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
        // Adding the data to players' data
        Block block = event.getBlock();
        System.out.println(block.getLocation());
        if (block.getType().equals(Material.OAK_LOG)) {
            PlayerData.addWood(event.getPlayer());
        } else if (block.getType().equals(Material.STONE)) {
            PlayerData.addStone(event.getPlayer());
        } else if (block.getType().equals(Material.SWEET_BERRY_BUSH)) {
            PlayerData.addFood(event.getPlayer());
        } else {
            StructureManager.deleteStructure(block.getLocation());
        }
    }

}
