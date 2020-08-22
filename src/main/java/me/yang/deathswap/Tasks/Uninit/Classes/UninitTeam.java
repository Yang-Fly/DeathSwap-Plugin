package me.yang.deathswap.Tasks.Uninit.Classes;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class UninitTeam {
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).unregister();
        Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).unregister();
    }
}
