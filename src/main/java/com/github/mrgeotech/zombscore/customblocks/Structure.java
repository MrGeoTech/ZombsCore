package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Structure {

    private List<Location> locations;

    public Structure(Map<Location, Material> locations) {
        this.locations = new ArrayList<>();
        for (Location location : locations.keySet()) {
            location.getWorld().getBlockAt(location).setType(locations.get(location));
            this.locations.add(location);
        }
    }

    public boolean contains(Location location) {
        for (Location tempLoc : locations) {
            if (tempLoc.equals(location)) return true;
        }
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
        for (Location location : locations) {
            location.getWorld().getBlockAt(location).setType(Material.AIR);
        }
    }

}
