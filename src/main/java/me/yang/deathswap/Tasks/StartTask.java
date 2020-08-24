package me.yang.deathswap.Tasks;

import com.destroystokyo.paper.Title;
import me.yang.deathswap.DeathSwap;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class StartTask extends BukkitRunnable {
    private final DeathSwap plugin;
    public StartTask(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Collection<? extends Player> player = Bukkit.getOnlinePlayers();
        if (player.size() < 2) {
            Bukkit.broadcastMessage(ChatColor.RED + "There must be at least two players in the server!");
        } else if (player.size() == 2) {
            if (Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).getEntries().size() == 1 &&
                    Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().size() == 1) {

                Bukkit.broadcastMessage("Prepareing to start the game.");

                String bluePlayerName = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).getEntries().toString();
                String greenPlayerName = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().toString();
                Player bluePlayer = Bukkit.getPlayer(bluePlayerName);
                Player greenPlayer = Bukkit.getPlayer(greenPlayerName);

                Bukkit.broadcastMessage(ChatColor.GOLD + "Blue Player: " + ChatColor.AQUA + bluePlayerName);
                Bukkit.broadcastMessage(ChatColor.GOLD + "Green Player: " + ChatColor.GREEN + greenPlayerName);

                Objects.requireNonNull(bluePlayer).getScoreboardTags().add("player");
                Objects.requireNonNull(greenPlayer).getScoreboardTags().add("player");
                Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setTime(3000);

                for (OfflinePlayer playerName : Bukkit.getOfflinePlayers()) {
                    Player name = playerName.getPlayer();
                    if (name != null) {
                        Set<String> tags = name.getScoreboardTags();
                        tags.remove("victor");
                    }
                }

                BossBar green = Bukkit.getBossBar(NamespacedKey.minecraft("green"));
                BossBar blue = Bukkit.getBossBar(NamespacedKey.minecraft("blue"));
                Objects.requireNonNull(green).setVisible(true);
                Objects.requireNonNull(blue).setVisible(true);

                if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").getScore() == 0) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setDifficulty(Difficulty.PEACEFUL);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").getScore() == 1) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setDifficulty(Difficulty.EASY);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").getScore() == 2) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setDifficulty(Difficulty.NORMAL);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").getScore() == 3) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setDifficulty(Difficulty.HARD);
                }

                if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("monsters").getScore() == 0) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.DO_MOB_SPAWNING, false);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("monsters").getScore() == 1) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.DO_MOB_SPAWNING, true);
                }

                if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("regen").getScore() == 0) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.NATURAL_REGENERATION, false);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("regen").getScore() == 1) {
                    Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).setGameRule(GameRule.NATURAL_REGENERATION, true);
                }

                Objects.requireNonNull(sm.getMainScoreboard().getObjective("game_state")).getScore("game_state").setScore(1);
                bluePlayer.playSound(bluePlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                greenPlayer.playSound(greenPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

                //Objects.requireNonNull(Bukkit.getPlayer("Xray_Yang")).performCommand("spreadplayers ~ ~ 2000 100000 false @a[tag=player]");
                //Objects.requireNonNull(Bukkit.getPlayer("CONSOLE")).performCommand("spreadplayers ~ ~ 2000 100000 false @a[tag=player]");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers ~ ~ 2000 100000 false @a[tag=player]");
                
                bluePlayer.getInventory().clear();
                greenPlayer.getInventory().clear();

                bluePlayer.setExp(0);
                bluePlayer.setLevel(0);
                greenPlayer.setExp(0);
                greenPlayer.setLevel(0);

                bluePlayer.setGameMode(GameMode.SURVIVAL);
                greenPlayer.setGameMode(GameMode.SURVIVAL);

                new Timer().runTask(plugin);

                Bukkit.broadcastMessage("Init Game Completed");

                Title title = new Title(ChatColor.AQUA + "" + ChatColor.BOLD + "Game Started", ChatColor.STRIKETHROUGH + "" + ChatColor.GRAY + "想办法搞死对面，gkd", 20, 100, 20);
                bluePlayer.sendTitle(title);
                greenPlayer.sendTitle(title);
            }
        }
    }
}
