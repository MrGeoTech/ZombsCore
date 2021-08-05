package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureManager {

    private static List<Structure> structures;

    public static void init() {
        structures = new ArrayList<>();
    }

    public static void createStructure(List<Location> locations, List<Material> materials) {
        structures.add(new Structure(locations, materials));
    }

    public static boolean deleteStructure(Location location) {
        for (Structure structure : structures) {
            if (structure.deleteIfContains(location)) return true;
        }
        return false;
    }

    public static void deleteAllStructures() {
        for (Structure structure : structures) {
            structure.deleteStructure();
        }
    }

    public static void createWall(Location origin) {
        List<Location> tempLoc = new ArrayList<>();
        List<Material> tempMats = new ArrayList<>();
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        tempMats.add(Material.STONE);
        origin = origin.getBlock().getRelative(BlockFace.UP).getLocation();
        tempLoc.add(origin);
        tempMats.add(Material.STONE);
        createStructure(tempLoc, tempMats);
    }

}
