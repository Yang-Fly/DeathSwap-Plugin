package me.yang.deathswap.Tasks;

import com.destroystokyo.paper.Title;
import me.yang.deathswap.DeathSwap;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.*;

public class StartTask extends BukkitRunnable {
    private final DeathSwap plugin;
    public StartTask(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();

        String bluePlayerName = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).getEntries().toString();
        String greenPlayerName = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().toString();

        Player bluePlayer = Bukkit.getPlayer(bluePlayerName);
        Player greenPlayer = Bukkit.getPlayer(greenPlayerName);

        Collection<Player> playPlayers = new ArrayList<>(Arrays.asList(bluePlayer, greenPlayer));

        if (Bukkit.getOnlinePlayers().size() < 2) {
            Bukkit.broadcastMessage(ChatColor.RED + "There must be at least two players in the Server");
        } else if (playPlayers.size() == 2) {
            if (Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).getEntries().size() == 1 &&
                    Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().size() == 1) {

                Bukkit.broadcastMessage("Preparing to start the game.");

                assert bluePlayer != null;
                World world = bluePlayer.getWorld();

                Bukkit.broadcastMessage(ChatColor.GOLD + "Blue Player: " + ChatColor.AQUA + bluePlayerName);
                Bukkit.broadcastMessage(ChatColor.GOLD + "Green Player: " + ChatColor.GREEN + greenPlayerName);

                Objects.requireNonNull(bluePlayer).getScoreboardTags().add("player");
                Objects.requireNonNull(greenPlayer).getScoreboardTags().add("player");
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setTime(3000);

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
                    world.setDifficulty(Difficulty.PEACEFUL);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").getScore() == 1) {
                    world.setDifficulty(Difficulty.EASY);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").getScore() == 2) {
                    world.setDifficulty(Difficulty.NORMAL);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("difficulty").getScore() == 3) {
                    world.setDifficulty(Difficulty.HARD);
                }

                if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("monsters").getScore() == 0) {
                    world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("monsters").getScore() == 1) {
                    world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
                }

                if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("regen").getScore() == 0) {
                    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
                } else if (Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("regen").getScore() == 1) {
                    world.setGameRule(GameRule.NATURAL_REGENERATION, true);
                }

                Objects.requireNonNull(sm.getMainScoreboard().getObjective("game_state")).getScore("game_state").setScore(1);
                bluePlayer.playSound(bluePlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                greenPlayer.playSound(greenPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

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
