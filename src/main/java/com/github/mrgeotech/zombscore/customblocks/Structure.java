package com.github.mrgeotech.zombscore.customblocks;

import com.github.mrgeotech.zombscore.ReflectionUtils;
import com.github.mrgeotech.zombscore.ZombsCore;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

class Structure {

    private String id;
    private String type;
    private Map<Location,Material> blocks;
    private int damage;

    public Structure(String id, String type, Map<Location,Material> blocks) {
        this.id = id;
        this.type = type;
        this.blocks = blocks;
        this.damage = 0;
        // Setting the blocks to the material in the world
        for (Location location : blocks.keySet()) {
            location.getWorld().getBlockAt(location).setType(blocks.get(location));
            StructureManager.addLocation(location, this);
        }
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Map<Location,Material> getBlocks() {
        return blocks;
    }

    public List<Location> getLocations() {
        return blocks.keySet().stream().toList();
    }

    public void setBlock(Location location, Material material) {
        location.getWorld().getBlockAt(location).setType(material);
        blocks.put(location, material);
    }

    public void deleteStructure() {
        for (Location location : blocks.keySet()) {
            location.getWorld().getBlockAt(location).setType(Material.AIR);
        }
    }

    public void damageStructure() {
        this.damage += 1;
        if (damage > 8) {
            deleteStructure();
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously(ZombsCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (Location location : blocks.keySet()) {
                        if (distanceFromEachOther(player.getLocation(), location) < 50)
                            ReflectionUtils.sendPacket(player, new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()), damage));
                    }
                }
            }
            private double distanceFromEachOther(Location pos1, Location pos2) {
                return Math.sqrt((pos1.getBlockX() - pos2.getBlockX()) + (pos1.getBlockY() - pos2.getBlockY()) + (pos1.getBlockZ() - pos2.getBlockZ()));
            }
        });
    }

    public void repairStructure() {
        this.damage -= 1;
        Bukkit.getScheduler().runTaskAsynchronously(ZombsCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (Location location : blocks.keySet()) {
                        if (distanceFromEachOther(player.getLocation(), location) < 50)
                            ReflectionUtils.sendPacket(player, new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()), damage));
                    }
                }
            }
            private double distanceFromEachOther(Location pos1, Location pos2) {
                return Math.sqrt((pos1.getBlockX() - pos2.getBlockX()) + (pos1.getBlockY() - pos2.getBlockY()) + (pos1.getBlockZ() - pos2.getBlockZ()));
            }
        });
    }

}
