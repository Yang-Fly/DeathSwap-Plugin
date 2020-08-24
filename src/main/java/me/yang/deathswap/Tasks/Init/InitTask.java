package me.yang.deathswap.Tasks.Init;

import me.yang.deathswap.DeathSwap;
import me.yang.deathswap.Tasks.Init.Classes.InitBossBar;
import me.yang.deathswap.Tasks.Init.Classes.InitScoreboard;
import me.yang.deathswap.Tasks.Init.Classes.InitTeam;
import me.yang.deathswap.Tasks.Reset.ResetTask;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.destroystokyo.paper.Title;

import java.util.Objects;
import java.util.Set;

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
        Title title = new Title(ChatColor.AQUA + "Changing Title Times", ChatColor.GREEN + "Wait for a second", 20, 100, 20);
        for (Player player : Bukkit.getOnlinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                name.updateTitle(title);
            }
        }
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                Set<String> tags = name.getScoreboardTags();
                tags.remove("victor");
            }
        }
        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setDifficulty(Difficulty.PEACEFUL);
        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.LOG_ADMIN_COMMANDS, true);
        //new ResetTask(plugin).run();
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
        
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "DeathSwap Plugin Ready!");
    }
}
