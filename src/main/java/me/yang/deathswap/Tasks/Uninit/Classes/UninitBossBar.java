package me.yang.deathswap.Tasks.Uninit.Classes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;

import java.util.Objects;

public class UninitBossBar {
    public void run() {
        BossBar green = Bukkit.getBossBar(NamespacedKey.minecraft("green"));
        BossBar blue = Bukkit.getBossBar(NamespacedKey.minecraft("blue"));
        Objects.requireNonNull(green).setVisible(false);
        Objects.requireNonNull(blue).setVisible(false);
        Bukkit.removeBossBar(NamespacedKey.minecraft("green"));
        Bukkit.removeBossBar(NamespacedKey.minecraft("blue"));
    }
}
