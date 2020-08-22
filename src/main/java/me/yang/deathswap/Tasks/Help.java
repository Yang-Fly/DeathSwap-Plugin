package me.yang.deathswap.Tasks;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.ChatColor;

public class Help extends BukkitRunnable {
    private final CommandSender sender;
    public Help(CommandSender sender) {
        this.sender = sender;
    }

    public void run() {
        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "DeathSwap " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.LIGHT_PURPLE + " Plugin Version v1.0 by Xray_Yang");
        sender.sendMessage(ChatColor.GRAY + "/deathswap " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.RESET + ChatColor.AQUA + " Show this help.");
        sender.sendMessage(ChatColor.GRAY + "/deathswap help " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.RESET + ChatColor.AQUA + " Show this help.");
        sender.sendMessage(ChatColor.GRAY + "/deathswap blue " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.RESET + ChatColor.AQUA + " Join the Blue Team.");
        sender.sendMessage(ChatColor.GRAY + "/deathswap green " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.RESET + ChatColor.AQUA + " Join the Green Team.");
        sender.sendMessage(ChatColor.GRAY + "/deathswap start " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.RESET + ChatColor.AQUA + " Start the game.");
        sender.sendMessage(ChatColor.GRAY + "/deathswap reset " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.RESET + ChatColor.AQUA + " Reset the settings.");
        sender.sendMessage(ChatColor.GRAY + "/deathswap reload " + ChatColor.RESET + ChatColor.GREEN + "-" + ChatColor.RESET + ChatColor.AQUA + " Reload the settings from the config.");
    }
}
