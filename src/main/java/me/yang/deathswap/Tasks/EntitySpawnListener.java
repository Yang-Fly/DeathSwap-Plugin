package me.yang.deathswap.Tasks;

import me.yang.deathswap.DeathSwap;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {
    private final DeathSwap plugin;

    public EntitySpawnListener(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        EntityType et = event.getEntityType();
        if (et.equals(EntityType.valueOf("minecraft.written_book"))) {
            event.getEntity().remove();
        }
    }
}
