package com.github.mrgeotech.zombscore.structures.structures;

import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Objects;
import java.util.UUID;

public class BaseHeart extends Structure {

    public BaseHeart(Location location, UUID owner) {
        super(location, owner, null, (short) 1, new Cost(25, 25, 0), (short) 50);
        Block origin = location.getBlock().getRelative(BlockFace.UP);
        origin.setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.NORTH).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.NORTH_EAST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.EAST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.SOUTH).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.WEST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.NORTH_WEST).setType(Material.OAK_WOOD);
        origin = origin.getRelative(BlockFace.UP);
        origin.setType(Material.RAW_GOLD_BLOCK);
        origin.getRelative(BlockFace.NORTH).setType(Material.RAW_GOLD_BLOCK);
        origin.getRelative(BlockFace.NORTH_EAST).setType(Material.OAK_SLAB);
        origin.getRelative(BlockFace.EAST).setType(Material.RAW_GOLD_BLOCK);
        origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.OAK_SLAB);
        origin.getRelative(BlockFace.SOUTH).setType(Material.RAW_GOLD_BLOCK);
        origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.OAK_SLAB);
        origin.getRelative(BlockFace.WEST).setType(Material.RAW_GOLD_BLOCK);
        origin.getRelative(BlockFace.NORTH_WEST).setType(Material.OAK_SLAB);
        origin.getRelative(BlockFace.UP).setType(Material.RAW_GOLD_BLOCK);
    }

    @Override
    public StructureType getType() {
        return StructureType.BASE_HEART;
    }

    @Override
    public void upgrade() {
        this.level++;
        Block origin = location.getBlock().getRelative(BlockFace.UP);
        switch (level) {
            case 1:
                origin.setType(Material.STONE);
                origin.getRelative(BlockFace.NORTH).setType(Material.STONE);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.STONE);
                origin.getRelative(BlockFace.EAST).setType(Material.STONE);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.STONE);
                origin.getRelative(BlockFace.SOUTH).setType(Material.STONE);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.STONE);
                origin.getRelative(BlockFace.WEST).setType(Material.STONE);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.STONE);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.COBBLESTONE_SLAB);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.COBBLESTONE_SLAB);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.COBBLESTONE_SLAB);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.COBBLESTONE_SLAB);
                this.cost.setCost(50, 50, 500);
                break;
            case 2:
                origin.setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.NORTH).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.EAST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.SOUTH).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.WEST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.IRON_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.SMOOTH_STONE_SLAB);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.SMOOTH_STONE_SLAB);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.SMOOTH_STONE_SLAB);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.SMOOTH_STONE_SLAB);
                this.cost.setCost(75, 75, 1000);
                break;
            case 3:
                origin.setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.NORTH).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.EAST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.SOUTH).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.WEST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.GOLD_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.SMOOTH_SANDSTONE_SLAB);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.SMOOTH_SANDSTONE_SLAB);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.SMOOTH_SANDSTONE_SLAB);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.SMOOTH_SANDSTONE_SLAB);
                this.cost.setCost(125, 125, 5000);
                break;
            case 4:
                origin.setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.NORTH).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.EAST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.SOUTH).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.WEST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.DIAMOND_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.PRISMARINE_BRICK_SLAB);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.PRISMARINE_BRICK_SLAB);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.PRISMARINE_BRICK_SLAB);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.PRISMARINE_BRICK_SLAB);
                this.cost.setCost(200, 200, 10000);
                break;
            case 5:
                origin.setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.NORTH).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.EAST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.SOUTH).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.WEST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.LAPIS_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.DARK_PRISMARINE_SLAB);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.DARK_PRISMARINE_SLAB);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.DARK_PRISMARINE_SLAB);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.DARK_PRISMARINE_SLAB);
                this.cost.setCost(500, 500, 50000);
                break;
            case 6:
                origin.setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.NORTH).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.EAST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.SOUTH).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.WEST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.REDSTONE_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.WAXED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.WAXED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.WAXED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.WAXED_CUT_COPPER_SLAB);
                this.cost.setCost(750, 750, 75000);
                break;
            case 7:
                origin.setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.NORTH).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.EAST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.SOUTH).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.WEST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.EMERALD_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                this.cost.setCost(1000, 1000, 100000);
                break;
            default:
                level--;
                break;
        }
    }

}
