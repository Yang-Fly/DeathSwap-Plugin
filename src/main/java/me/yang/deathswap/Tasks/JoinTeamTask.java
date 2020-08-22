package me.yang.deathswap.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class JoinTeamTask extends BukkitRunnable {
    private final CommandSender sender;
    private final String playerName;
    private final ScoreboardManager sm = Bukkit.getScoreboardManager();

    private String teamName;

    public JoinTeamTask(CommandSender sender, String teamName, String playerName) {
        this.sender = sender;
        this.teamName = teamName;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        ChatColor color;

        if (teamName.equals("blue")) {
            color = ChatColor.AQUA;
            teamName = "Blue";
        } else {
            color = ChatColor.GREEN;
            teamName = "Green";
        }
        Team team = sm.getMainScoreboard().getTeam(teamName);
        Objects.requireNonNull(team).addEntry(playerName);
        sender.sendMessage("Let " + color + playerName + ChatColor.RESET + "Joined Team " + color + team);
    }
}
