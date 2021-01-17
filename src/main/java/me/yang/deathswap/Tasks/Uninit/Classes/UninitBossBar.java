package me.yang.deathswap.Tasks.Uninit.Classes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;

public class UninitBossBar {
    public void run() {
        BossBar green = Bukkit.getBossBar(NamespacedKey.minecraft("green"));
        BossBar blue = Bukkit.getBossBar(NamespacedKey.minecraft("blue"));
        try {
            blue.setVisible(false);
            green.setVisible(false);
        } catch (NullPointerException exception) {
            Bukkit.getLogger().warning("Catch NullPointerException at UninitBossbar");
            Bukkit.getLogger().warning(exception.getLocalizedMessage());
        }
        Bukkit.removeBossBar(NamespacedKey.minecraft("green"));
        Bukkit.removeBossBar(NamespacedKey.minecraft("blue"));
    }
}
