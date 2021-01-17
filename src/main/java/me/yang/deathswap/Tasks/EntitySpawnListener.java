package me.yang.deathswap.Tasks;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        EntityType et = event.getEntityType();
        Entity entity = event.getEntity();

        if (et.equals(EntityType.DROPPED_ITEM)) {
            entity.remove();
        }
    }
}
