package me.yang.deathswap.Tasks;

import com.destroystokyo.paper.Title;
import me.yang.deathswap.DeathSwap;
import me.yang.deathswap.Tasks.Reset.ResetTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class End extends BukkitRunnable {
    private final DeathSwap plugin;

    private final String winnerName;
    private final String loserName;
    private final String winnerTeamName;

    private ChatColor winnerColor;
    private ChatColor loserColor;

    public End(DeathSwap plugin, String winnerName, String loserName, String winnerTeamName) {
        this.plugin = plugin;
        this.winnerName = winnerName;
        this.loserName = loserName;
        this.winnerTeamName = winnerTeamName;
    }

    @Override
    public void run() {
        Player winner = Bukkit.getPlayer(winnerName);
        Player loser = Bukkit.getPlayer(loserName);
        if (winnerTeamName.equals("blue")) {
            winnerColor = ChatColor.AQUA;
            loserColor = ChatColor.GREEN;
        } else if (winnerTeamName.equals("green")) {
            winnerColor = ChatColor.GREEN;
            loserColor = ChatColor.AQUA;
        }
        Title title = new Title(winnerColor + winnerName + " Wins!", loserColor + "Good Game " + loserName);
        Objects.requireNonNull(winner).sendTitle(title);
        Objects.requireNonNull(loser).sendTitle(title);

        winner.playSound(winner.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
        loser.playSound(loser.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);

        winner.remove();
        loser.remove();

        new ResetTask(plugin).runTask(plugin);
    }
}
