package com.github.mrgeotech.zombscore.structures;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.ZombsCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class Structure {

    private List<Location> locations;
    private Entity entity;
    private short id;
    private short level;
    private Player owner;
    private short health;

    public Structure(List<Location> locations, Player player, short type) {
        this.locations = locations;
        this.id = type;
        this.level = 0;
        this.owner = player;
        this.health = 100;
        PlayerData.removeStructure(player, id);
        for (int i = 0; i < locations.size(); i++) {
            locations.get(i).getBlock().setType(Material.OAK_WOOD);
        }
        if (type == 2) {
            GoldMiner gm = new GoldMiner(this);
            gm.setId(Bukkit.getScheduler().runTaskTimer(ZombsCore.getInstance(), gm, 0, 20).getTaskId());
        }
    }

    public short getType() {
        return id;
    }

    public short getLevel() {
        return level;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void upgrade() {
        level++;
        health *= 2;
        if (level == 1) {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).getBlock().setType(Material.STONE);
            }
        } else if (level == 2) {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).getBlock().setType(Material.IRON_BLOCK);
            }
        } else if (level == 3) {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).getBlock().setType(Material.GOLD_BLOCK);
            }
        } else if (level == 4) {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).getBlock().setType(Material.DIAMOND_BLOCK);
            }
        } else if (level == 5) {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).getBlock().setType(Material.LAPIS_BLOCK);
            }
        } else if (level == 6) {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).getBlock().setType(Material.REDSTONE_BLOCK);
            }
        }
    }

    public boolean contains(Location location) {
        for (int i = 0; i < locations.size(); i++) {
            Location location1 = locations.get(i);
            if (location.getBlockX() == location1.getBlockX() && location.getBlockY() == location1.getBlockY() && location.getBlockZ() == location1.getBlockZ()) {
                return true;
            }
        }
        return false;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOwnedBy(Player player) {
        return player == owner;
    }

    public boolean deleteIfContains(Location location) {
        if (this.contains(location)) {
            this.deleteStructure();
            return true;
        }
        return false;
    }

    public boolean deleteIfOwnedBy(Player player) {
        if (this.owner == player) {
            deleteStructure();
            return true;
        }
        return false;
    }

    public void deleteStructure() {
        for (Location location : locations) {
            location.getWorld().getBlockAt(location).setType(Material.AIR);
        }
        PlayerData.addStructure(owner, id);
    }

}