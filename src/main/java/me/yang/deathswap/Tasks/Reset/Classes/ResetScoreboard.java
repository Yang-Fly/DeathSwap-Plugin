package me.yang.deathswap.Tasks.Reset.Classes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class ResetScoreboard extends BukkitRunnable {
    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("game_state")).getScore("game_state").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("elapsed").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("seconds").setScore(0);
        for (Player player : Bukkit.getOnlinePlayers()) {
            String name = player.getName();
            Objects.requireNonNull(sm.getMainScoreboard().getObjective("deaths")).getScore(name).setScore(0);
        }
    }
}
