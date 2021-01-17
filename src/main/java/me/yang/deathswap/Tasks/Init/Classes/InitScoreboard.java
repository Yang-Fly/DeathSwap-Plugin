package me.yang.deathswap.Tasks.Init.Classes;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class InitScoreboard extends BukkitRunnable {
    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        //Init Scoreboard Objectives
        sm.getMainScoreboard().registerNewObjective("DeathSwap", "dummy", "DeathSwap");
        sm.getMainScoreboard().registerNewObjective("deaths", "deathCount", "deaths");
        sm.getMainScoreboard().registerNewObjective("timer", "dummy", "timer");
        sm.getMainScoreboard().registerNewObjective("game_state", "dummy", "game_state");
        sm.getMainScoreboard().registerNewObjective("health", "health", "health").setDisplaySlot(DisplaySlot.PLAYER_LIST);
        sm.getMainScoreboard().registerNewObjective("settings", "dummy", "settings");
        sm.getMainScoreboard().registerNewObjective("playerCount", "dummy", "playerCount");
        sm.getMainScoreboard().registerNewObjective("triggers", "trigger", "triggers");
        //Init Scoreboard Players
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("DeathSwap")).getScore("ON").setScore(1);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("timer").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("spread").setScore(10);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("start").setScore(600);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("startseconds").setScore(30);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("limit").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).getScore("tickrate").setScore(20);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").setScore(2);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("monsters").setScore(1);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("regen").setScore(1);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("saturation").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("spawn").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("warn").setScore(0);
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("game_state")).getScore("game_state").setScore(0);
    }
}
