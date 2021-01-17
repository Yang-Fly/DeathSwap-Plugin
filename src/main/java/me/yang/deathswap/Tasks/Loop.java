package me.yang.deathswap.Tasks;

import com.destroystokyo.paper.ParticleBuilder;
import me.yang.deathswap.DeathSwap;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.*;

public class Loop extends BukkitRunnable {
    private final DeathSwap plugin;
    public Loop(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() throws NullPointerException, IllegalArgumentException{
        ScoreboardManager sm = Bukkit.getScoreboardManager();

        Objective timerObjective = sm.getMainScoreboard().getObjective("timer");
        Objective deaths = sm.getMainScoreboard().getObjective("deaths");
        Objective settings = sm.getMainScoreboard().getObjective("settings");

        int game_state = Objects.requireNonNull(sm.getMainScoreboard().getObjective("game_state")).getScore("game_state").getScore();

        int warn = Objects.requireNonNull(settings).getScore("warn").getScore();
        int saturation = Objects.requireNonNull(settings).getScore("saturation").getScore();

        int timer = Objects.requireNonNull(timerObjective).getScore("timer").getScore();
        int limit = Objects.requireNonNull(timerObjective).getScore("limit").getScore();
        int elapsed = Objects.requireNonNull(timerObjective).getScore("elapsed").getScore();
        int tickrate = Objects.requireNonNull(timerObjective).getScore("tickrate").getScore();
        int seconds = Objects.requireNonNull(timerObjective).getScore("seconds").getScore();
        int startseconds = Objects.requireNonNull(timerObjective).getScore("startseconds").getScore();

        BossBar green = Bukkit.getBossBar(NamespacedKey.minecraft("green"));
        BossBar blue = Bukkit.getBossBar(NamespacedKey.minecraft("blue"));

        if (game_state == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                PotionEffect pe = new PotionEffect(PotionEffectType.SATURATION, 2, 0, true);
                player.addPotionEffect(pe);
            }
            new Trigger(plugin).runTask(plugin);

            for (Player player : Bukkit.getOnlinePlayers()) {
                String name = player.getName();
                if (!player.getInventory().contains(Material.WRITTEN_BOOK)) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute if score game_state game_state matches 0 " +
                            "run replaceitem entity " + name + " hotbar.0 written_book{pages:[\"[\\\"\\\",{\\\"text\\\":\\\"      \\\"},{\\\"t" +
                            "ext\\\":\\\"Death Swap\\\",\\\"bold\\\":true,\\\"underlined\\\":true,\\\"color\\\":\\\"blue\\\"},{\\\"tex" +
                            "t\\\":\\\"\\\\n\\\\n\\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Rules\\\",\\\"color\\\":\\\"red\\\"" +
                            ",\\\"clickEvent\\\":{\\\"action\\\":\\\"change_page\\\",\\\"value\\\":2}},{\\\"text\\\":\\\"\\\\n\\\",\\\"" +
                            "color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Help\\\",\\\"color\\\":\\\"dark_aqua\\\",\\\"clickEvent\\\":{\\\"a" +
                            "ction\\\":\\\"change_page\\\",\\\"value\\\":4}},{\\\"text\\\":\\\"\\\\n\\\",\\\"color\\\":\\\"reset\\\"},{\\\"t" +
                            "ext\\\":\\\"Settings\\\",\\\"color\\\":\\\"light_purple\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"change_page\\" +
                            "\",\\\"value\\\":6}},{\\\"text\\\":\\\"\\\\n\\\\n\\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Join Blu" +
                            "e\\\",\\\"color\\\":\\\"aqua\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/tri" +
                            "gger triggers set 1\\\"}},{\\\"text\\\":\\\"\\\\n\\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Join Green\\" +
                            "\",\\\"color\\\":\\\"green\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger" +
                            " triggers set 2\\\"}},{\\\"text\\\":\\\"\\\\n\\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Begin!\\\",\\\"" +
                            "color\\\":\\\"dark_purple\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger" +
                            " triggers set 3\\\"}},{\\\"text\\\":\\\"\\\\n\\\\n\\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Made by:\\" +
                            "\\n\\\",\\\"color\\\":\\\"black\\\"},{\\\"text\\\":\\\"/u/Maximus3-141\\\",\\\"bold\\\":true,\\\"color\\\":\\\"b" +
                            "lack\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"open_url\\\",\\\"value\\\":\\\"https://www.reddit.com/user/Maximus" +
                            "3-141\\\"}},{\\\"text\\\":\\\"\\\\nOriginal Creator:\\\\n\\\",\\\"color\\\":\\\"black\\\"},{\\\"text\\\":\\\"SethB" +
                            "ling\\\",\\\"bold\\\":true,\\\"color\\\":\\\"black\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"open_url\\\",\\\"val" +
                            "ue\\\":\\\"https://www.youtube.com/channel/UC8aG3LDTDwNR1UQhSn9uVrw\\\"}}]\",\"[\\\"\\\",{\\\"text\\\":\\\"       " +
                            "   \\\"},{\\\"text\\\":\\\"Rules\\\",\\\"bold\\\":true,\\\"underlined\\\":true,\\\"color\\\":\\\"blue\\\",\\\"click" +
                            "Event\\\":{\\\"action\\\":\\\"change_page\\\",\\\"value\\\":1}},{\\\"text\\\":\\\"\\\\n\\\\nDeathswap is a game whe" +
                            "re two players duke it out, trying to be the last one alive!\\\\n\\\\nPlay begins with both players starting a fres" +
                            "h survival experience, thousands of blocks apart.\\\",\\\"color\\\":\\\"reset\\\"}]\",\"{\\\"text\\\":\\\"Randomly," +
                            " every 30 to 150 seconds, the players will switch positions, without any warning. The aim is to put yourself in a p" +
                            "osition that would result in your opponent's death, should a swap occur!\\\"}\",\"[\\\"\\\",{\\\"text\\\":\\\"     " +
                            "      \\\"},{\\\"text\\\":\\\"Help\\\",\\\"bold\\\":true,\\\"underlined\\\":true,\\\"color\\\":\\\"blue\\\",\\\"cli" +
                            "ckEvent\\\":{\\\"action\\\":\\\"change_page\\\",\\\"value\\\":1}},{\\\"text\\\":\\\"\\\\n\\\\nTo begin a game, simp" +
                            "ly join a team by clicking on one of the options on the first page of this book. Only one player can be on one team" +
                            " at a time! When both teams are occupied, click begin to start the game! There are several difficulty settings that" +
                            " can be\\\",\\\"color\\\":\\\"reset\\\"}]\",\"[\\\"\\\",{\\\"text\\\":\\\"toggled to customise your gameplay. Chang" +
                            "e these with the \\\"},{\\\"text\\\":\\\"Settings\\\",\\\"underlined\\\":true,\\\"color\\\":\\\"light_purple\\\",\\" +
                            "\"clickEvent\\\":{\\\"action\\\":\\\"change_page\\\",\\\"value\\\":6}},{\\\"text\\\":\\\" button. If there are more" +
                            " than two players in the world, they will be put into spectator mode.\\\",\\\"color\\\":\\\"reset\\\"}]\",\"[\\\"\\" +
                            "\",{\\\"text\\\":\\\"        \\\"},{\\\"text\\\":\\\"Settings\\\",\\\"bold\\\":true,\\\"underlined\\\":true,\\\"col" +
                            "or\\\":\\\"blue\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"change_page\\\",\\\"value\\\":1}},{\\\"text\\\":\\\"\\\\" +
                            "n\\\\nDifficulty: \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"0\\\",\\\"color\\\":\\\"light_purple\\\",\\\"" +
                            "clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set 4\\\"}},{\\\"text\\\":\\" +
                            "\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"1\\\",\\\"color\\\":\\\"light_purple\\\",\\\"clickEvent\\\"" +
                            ":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set 5\\\"}},{\\\"text\\\":\\\", \\\",\\\"c" +
                            "olor\\\":\\\"reset\\\"},{\\\"text\\\":\\\"2\\\",\\\"color\\\":\\\"light_purple\\\",\\\"clickEvent\\\":{\\\"action\\" +
                            "\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set 6\\\"}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"r" +
                            "eset\\\"},{\\\"text\\\":\\\"3\\\",\\\"color\\\":\\\"light_purple\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_com" +
                            "mand\\\",\\\"value\\\":\\\"/trigger triggers set 7\\\"}},{\\\"text\\\":\\\"\\\\n\\\\nMonsters: \\\",\\\"color\\\":\\" +
                            "\"reset\\\"},{\\\"text\\\":\\\"On\\\",\\\"color\\\":\\\"dark_green\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_c" +
                            "ommand\\\",\\\"value\\\":\\\"/trigger triggers set 8\\\"}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"" +
                            "text\\\":\\\"Off\\\",\\\"color\\\":\\\"red\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"" +
                            "/trigger triggers set 9\\\"}},{\\\"text\\\":\\\"\\\\n\\\\nRegeneration: \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":" +
                            "\\\"On\\\",\\\"color\\\":\\\"dark_green\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"" +
                            "/trigger triggers set 10\\\"}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Off\\\",\\\"c" +
                            "olor\\\":\\\"red\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set" +
                            " 11\\\"}},{\\\"text\\\":\\\"\\\\n\\\\nSaturation: \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"On\\\",\\\"co" +
                            "lor\\\":\\\"dark_green\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger trigg" +
                            "ers set 12\\\"}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"Off\\\",\\\"color\\\":\\\"" +
                            "red\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set 13\\\"}},{\\\"t" +
                            "ext\\\":\\\"\\\\n \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"\\\\nSafe Spawn: \\\",\\\"color\\\":\\\"reset\\\"" +
                            "},{\\\"text\\\":\\\"0s\\\",\\\"color\\\":\\\"red\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\" +
                            "\":\\\"/trigger triggers set 14\\\"}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"1s\\\",\\" +
                            "\"color\\\":\\\"dark_green\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger trigge" +
                            "rs set 15\\\"}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"3s\\\",\\\"color\\\":\\\"dark_gr" +
                            "een\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set 16\\\"}},{\\\"tex" +
                            "t\\\":\\\"\\\\n \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"\\\\nWarning: \\\",\\\"color\\\":\\\"reset\\\"},{\\\"" +
                            "text\\\":\\\"0s\\\",\\\"color\\\":\\\"red\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"" +
                            "/trigger triggers set 17\\\"}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"5s\\\",\\\"color\\" +
                            "\":\\\"dark_green\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set 18\\\"" +
                            "}},{\\\"text\\\":\\\", \\\",\\\"color\\\":\\\"reset\\\"},{\\\"text\\\":\\\"10s\\\",\\\"color\\\":\\\"dark_green\\\",\\\"cl" +
                            "ickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/trigger triggers set 19\\\"}},{\\\"text\\\":\\\"\\\\n " +
                            "\\\",\\\"color\\\":\\\"reset\\\"}]\"],title:DeathSwap,author:\"Maximus3-141\"} 1");
                }
            }
        } else if (game_state == 1) {
            String bluePlayerName = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Blue")).getEntries().toString();
            String greenPlayerName = Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().toString();

            Player bluePlayer = Bukkit.getPlayer(bluePlayerName);
            Player greenPlayer = Bukkit.getPlayer(greenPlayerName);

            int blueDeaths = Objects.requireNonNull(deaths).getScore(bluePlayerName).getScore();
            int greenDeaths = Objects.requireNonNull(deaths).getScore(greenPlayerName).getScore();

            double bluePlayerHealth = Objects.requireNonNull(bluePlayer).getHealth();
            double greenPlayerHealth = Objects.requireNonNull(greenPlayer).getHealth();

            Collection<Player> playPlayers = new ArrayList<>(Arrays.asList(bluePlayer, greenPlayer));

            Objects.requireNonNull(bluePlayer).setGameMode(GameMode.SURVIVAL);
            Objects.requireNonNull(greenPlayer).setGameMode(GameMode.SURVIVAL);

            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            onlinePlayers.removeAll(playPlayers);

            for (Player player : onlinePlayers) {
                player.setGameMode(GameMode.SPECTATOR);
            }

            if (warn == 1) {
                warn(timer, bluePlayer, greenPlayer);
            } else if (warn == 2) {
                if (timer == 200) {
                    Bukkit.broadcastMessage(ChatColor.BLUE + "A swap will occur in 10 seconds!");
                    bluePlayer.playSound(bluePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    greenPlayer.playSound(greenPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }
                warn(timer, bluePlayer, greenPlayer);
            }

            if (timer == 0) {
                new Swap().runTask(plugin);
                bluePlayer.playSound(bluePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                greenPlayer.playSound(greenPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
            }

            if (timer > limit) {
                Objects.requireNonNull(timerObjective).getScore("timer").setScore(timer - 1);
                Objects.requireNonNull(timerObjective).getScore("elapsed").setScore(elapsed + 1);
            }

            if (elapsed == tickrate) {
                Objects.requireNonNull(timerObjective).getScore("seconds").setScore(seconds + 1);
                Objects.requireNonNull(timerObjective).getScore("elapsed").setScore(1);
            }

            Objects.requireNonNull(green).setProgress(greenPlayerHealth);
            Objects.requireNonNull(blue).setProgress(bluePlayerHealth);

            if (greenDeaths == 1) {
                new End(plugin, bluePlayerName, greenPlayerName, "blue");
            } else if (blueDeaths == 1) {
                new End(plugin, greenPlayerName, bluePlayerName, "green");
            }

            if (saturation == 1) {
                bluePlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 0, true));
                greenPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 0, true));
            }

            if (seconds < startseconds) {
                bluePlayer.sendActionBar(ChatColor.DARK_GREEN + "Time since swap: " + seconds + "  [Safe]");
                greenPlayer.sendActionBar(ChatColor.DARK_GREEN + "Time since swap: " + seconds + "  [Safe]");
            } else {
                bluePlayer.sendActionBar(ChatColor.RED + "Time since swap: " + seconds + "  [Unsafe]");
                greenPlayer.sendActionBar(ChatColor.RED + "Time since swap: " + seconds + "  [Unsafe]");
            }
        }
        //Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute at @a[tag=victor] run particle minecraft:dust 255 255 255 1 ~ ~1 ~ 0.5 0.5 0.5 1 1 normal @a");

        List<String> victor = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            Set<String> playerTags = player.getScoreboardTags();
            if (playerTags.contains("victor")) {
                victor.add(player.getName());
            }
        }
        ParticleBuilder pb = new ParticleBuilder(Particle.REDSTONE);
        Collection<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (String name : victor) {
            Player victorPlayer = Bukkit.getPlayer(name);
            assert victorPlayer != null;
            pb.receivers(onlinePlayers);
            pb.location(victorPlayer.getLocation());
            pb.color(Color.AQUA);
            pb.force(false);
            pb.spawn();
        }
    }

    private void warn(int timer, Player bluePlayer, Player greenPlayer) {
        if (timer == 100) {
            Bukkit.broadcastMessage(ChatColor.BLUE + "A swap will occur in 5 seconds!");
            bluePlayer.playSound(bluePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            greenPlayer.playSound(greenPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
        if (timer == 80) {
            Bukkit.broadcastMessage(ChatColor.BLUE + "A swap will occur in 4 seconds!");
            bluePlayer.playSound(bluePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            greenPlayer.playSound(greenPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
        if (timer == 60) {
            Bukkit.broadcastMessage(ChatColor.BLUE + "A swap will occur in 3 seconds!");
            bluePlayer.playSound(bluePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            greenPlayer.playSound(greenPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
        if (timer == 40) {
            Bukkit.broadcastMessage(ChatColor.BLUE + "A swap will occur in 2 seconds!");
            bluePlayer.playSound(bluePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            greenPlayer.playSound(greenPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
        if (timer == 20) {
            Bukkit.broadcastMessage(ChatColor.BLUE + "A swap will occur in 1 seconds!");
            bluePlayer.playSound(bluePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            greenPlayer.playSound(greenPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
    }
}