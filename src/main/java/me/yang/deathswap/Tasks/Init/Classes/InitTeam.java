package me.yang.deathswap.Tasks.Init.Classes;

import me.yang.deathswap.DeathSwap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class InitTeam extends BukkitRunnable {
    private final DeathSwap plugin;
    public InitTeam(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        sm.getMainScoreboard().registerNewTeam("Green").setColor(ChatColor.GREEN);
        sm.getMainScoreboard().registerNewTeam("Blue").setColor(ChatColor.AQUA);
        Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Green Player");
        Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Blue Player");
    }
}
