package me.yang.deathswap.Tasks.Reset;

import me.yang.deathswap.DeathSwap;
import me.yang.deathswap.Tasks.Reset.Classes.ResetBossBar;
import me.yang.deathswap.Tasks.Reset.Classes.ResetScoreboard;
import me.yang.deathswap.Tasks.Reset.Classes.ResetTeam;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Set;

public class ResetTask extends BukkitRunnable {
    private final DeathSwap plugin;

    public ResetTask(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        new ResetScoreboard().runTask(plugin);
        new ResetTeam().runTask(plugin);
        new ResetBossBar().runTask(plugin);

        for (Player player : Bukkit.getOnlinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                name.getInventory().clear();
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                name.setGameMode(GameMode.SURVIVAL);
            }
        }

        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                Set<String> tags = name.getScoreboardTags();
                tags.remove("player");
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setExp(0);
            player.setLevel(0);
        }

        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setTime(3000);
        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.DO_MOB_SPAWNING, false);
        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.NATURAL_REGENERATION, true);
        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.KEEP_INVENTORY, true);
        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Reset Complete!");
    }
}
