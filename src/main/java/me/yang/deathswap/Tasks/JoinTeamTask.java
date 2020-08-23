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
    private String otherTeamName;

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
            otherTeamName = "Green";
        } else {
            color = ChatColor.GREEN;
            teamName = "Green";
            otherTeamName = "Blue";
        }
        
        Team team = sm.getMainScoreboard().getTeam(teamName);
        Team otherTeam = sm.getMainScoreboard().getTeam(otherTeamName);
        
        if (teamName.equals("Blue")) {
            if (Objects.requireNonNull(team).getEntries().contains(playerName) && sender.getName().equals(playerName)) {
                sender.sendMessage("You are already joined in the team.");
            } else if (team.getEntries().contains(playerName) && !sender.getName().equals(playerName)) {
                sender.sendMessage("He is already joined in that team.");
            } else if (Objects.requireNonNull(otherTeam).getEntries().contains(playerName) && sender.getName().equals(playerName)) {
                sender.sendMessage("You are already joined another team.");
            } else if (otherTeam.getEntries().contains(playerName) && !sender.getName().equals(playerName)) {
                sender.sendMessage("He is already joined another team.");
            } else if (!team.getEntries().contains(playerName) && !otherTeam.getEntries().contains(playerName) && sender.getName().equals(playerName)){
                Objects.requireNonNull(team).addEntry(playerName);
                sender.sendMessage("Joined Team " + color + teamName);
            } else if (!team.getEntries().contains(playerName) && !otherTeam.getEntries().contains(playerName) && !sender.getName().equals(playerName)) {
                Objects.requireNonNull(team).addEntry(playerName);
                sender.sendMessage("Let " + color + playerName + ChatColor.RESET + "Joined Team " + color + teamName);
            }
        }
        if (teamName.equals("Green")) {
            if (Objects.requireNonNull(team).getEntries().contains(playerName) && sender.getName().equals(playerName)) {
                sender.sendMessage("You are already joined in the team.");
            } else if (team.getEntries().contains(playerName) && !sender.getName().equals(playerName)) {
                sender.sendMessage("He is already joined in that team.");
            } else if (Objects.requireNonNull(otherTeam).getEntries().contains(playerName) && sender.getName().equals(playerName)) {
                sender.sendMessage("You are already joined another team.");
            } else if (otherTeam.getEntries().contains(playerName) && !sender.getName().equals(playerName)) {
                sender.sendMessage("He is already joined another team.");
            } else if (!team.getEntries().contains(playerName) && !otherTeam.getEntries().contains(playerName) && sender.getName().equals(playerName)){
                Objects.requireNonNull(team).addEntry(playerName);
                sender.sendMessage("Joined Team " + color + teamName);
            } else if (!team.getEntries().contains(playerName) && !otherTeam.getEntries().contains(playerName) && !sender.getName().equals(playerName)) {
                Objects.requireNonNull(team).addEntry(playerName);
                sender.sendMessage("Let " + color + playerName + ChatColor.RESET + "Joined Team " + color + teamName);
            }
        }
    }
}
