package me.yang.deathswap.Tasks.Init.Classes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class InitBossBar extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.createBossBar(NamespacedKey.minecraft("green"), ChatColor.GREEN + "" + ChatColor.BOLD + "Green Player", BarColor.GREEN, BarStyle.SOLID);
        Bukkit.createBossBar(NamespacedKey.minecraft("blue"), ChatColor.AQUA + "" + ChatColor.BOLD + "Blue Player", BarColor.BLUE, BarStyle.SOLID);
        BossBar green = Bukkit.getBossBar(NamespacedKey.minecraft("green"));
        BossBar blue = Bukkit.getBossBar(NamespacedKey.minecraft("blue"));
        Objects.requireNonNull(green).setVisible(true);
        Objects.requireNonNull(blue).setVisible(true);
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                blue.addPlayer(name);
                green.addPlayer(name);
            }
        }
    }
}
