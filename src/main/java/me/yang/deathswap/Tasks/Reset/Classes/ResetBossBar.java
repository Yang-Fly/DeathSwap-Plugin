package me.yang.deathswap.Tasks.Reset.Classes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ResetBossBar extends BukkitRunnable {
    @Override
    public void run() {
        BossBar blue = Bukkit.getBossBar(NamespacedKey.minecraft("blue"));
        BossBar green = Bukkit.getBossBar(NamespacedKey.minecraft("green"));
        Objects.requireNonNull(green).setVisible(false);
        Objects.requireNonNull(blue).setVisible(false);
    }
}
