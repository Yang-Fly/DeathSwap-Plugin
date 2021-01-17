package me.yang.deathswap.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Set;

public class JoinTeamTask extends BukkitRunnable {
    private final String playerName;
    private final ScoreboardManager sm = Bukkit.getScoreboardManager();
    private String teamName;

    public JoinTeamTask(String teamName, String playerName) {
        this.teamName = teamName;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        ChatColor color;
        String otherTeamName;

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
        assert otherTeam != null;
        assert team != null;

        if (playerName.equals("CONSOLE")) {
            Bukkit.getLogger().warning("You must be a player!");
        } else {
            if (teamName.equals("Blue")) {
                joinATeam(color, team, otherTeam);
            }
            if (teamName.equals("Green")) {
                joinATeam(color, team, otherTeam);
            }
        }
    }

    private void joinATeam(ChatColor color, Team team, Team otherTeam) {
        Set<String> teamMember = team.getEntries();
        Set<String> otherTeamMember = otherTeam.getEntries();
        Player player = Bukkit.getPlayer(playerName);
        assert player != null;
        int teamNum = teamMember.size();
        /*
            situations:
            1.teamMember.contain(playerName)           "You are already joined in the team."
            2.!teamMember.contain(playerName) && otherTeamMember.contain(playerName)           "You are already in another team."
            3.!teamMember.contain(playerName) && !otherTeamMember.contain(playerName) && teamNum = 1           "There is already another player in that team."
            4.!teamMember.contain(playerName) && !otherTeamMember.contain(playerName) && teamNum != 1          playerName + " joined " + color + teamName
         */
        if (teamMember.contains(playerName)) {
            player.sendMessage("You are already joined in the team.");
        } else {
            if (otherTeamMember.contains(playerName)) {
                player.sendMessage("You are already in another team.");
            } else {
                if (teamNum == 1) {
                    player.sendMessage("There is already another player in that team.");
                } else {
                    Bukkit.broadcastMessage(playerName + " joined " + color + teamName);
                }
            }
        }
    }
}
