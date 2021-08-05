package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

class WallBuilder implements StructureBuilder {

    @Override
    public void addLocation(Location origin) {
        // Creating a new wall
        Map<Location,Material> locations = new HashMap<>();
        locations.put(origin.add(0, 1, 0), Material.STONE);
        locations.put(origin.add(0, 2, 0), Material.STONE);
        new Structure(origin.toString(), "wall", locations);
    }

}
