package me.yang.deathswap.Tasks.Reset.Classes;

import me.yang.deathswap.DeathSwap;
import me.yang.deathswap.Tasks.Init.Classes.InitTeam;
import me.yang.deathswap.Tasks.Uninit.Classes.UninitTeam;
import org.bukkit.scheduler.BukkitRunnable;

public class ResetTeam extends BukkitRunnable {
    private final DeathSwap plugin;

    public ResetTeam(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        //ScoreboardManager sm = Bukkit.getScoreboardManager();
        //Set<String> green = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries();
        //Set<String> blue = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).getEntries();
        new UninitTeam().run();
        new InitTeam(plugin).runTask(plugin);
    }
}
