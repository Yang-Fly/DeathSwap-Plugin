package me.yang.deathswap.Tasks;

import me.yang.deathswap.DeathSwap;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class Timer extends BukkitRunnable {
    private final DeathSwap plugin;
    private final CommandSender sender;

    public Timer(DeathSwap plugin, CommandSender sender) {
        this.plugin = plugin;
        this.sender = sender;
    }

    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("elapsed").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("seconds").setScore(0);

        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 1);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 2);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 4);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 8);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 16);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 32);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 64);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore() + 128);

        int timer = Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore();
        int spread = Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("spread").getScore();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(timer * spread);

        timer = Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").getScore();
        int start = Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("start").getScore();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(timer + start);
        Bukkit.broadcastMessage("Timer Init Completed");
    }
}
