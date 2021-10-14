package com.github.mrgeotech.zombscore.customblocks;

import com.github.mrgeotech.zombscore.PlayerData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class Structure {

    private List<Location> locations;
    private Entity entity;
    private int id;
    private int level;
    private Player owner;

    public Structure(List<Location> locations, Player player, int type) {
        PlayerData.removeStructure(player, id);
        this.locations = locations;
        this.id = type;
        this.level = 0;
        this.owner = player;
        for (int i = 0; i < locations.size(); i++) {
            locations.get(i).getBlock().setType(Material.OAK_WOOD);
        }
    }

    public int getType() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void upgrade() {
        level++;
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
