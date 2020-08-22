package me.yang.deathswap.Tasks;

import me.yang.deathswap.DeathSwap;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinTeamTask extends BukkitRunnable {
    private final DeathSwap plugin;
    private final CommandSender sender;
    private String team;
    private String playerName;

    public JoinTeamTask(DeathSwap plugin, CommandSender sender, String team, String playerName) {
        this.plugin = plugin;
        this.sender = sender;
        this.team = team;
        this.playerName = playerName;
    }

    public JoinTeamTask(DeathSwap plugin, CommandSender sender, String team) {
        this.plugin = plugin;
        this.sender = sender;
        this.team = team;
    }

    @Override
    public void run() {
        final ChatColor color;
        if (team.equals("blue")) {
            color = ChatColor.AQUA;
            team = "Blue";
        } else {
            color = ChatColor.GREEN;
            team = "Green";
        }
        sender.sendMessage("Joined Team " + color + team);

        //TODO: Join Team
    }
}
