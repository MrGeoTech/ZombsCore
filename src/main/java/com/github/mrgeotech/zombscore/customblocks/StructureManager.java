package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureManager {

    private static List<Structure> structures;

    public static void init() {
        structures = new ArrayList<>();
    }

    public static void createStructure(Map<Location,Material> locations) {
        structures.add(new Structure(locations));
    }

    public static void deleteStructure(Location location) {
        for (Structure structure : structures) {
            if (structure.deleteIfContains(location)) return;
        }
    }

    public static void createWall(Location origin) {
        Map<Location,Material> temp = new HashMap<>();
        origin.setY(origin.getBlockY() + 1);
        System.out.println(origin.toString());
        temp.put(origin, Material.STONE);
        origin.setY(origin.getBlockY() + 1);
        System.out.println(origin.toString());
        temp.put(origin, Material.STONE);
        createStructure(temp);
    }

}
