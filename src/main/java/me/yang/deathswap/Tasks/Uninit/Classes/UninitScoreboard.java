package me.yang.deathswap.Tasks.Uninit.Classes;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.ScoreboardManager;

public class UninitScoreboard {
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        //Uninit Scoreboard Objectives
        try {
            sm.getMainScoreboard().getObjective("DeathSwap").unregister();
            sm.getMainScoreboard().getObjective("deaths").unregister();
            sm.getMainScoreboard().getObjective("game_state").unregister();
            sm.getMainScoreboard().getObjective("health").unregister();
            sm.getMainScoreboard().getObjective("timer").unregister();
            sm.getMainScoreboard().getObjective("settings").unregister();
            sm.getMainScoreboard().getObjective("playerCount").unregister();
            sm.getMainScoreboard().getObjective("triggers").unregister();
        } catch (NullPointerException exception) {
            Bukkit.getLogger().warning("Catch NullPointerException at UninitScoreboard");
            Bukkit.getLogger().warning(exception.getLocalizedMessage());
        }
    }
}
