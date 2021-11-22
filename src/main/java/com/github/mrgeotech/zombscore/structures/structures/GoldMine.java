package com.github.mrgeotech.zombscore.structures.structures;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Objects;
import java.util.UUID;

public class GoldMine extends Structure {

    private final int taskID;

    public GoldMine(Location location, UUID owner) {
        super(location, owner, null, (short) 1, new Cost(15, 15, 0), (short) 50);
        Block origin = location.getBlock().getRelative(BlockFace.UP);
        origin.setType(Material.RAW_GOLD_BLOCK);
        origin.getRelative(BlockFace.NORTH_EAST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.NORTH_WEST).setType(Material.OAK_WOOD);
        origin.getRelative(BlockFace.NORTH).setType(Material.GRAY_STAINED_GLASS_PANE);
        origin.getRelative(BlockFace.NORTH).setType(Material.GRAY_STAINED_GLASS_PANE);
        origin.getRelative(BlockFace.NORTH).setType(Material.GRAY_STAINED_GLASS_PANE);
        origin.getRelative(BlockFace.NORTH).setType(Material.GRAY_STAINED_GLASS_PANE);
        origin = origin.getRelative(BlockFace.UP);
        origin.getRelative(BlockFace.NORTH).setType(Material.OAK_SLAB);
        origin.getRelative(BlockFace.EAST).setType(Material.OAK_SLAB);
        origin.getRelative(BlockFace.SOUTH).setType(Material.OAK_SLAB);
        origin.getRelative(BlockFace.WEST).setType(Material.OAK_SLAB);
        this.taskID = Bukkit.getScheduler().runTaskTimer(
                Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ZombsCore")),
                () -> PlayerData.addGold(this.getOwner(), 10 * (this.getLevel() + 1)),
                20,
                20).getTaskId();
    }

    @Override
    public StructureType getType() {
        return StructureType.GOLD_MINE;
    }

    @Override
    public void upgrade() {
        level++;
        Block origin = location.getBlock().getRelative(BlockFace.UP);
        switch (level) {
            case 1:
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.STONE);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.STONE);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.STONE);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.STONE);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH).setType(Material.COBBLESTONE_SLAB);
                origin.getRelative(BlockFace.EAST).setType(Material.COBBLESTONE_SLAB);
                origin.getRelative(BlockFace.SOUTH).setType(Material.COBBLESTONE_SLAB);
                origin.getRelative(BlockFace.WEST).setType(Material.COBBLESTONE_SLAB);
                this.cost.setCost(30, 30, 250);
                break;
            case 2:
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.IRON_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.IRON_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH).setType(Material.SMOOTH_STONE_SLAB);
                origin.getRelative(BlockFace.EAST).setType(Material.SMOOTH_STONE_SLAB);
                origin.getRelative(BlockFace.SOUTH).setType(Material.SMOOTH_STONE_SLAB);
                origin.getRelative(BlockFace.WEST).setType(Material.SMOOTH_STONE_SLAB);
                this.cost.setCost(50, 50, 500);
                break;
            case 3:
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.GOLD_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.GOLD_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH).setType(Material.SMOOTH_SANDSTONE_SLAB);
                origin.getRelative(BlockFace.EAST).setType(Material.SMOOTH_SANDSTONE_SLAB);
                origin.getRelative(BlockFace.SOUTH).setType(Material.SMOOTH_SANDSTONE_SLAB);
                origin.getRelative(BlockFace.WEST).setType(Material.SMOOTH_SANDSTONE_SLAB);
                this.cost.setCost(100, 100, 1000);
                break;
            case 4:
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.DIAMOND_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.DIAMOND_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH).setType(Material.PRISMARINE_BRICK_SLAB);
                origin.getRelative(BlockFace.EAST).setType(Material.PRISMARINE_BRICK_SLAB);
                origin.getRelative(BlockFace.SOUTH).setType(Material.PRISMARINE_BRICK_SLAB);
                origin.getRelative(BlockFace.WEST).setType(Material.PRISMARINE_BRICK_SLAB);
                this.cost.setCost(200, 200, 5000);
                break;
            case 5:
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.LAPIS_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.LAPIS_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH).setType(Material.DARK_PRISMARINE_SLAB);
                origin.getRelative(BlockFace.EAST).setType(Material.DARK_PRISMARINE_SLAB);
                origin.getRelative(BlockFace.SOUTH).setType(Material.DARK_PRISMARINE_SLAB);
                origin.getRelative(BlockFace.WEST).setType(Material.DARK_PRISMARINE_SLAB);
                this.cost.setCost(300, 300, 10000);
                break;
            case 6:
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.REDSTONE_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.REDSTONE_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH).setType(Material.WAXED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.EAST).setType(Material.WAXED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.SOUTH).setType(Material.WAXED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.WEST).setType(Material.WAXED_CUT_COPPER_SLAB);
                this.cost.setCost(450, 450, 20000);
                break;
            case 7:
                origin.getRelative(BlockFace.NORTH_EAST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_EAST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.SOUTH_WEST).setType(Material.EMERALD_BLOCK);
                origin.getRelative(BlockFace.NORTH_WEST).setType(Material.EMERALD_BLOCK);
                origin = origin.getRelative(BlockFace.UP);
                origin.getRelative(BlockFace.NORTH).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.EAST).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.SOUTH).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                origin.getRelative(BlockFace.WEST).setType(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                this.cost.setCost(700, 700, 40000);
                break;
            default:
                level--;
                break;
        }
    }

    @Override
    public void delete() {
        Bukkit.getScheduler().cancelTask(taskID);
        super.delete();
    }

}
