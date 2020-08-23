package me.yang.deathswap.Tasks.Reset.Classes;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class ResetTeam extends BukkitRunnable {
    @Override
    public void run() throws UnsupportedOperationException{
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().clear();
        Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).getEntries().clear();
    }
}
