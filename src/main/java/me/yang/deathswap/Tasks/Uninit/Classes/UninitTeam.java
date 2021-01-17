package me.yang.deathswap.Tasks.Uninit.Classes;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.ScoreboardManager;

public class UninitTeam {
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        try {
            sm.getMainScoreboard().getTeam("Blue").unregister();
            sm.getMainScoreboard().getTeam("Green").unregister();
        } catch (NullPointerException exception) {
            Bukkit.getLogger().warning("Catch NullPointerException at UninitTeam");
            Bukkit.getLogger().warning(exception.getLocalizedMessage());
        }
    }
}
