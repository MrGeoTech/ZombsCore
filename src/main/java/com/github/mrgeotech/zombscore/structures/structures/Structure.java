package com.github.mrgeotech.zombscore.structures.structures;

import com.github.mrgeotech.zombscore.structures.StructureType;
import com.github.mrgeotech.zombscore.utils.Cost;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.UUID;

public abstract class Structure {

    protected Location location;
    protected Entity entity;
    protected short level;
    protected short size;
    protected UUID owner;
    protected Cost cost;

    public Structure(Location location, UUID owner, Entity entity, short size, Cost cost) {
        this.location = location;
        this.owner = owner;
        this.entity = entity;
        this.size = size;
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

    public boolean isOwnedBy(UUID player) {
        return player.equals(owner);
    }

    public Cost getCost() {
        return cost;
    }

    public boolean contains(Location location) {
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

    public abstract void delete();

}
