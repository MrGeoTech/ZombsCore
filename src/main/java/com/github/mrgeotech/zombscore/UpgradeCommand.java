package com.github.mrgeotech.zombscore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpgradeCommand implements CommandExecutor, Listener {

    private Plugin plugin;

    private Map<String,ItemStack> axeUpgrades;
    private Map<String,ItemStack> pickaxeUpgrades;
    private Map<String,ItemStack> swordUpgrades;

    public UpgradeCommand() {
        axeUpgrades = new HashMap<>();
        pickaxeUpgrades = new HashMap<>();
        swordUpgrades = new HashMap<>();
        plugin = Bukkit.getPluginManager().getPlugin("ZombsCore");
        // Initializing all the items and their upgrades
        ItemStack item = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Wooden Axe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "stone");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "not-worldedit"), PersistentDataType.INTEGER, 1);
        item.setItemMeta(meta);
        axeUpgrades.put("wood", item);
        item = new ItemStack(Material.STONE_AXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Stone Axe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "iron");
        item.setItemMeta(meta);
        axeUpgrades.put("stone", item);
        item = new ItemStack(Material.IRON_AXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Iron Axe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "gold");
        item.setItemMeta(meta);
        axeUpgrades.put("iron", item);
        item = new ItemStack(Material.GOLDEN_AXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Axe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "diamond");
        item.setItemMeta(meta);
        axeUpgrades.put("gold", item);
        item = new ItemStack(Material.DIAMOND_AXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Diamond Axe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "shiny-gold");
        item.setItemMeta(meta);
        axeUpgrades.put("diamond", item);
        item = new ItemStack(Material.GOLDEN_AXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Shiny Gold Axe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "shiny-diamond");
        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        item.setItemMeta(meta);
        axeUpgrades.put("shiny-gold", item);
        item = new ItemStack(Material.DIAMOND_AXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Shiny Diamond Axe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "none");
        meta.addEnchant(Enchantment.DIG_SPEED, 7, true);
        item.setItemMeta(meta);
        axeUpgrades.put("shiny-diamond", item);
        item = new ItemStack(Material.WOODEN_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Wooden Pickaxe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "stone");
        item.setItemMeta(meta);
        // Pickaxes
        pickaxeUpgrades.put("wood", item);
        item = new ItemStack(Material.STONE_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Stone Pickaxe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "iron");
        item.setItemMeta(meta);
        pickaxeUpgrades.put("stone", item);
        item = new ItemStack(Material.IRON_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Iron Pickaxe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "gold");
        item.setItemMeta(meta);
        pickaxeUpgrades.put("iron", item);
        item = new ItemStack(Material.GOLDEN_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Pickaxe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "diamond");
        item.setItemMeta(meta);
        pickaxeUpgrades.put("gold", item);
        item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Diamond Pickaxe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "shiny-gold");
        item.setItemMeta(meta);
        pickaxeUpgrades.put("diamond", item);
        item = new ItemStack(Material.GOLDEN_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Shiny Gold Pickaxe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "shiny-diamond");
        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        item.setItemMeta(meta);
        pickaxeUpgrades.put("shiny-gold", item);
        item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Shiny Diamond Pickaxe");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "none");
        meta.addEnchant(Enchantment.DIG_SPEED, 7, true);
        item.setItemMeta(meta);
        pickaxeUpgrades.put("shiny-diamond", item);
        item = new ItemStack(Material.WOODEN_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Wooden Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "stone");
        item.setItemMeta(meta);
        // Swords
        swordUpgrades.put("wood", item);
        item = new ItemStack(Material.STONE_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Stone Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "iron");
        item.setItemMeta(meta);
        swordUpgrades.put("stone", item);
        item = new ItemStack(Material.IRON_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Iron Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "gold");
        item.setItemMeta(meta);
        swordUpgrades.put("iron", item);
        item = new ItemStack(Material.GOLDEN_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "diamond");
        item.setItemMeta(meta);
        swordUpgrades.put("gold", item);
        item = new ItemStack(Material.DIAMOND_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Diamond Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "shiny-gold");
        item.setItemMeta(meta);
        swordUpgrades.put("diamond", item);
        item = new ItemStack(Material.GOLDEN_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Shiny Gold Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "shiny-diamond");
        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        item.setItemMeta(meta);
        swordUpgrades.put("shiny-gold", item);
        item = new ItemStack(Material.DIAMOND_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Shiny Diamond Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING, "none");
        meta.addEnchant(Enchantment.DIG_SPEED, 7, true);
        item.setItemMeta(meta);
        swordUpgrades.put("shiny-diamond", item);
        System.gc(); // Much needed garbage collection
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("player.upgrade")) return sendAndQuit(sender, "Sorry but you don't have permission to execute this command!");
        if (!(sender instanceof Player)) return sendAndQuit(sender, "Only players can execute this command!");
        Player player = (Player) sender;
        // Creating the inventory for the player
        Inventory inventory = Bukkit.createInventory(null, 27, "Upgrades Menu");
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
        item = new ItemStack(Material.WOODEN_AXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Upgrade Axe");
        lore.add(ChatColor.DARK_GRAY + "Click to upgrade your axe!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(10, item);
        lore.clear();
        item = new ItemStack(Material.WOODEN_PICKAXE, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Upgrade Pickaxe");
        lore.add(ChatColor.DARK_GRAY + "Click to upgrade your pickaxe!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(12, item);
        lore.clear();
        item = new ItemStack(Material.WOODEN_SWORD, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Upgrade Sword");
        lore.add(ChatColor.DARK_GRAY + "Click to upgrade your sword!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(14, item);
        lore.clear();
        item = new ItemStack(Material.SHEEP_SPAWN_EGG, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Pets");
        lore.add(ChatColor.DARK_GRAY + "Click to access your pets!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(16, item);
        // Opens the inventory for the player
        player.openInventory(inventory);
        return true;
    }

    private boolean sendAndQuit(CommandSender sender, String text) {
        // A way to make sending a message to a player and returning true easier
        sender.sendMessage(ChatColor.RED + text);
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getView().getTitle().equalsIgnoreCase("Upgrades Menu")) {
            Player player = (Player) event.getWhoClicked();
            PlayerInventory inventory = player.getInventory();
            String next;
            // The slots that have things that do stuff when clicked
            switch (event.getSlot()) {
                case 10:
                    // Upgrading the axe
                    next = inventory.getItem(1).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING);
                    if (!next.equalsIgnoreCase("none"))
                        inventory.setItem(1, axeUpgrades.get(next));
                    else
                        player.sendMessage(ChatColor.RED + "You already have the best axe!");
                    player.closeInventory();
                case 12:
                    // Upgrading the pickaxe
                    next = inventory.getItem(2).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING);
                    if (!next.equalsIgnoreCase("none"))
                        inventory.setItem(2, pickaxeUpgrades.get(next));
                    else
                        player.sendMessage(ChatColor.RED + "You already have the best pickaxe!");
                    player.closeInventory();
                case 14:
                    // Upgrading the sword
                    next = inventory.getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "next-upgrade"), PersistentDataType.STRING);
                    if (!next.equalsIgnoreCase("none"))
                        inventory.setItem(0, swordUpgrades.get(next));
                    else
                        player.sendMessage(ChatColor.RED + "You already have the best sword!");
                    player.closeInventory();
                case 16:
                    // Upgrading/getting pets
                    player.closeInventory();
                    player.sendMessage("Coming soon");
            }
        } else if (event.getCurrentItem().getType().equals(Material.NETHER_STAR)) {
            // If it is the nether star in the players' inventory
            ((Player) event.getWhoClicked()).performCommand("/upgrades");
        } else if (event.getCurrentItem().getType().equals(Material.SWEET_BERRIES)) {
            // If it is the sweet berries in the players' inventory
            PlayerData.removeFood((Player) event.getWhoClicked());
        }
    }

    public Map<String, ItemStack> getAxeUpgrades() {
        return axeUpgrades;
    }

    public Map<String, ItemStack> getPickaxeUpgrades() {
        return pickaxeUpgrades;
    }

    public Map<String, ItemStack> getSwordUpgrades() {
        return swordUpgrades;
    }

}
