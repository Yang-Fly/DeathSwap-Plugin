package me.yang.deathswap.Tasks.Uninit.Classes;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class UninitScoreboard {
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        //Uninit Scoreboard Objectives
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("deaths")).unregister();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("timer")).unregister();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("game_state")).unregister();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("health")).unregister();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).unregister();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("playerCount")).unregister();
        Objects.requireNonNull(sm.getMainScoreboard().getObjective("triggers")).unregister();
    }
}
