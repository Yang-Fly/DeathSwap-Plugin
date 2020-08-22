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
        new InitScoreboard(plugin).runTask(plugin);
        new InitTeam(plugin).runTask(plugin);
        new InitBossBar(plugin).runTask(plugin);
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
        new ResetTask(plugin).runTask(plugin);
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "DeathSwap Plugin Ready!");
    }
}
