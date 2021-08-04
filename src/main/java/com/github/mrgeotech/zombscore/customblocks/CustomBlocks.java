package com.github.mrgeotech.zombscore.customblocks;

import org.bukkit.Location;

import java.util.*;

/**
 * The manager for all custom blocks/structures.
 * It has a list of all block locations for testing for block location purposes.
 */
public class CustomBlocks {

    private static Map<Location, CustomBlockHandler> handlers;

    public CustomBlocks() {
        handlers = new HashMap<>();
    }
    
    public static CustomBlockHandler getHandler(Location l) {
        return handlers.get(l);
    }
    
    public static boolean closeHandler(Location l) {
        CustomBlockHandler old = handlers.remove(l);
        if (old == null) return false;
        //TODO: close custom block handler
        return true;
    }
    
    //Throws an exception if the old handler hasn't been removed yet, as this probably means we forgot to close the CustomBlockHandler
    public static void mapHandler(Location l, CustomBlockHandler handler) throws IllegalStateException {
        if (handler == null) {
            handlers.remove(l);
        } else {
            CustomBlockHandler old = handlers.put(l, handler);
            if (old != null) throw new IllegalStateException("Old block handler not removed yet at " + l);
        }
    }

}
