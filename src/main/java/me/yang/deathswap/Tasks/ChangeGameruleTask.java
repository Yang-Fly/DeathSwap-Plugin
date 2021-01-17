package me.yang.deathswap.Tasks;

import com.destroystokyo.paper.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ChangeGameruleTask extends BukkitRunnable {
    private final Player player;

    public ChangeGameruleTask(Player player) {
        this.player = player;
    }

    public static void gameruleSet(World world) {
        world.setTime(3000);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.NATURAL_REGENERATION, true);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Reset Complete!");
    }

    @Override
    public void run() {
        World world = player.getWorld();

        Title title = new Title(ChatColor.AQUA + "Changing Title Times", ChatColor.GREEN + "Wait for a second", 20, 100, 20);
        for (Player player : Bukkit.getOnlinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                name.updateTitle(title);
            }
        }
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            Player name = player.getPlayer();
            if (name != null) {
                name.removeScoreboardTag("victor");
            }
        }
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
            player.setExp(0);
            player.setLevel(0);
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players enable @a triggers");
        gameruleSet(world);
        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "DeathSwap Plugin Ready!");
    }
}
