package me.yang.deathswap;

import me.yang.deathswap.Tasks.Help;
import me.yang.deathswap.Tasks.JoinTeamTask;
import me.yang.deathswap.Tasks.Reset.ResetTask;
import me.yang.deathswap.Tasks.StartTask;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandsExecutor implements TabExecutor{
    private final DeathSwap plugin;
    private final List<String> SubCommandsForPlayer = new ArrayList<>();
    private final List<String> SubCommandsForAdmin = new ArrayList<>();

    public CommandsExecutor(DeathSwap plugin) {
        this.plugin = plugin;
        SubCommandsForPlayer.add("help");
        SubCommandsForPlayer.add("blue");
        SubCommandsForPlayer.add("green");

        SubCommandsForAdmin.addAll(SubCommandsForPlayer);
        SubCommandsForAdmin.add("start");
        SubCommandsForAdmin.add("reset");
        /*
        help    deathswap.player
        blue    deathswap.player
        green   deathswap.player

        start   deathswap.admin
        reset   deathswap.admin
         */
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1 && !sender.hasPermission("deathswap.admin")) {
            sender.sendMessage("Unknown command. Type \"/deathswap help\" for help.");
            return true;
        } else if (args.length == 0 || args[0].equals("help")){
            new Help(sender).runTask(plugin);
            return true;
        } else if (sender.hasPermission("deathswap.admin") && args.length == 1){
            if (args[0].equals("start")) {
                new StartTask(plugin, sender).runTaskAsynchronously(plugin);
                return true;
            }
            if (args[0].equals("reset")) {
                new ResetTask(plugin).runTaskAsynchronously(plugin);
                return true;
            }
            if (args[0].equals("blue") || args[0].equals("green")) {
                new JoinTeamTask(sender, args[0], sender.getName()).runTaskAsynchronously(plugin);
                return true;
            }
            sender.sendMessage("Unknown command. Type \"/deathswap help\" for help.");
            return true;
        } else if ((args[0].equals("blue") || args[0].equals("green")) && args.length == 2) {
            new JoinTeamTask(sender, args[0], args[1]).runTaskAsynchronously(plugin);
            return true;
        } else if (sender.hasPermission("deathswap.player") && args.length == 1) {
            if (args[0].equals("blue") || args[0].equals("green")) {
                new JoinTeamTask(sender, args[0], sender.getName()).runTaskAsynchronously(plugin);
                return true;
            }
            sender.sendMessage("Unknown command. Type \"/deathswap help\" for help.");
            return true;
        }
        sender.sendMessage("You do not have permission to execute this command!");
        StringBuilder builder = new StringBuilder();
        builder.append(command);
        for (String arg : args) {
            builder.append(" ");
            builder.append(arg);
        }
        plugin.getLogger().warning(sender.getName() + " tried to execute command: " + builder);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> result = new ArrayList<>();
        if (sender.hasPermission("deathswap.admin") && args.length == 1) {
            result.addAll(SubCommandsForAdmin);
            return result;
        } else if (sender.hasPermission("deathswap.player") && args.length == 1){
            result.addAll(SubCommandsForPlayer);
            return result;
        } else if (args.length == 2 && (args[0].equals("blue") || args[0].equals("green")) && sender.hasPermission("deathswap.admin")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                String name = player.getName();
                result.add(name);
            }
            return result;
        }
        return result;
    }
}