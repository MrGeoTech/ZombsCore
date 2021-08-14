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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class ZombsCore extends JavaPlugin implements Listener {

    private UpgradeCommand upgradeCommand;
    private List<ItemStack> basicInventory;
    private boolean isRunning;

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
        this.isRunning = true;
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &aComplete!"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.isRunning = false;
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&8[&3ZombsCore&8] &cDisabling..."));
        StructureManager.deleteAllStructures();
        Bukkit.getScheduler().cancelTasks(this);
    }

    private boolean allEquals(Material mat, Material... mats) {
        for (Material temp : mats) {
            if (mat.equals(temp)) return true;
        }
        return false;
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
        if (event.hasBlock() && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            event.setCancelled(true);
            Material mat = event.getClickedBlock().getType();
            if (mat.equals(Material.OAK_WOOD) || mat.equals(Material.STONE) || mat.equals(Material.IRON_BLOCK) || mat.equals(Material.GOLD_BLOCK) || mat.equals(Material.DIAMOND_BLOCK) || mat.equals(Material.LAPIS_BLOCK) || mat.equals(Material.REDSTONE_BLOCK)) {
                Player player = event.getPlayer();
                if (StructureManager.clickingAtStructure(event.getClickedBlock().getLocation(), player) && player.isSneaking()) {
                    StructureManager.upgradeStructureAt(event.getClickedBlock().getLocation());
                    return;
                }
            }
        }
        if (!event.hasItem() && !(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
        event.setCancelled(true);
        ItemStack item = event.getItem();
        if (item.getType().equals(Material.SWEET_BERRIES)) {
            if (PlayerData.getPlayersLastEvent(event.getPlayer()) < System.currentTimeMillis() - 250) {
                Player player = event.getPlayer();
                if (player.getFoodLevel() < 20) {
                    if (PlayerData.getFood(player) > 0) {
                        PlayerData.removeFood(player);
                        player.setFoodLevel(player.getFoodLevel() + 1);
                        player.sendMessage(ChatColor.GREEN + "You have eaten!");
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough food!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Your food bar is full!");
                }
                PlayerData.setPlayersLastEvent(player);
            }
        } else if (item.getType().equals(Material.NETHER_STAR)) {
            event.getPlayer().performCommand("upgrades");
        } else if (item.getType().equals(Material.BELL)) {
            event.getPlayer().performCommand("structures");
        } else if (event.hasBlock()) {
            if (item.getType().equals(Material.STONE)) {
                if (event.getClickedBlock().getType().equals(Material.GRASS_BLOCK)) {
                    StructureManager.createWall(event.getClickedBlock().getLocation(), event.getPlayer());
                    if (!event.getPlayer().isSneaking())
                        event.getPlayer().getInventory().setItem(3, basicInventory.get(3));
                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "You must place structures on the ground");
                }
            }
        }
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
            boolean interupted = false;
            try {
                Thread.sleep(300000);
            } catch (InterruptedException ignored) {
                interupted = true;
            }
            if (isRunning && !interupted) {
                PlayerData.removePlayer(player);
            } else if (!isRunning) {
                PlayerData.removePlayer(player);
            }
        });
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().hasPermission("temp.canbreakblocks")) return;
        event.setCancelled(true);
        Block block = event.getBlock();
        if (StructureManager.sellStructureAt(block.getLocation())) return;
        // Adding the data to players' data
        if (block.getType().equals(Material.OAK_LOG)) {
            PlayerData.addWood(event.getPlayer());
        } else if (block.getType().equals(Material.STONE)) {
            PlayerData.addStone(event.getPlayer());
        } else if (block.getType().equals(Material.SWEET_BERRY_BUSH)) {
            if (PlayerData.getPlayersLastEvent(event.getPlayer()) < System.currentTimeMillis() - 250) {
                PlayerData.addFood(event.getPlayer());
                PlayerData.setPlayersLastEvent(event.getPlayer());
            }
        }
    }

}
