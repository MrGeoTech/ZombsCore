package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class Structure {

    private List<Location> locations;

    public Structure(List<Location> locations, List<Material> materials) {
        this.locations = locations;
        for (int i = 0; i < locations.size(); i++) {
            locations.get(i).getBlock().setType(materials.get(i));
        }
    }

    public boolean contains(Location location) {
        for (int i = 0; i < locations.size(); i++) {
            Location location1 = locations.get(i);
            if (location.getBlockX() == location1.getBlockX() && location.getBlockY() == location1.getBlockY() && location.getBlockZ() == location1.getBlockZ()) {
                System.out.println("True");
                return true;
            }
        }
        System.out.println("False");
        return false;
    }

    public boolean deleteIfContains(Location location) {
        if (this.contains(location)) {
            this.deleteStructure();
            return true;
        } else {
            return false;
        }
    }

    public void deleteStructure() {
        System.out.println("delete");
        for (Location location : locations) {
            location.getWorld().getBlockAt(location).setType(Material.AIR);
        }
    }

}
