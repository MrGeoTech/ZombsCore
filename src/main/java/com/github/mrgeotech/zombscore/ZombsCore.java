package com.github.mrgeotech.zombscore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Level;

public final class ZombsCore extends JavaPlugin implements Listener {

    UpgradeCommand upgradeCommand;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) Bukkit.getPluginManager().disablePlugin(this);
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &fCreating items..."));
        upgradeCommand = new UpgradeCommand();
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &fRegistering items..."));
        this.getCommand("upgrades").setExecutor(upgradeCommand);
        this.getCommand("playerdata").setExecutor(new PlayerDataCommand());
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
        if (!PlayerData.containsPlayer(player)) PlayerData.addPlayer(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
        player.getInventory().clear();
        player.getInventory().setItem(0, upgradeCommand.getSwordUpgrades().get("wood"));
        player.getInventory().setItem(1, upgradeCommand.getAxeUpgrades().get("wood"));
        player.getInventory().setItem(2, upgradeCommand.getPickaxeUpgrades().get("wood"));
        ItemStack item = new ItemStack(Material.SWEET_BERRIES, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Eat");
        item.setItemMeta(meta);
        player.getInventory().setItem(7, item);
        item = new ItemStack(Material.NETHER_STAR, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Upgrades");
        item.setItemMeta(meta);
        player.getInventory().setItem(8, item);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent event) {
        if (!event.getItemInHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "can-be-placed"), PersistentDataType.SHORT)) {
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        event.setCancelled(true);
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
        Bukkit.getScheduler().runTaskAsynchronously(this, new PlayerQuitEventHandler().setPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
        event.getPlayer().sendMessage("" + event.getBlock().getType().toString());
        if (event.getBlock().getType().equals(Material.OAK_LOG)) {
            PlayerData.addWood(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.STONE)) {
            PlayerData.addStone(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.SWEET_BERRY_BUSH)) {
            PlayerData.addFood(event.getPlayer());
        }
    }

}
