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
        if (et.equals(EntityType.valueOf("minecraft.written_book"))) {
            event.getEntity().remove();
        } else if (et.equals(EntityType.ARMOR_STAND)) {
            Entity entity = event.getEntity();
            entity.setCustomName("tp");
            entity.setCustomNameVisible(false);
            entity.setGravity(false);
            entity.setInvulnerable(true);
        }
    }
}
