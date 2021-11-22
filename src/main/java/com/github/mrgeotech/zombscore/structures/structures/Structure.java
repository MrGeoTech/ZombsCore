package com.github.mrgeotech.zombscore.structures.structures;

import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public abstract class Structure {

    protected final Location location;
    protected final Entity entity;
    protected final UUID owner;
    protected final short size;

    protected Cost cost;
    protected short level;
    protected short health;

    public Structure(@NotNull Location location, @NotNull UUID owner, Entity entity, short size, @NotNull Cost cost, short health) {
        this.location = location;
        this.owner = owner;
        this.entity = entity;
        this.size = size;
        this.health = health;
        this.level = 0;
        this.cost = cost;
    }

    public short getLevel() {
        return level;
    }

    public UUID getOwner() {
        return owner;
    }

    public short getSize() {
        return size;
    }

    public short getHealth() {
        return health;
    }

    public boolean isOwnedBy(@NotNull UUID player) {
        return player.equals(owner);
    }

    public Cost getCost() {
        return cost;
    }

    public boolean contains(@NotNull Location location) {
        for (int x = this.location.getBlockX() - size; x <= this.location.getBlockX() + size; x++) {
            for (int z = this.location.getBlockX() - size; z <= this.location.getBlockX() + size; z++) {
                if (this.location == location) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract StructureType getType();

    public abstract void upgrade();

    public void delete() {
        for (int x = this.location.getBlockX() - size; x <= this.location.getBlockX() + size; x++) {
            for (int y = this.location.getBlockX() + 1; y <= this.location.getBlockX() + 4; y++) {
                for (int z = this.location.getBlockX() - size; z <= this.location.getBlockX() + size; z++) {
                    Objects.requireNonNull(location.getWorld()).getBlockAt(x, y, x).setType(Material.AIR);
                }
            }
        }
    }

}
