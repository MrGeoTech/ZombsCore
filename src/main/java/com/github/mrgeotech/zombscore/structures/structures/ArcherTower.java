package com.github.mrgeotech.zombscore.structures.structures;

import com.github.mrgeotech.zombscore.PlayerData;
import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;

import java.util.Objects;
import java.util.UUID;

public class ArcherTower extends Structure {

    private final int taskID;

    public ArcherTower(Location location, UUID owner) {
        super(location,
                owner,
                Objects.requireNonNull(location.getWorld()).spawnEntity(location.add(0, 3, 0), EntityType.ARMOR_STAND),
                (short) 1,
                new Cost(20, 20, 0),
                (short) 75);
        entity.setGravity(false);
        entity.setInvulnerable(true);
        ((ArmorStand) entity).setVisible(false);
        this.taskID = Bukkit.getScheduler().runTaskTimer(
                Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ZombsCore")),
                () -> {
                    if (this.entity.getNearbyEntities(7, 7, 7).size() < 1)
                        return;
                    entity.getWorld().spawnArrow(entity.getLocation(),
                            this.entity.getNearbyEntities(7, 7, 7).get(0).getLocation().toVector().subtract(this.entity.getLocation().toVector()),
                            .9f,
                            0);
                    System.out.println("shoot");
                },
                20,
                20).getTaskId();
        Block origin = location.getBlock().getRelative(BlockFace.UP);
    }

    @Override
    public StructureType getType() {
        return StructureType.ARCHER_TOWER;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void delete() {
        Bukkit.getScheduler().cancelTask(taskID);
        super.delete();
    }

}
