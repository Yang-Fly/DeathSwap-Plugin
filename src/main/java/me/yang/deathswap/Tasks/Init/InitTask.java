package me.yang.deathswap.Tasks.Init;

import me.yang.deathswap.DeathSwap;
import me.yang.deathswap.Tasks.Init.Classes.InitBossBar;
import me.yang.deathswap.Tasks.Init.Classes.InitScoreboard;
import me.yang.deathswap.Tasks.Init.Classes.InitTeam;
import org.bukkit.scheduler.BukkitRunnable;

public class InitTask extends BukkitRunnable {
    private final DeathSwap plugin;

    public InitTask(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        new InitScoreboard().runTask(plugin);
        new InitTeam().runTask(plugin);
        new InitBossBar().runTask(plugin);
    }
}
