package me.yang.deathswap.Tasks;

import me.yang.deathswap.DeathSwap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class Trigger extends BukkitRunnable {
    private final DeathSwap plugin;

    public Trigger(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Objective settings = Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings"));
        for (Player player : Bukkit.getOnlinePlayers()) {
            String name = player.getName();
            int triggers = Objects.requireNonNull(player.getScoreboard().getObjective("triggers")).getScore(name).getScore();
            switch (triggers) {
                case 1:
                    new JoinTeamTask("blue", name).run();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 2:
                    new JoinTeamTask("green", name).run();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 3:
                    new StartTask(plugin).runTask(plugin);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 4:
                    settings.getScore("difficulty").setScore(0);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " set the difficulty to Peaceful");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 5:
                    settings.getScore("difficulty").setScore(1);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " set the difficulty to Easy");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 6:
                    settings.getScore("difficulty").setScore(2);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " set the difficulty to Normal");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 7:
                    settings.getScore("difficulty").setScore(3);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " set the difficulty to Hard");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 8:
                    settings.getScore("monsters").setScore(1);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " enabled monster spawning");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 9:
                    settings.getScore("monsters").setScore(0);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " disabled monster spawning");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 10:
                    settings.getScore("regen").setScore(1);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " enabled natural health regeneration");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 11:
                    settings.getScore("regen").setScore(0);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " disabled natural health regeneration");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 12:
                    settings.getScore("saturation").setScore(1);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " enabled infinite saturation");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 13:
                    settings.getScore("saturation").setScore(0);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " disabled infinite saturation");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 14:
                    settings.getScore("spawn").setScore(0);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " disabled spawn safety");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 15:
                    settings.getScore("spawn").setScore(1);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " enabled 1 second spawn safety");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 16:
                    settings.getScore("spawn").setScore(2);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " enabled 3 second spawn safety");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 17:
                    settings.getScore("warn").setScore(0);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " disabled swap warning");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 18:
                    settings.getScore("warn").setScore(1);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " enabled 5 second swap warning");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
                case 19:
                    settings.getScore("warn").setScore(2);
                    Bukkit.broadcastMessage(ChatColor.BLUE + name + " enabled 10 second swap warning");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
                    break;
            }
            Objects.requireNonNull(sm.getMainScoreboard().getObjective("triggers")).getScore(name).setScore(0);
        }
    }
}
