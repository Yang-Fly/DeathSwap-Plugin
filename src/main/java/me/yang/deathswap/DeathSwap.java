package me.yang.deathswap;

import me.yang.deathswap.Tasks.EntitySpawnListener;
import me.yang.deathswap.Tasks.Init.InitTask;
import me.yang.deathswap.Tasks.Loop;
import me.yang.deathswap.Tasks.Uninit.UninitTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.HandlerList;

import java.util.Objects;

public final class DeathSwap extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        new InitTask(this).runTask(this);
        CommandsExecutor executor = new CommandsExecutor(this);
        Objects.requireNonNull(this.getCommand("deathswap")).setExecutor(executor);
        Objects.requireNonNull(this.getCommand("deathswap")).setTabCompleter(executor);
        new Loop(this).runTaskTimer(this, 40, 1);
        getServer().getPluginManager().registerEvents(new EntitySpawnListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        new UninitTask().run();
        HandlerList.unregisterAll(this);
    }
}
