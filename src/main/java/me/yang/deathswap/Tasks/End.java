package me.yang.deathswap.Tasks;

import me.yang.deathswap.DeathSwap;
import org.bukkit.scheduler.BukkitRunnable;

public class End extends BukkitRunnable {
    private final DeathSwap plugin;
    private final String winnerName;
    private final String loserName;

    public End(DeathSwap plugin, String winnerName, String loserName) {
        this.plugin = plugin;
        this.winnerName = winnerName;
        this.loserName = loserName;
    }

    @Override
    public void run() {
        //TODO: End
    }
}
