package me.yang.deathswap.Tasks.Reset;

import me.yang.deathswap.DeathSwap;
import me.yang.deathswap.Tasks.ChangeGameruleTask;
import me.yang.deathswap.Tasks.Reset.Classes.ResetBossBar;
import me.yang.deathswap.Tasks.Reset.Classes.ResetScoreboard;
import me.yang.deathswap.Tasks.Reset.Classes.ResetTeam;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;
import java.util.Set;

public class ResetTask extends BukkitRunnable {
    private final DeathSwap plugin;

    public ResetTask(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Player bluePlayer = Bukkit.getPlayer(Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().toString());
        assert bluePlayer != null;
        World world = bluePlayer.getWorld();

        new ResetScoreboard().runTask(plugin);
        new ResetTeam().runTask(plugin);
        new ResetBossBar().runTask(plugin);

        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                Set<String> tags = name.getScoreboardTags();
                tags.remove("player");
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
            player.setExp(0);
            player.setLevel(0);
        }

        ChangeGameruleTask.gameruleSet(world);
    }
}
