package me.yang.deathswap;

import me.yang.deathswap.Tasks.Help;
import me.yang.deathswap.Tasks.JoinTeamTask;
import me.yang.deathswap.Tasks.StartTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

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
        /*
        help    deathswap.player
        blue    deathswap.player
        green   deathswap.player

        start   deathswap.admin
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
                new StartTask(plugin).runTaskAsynchronously(plugin);
                return true;
            }
            if (args[0].equals("blue") || args[0].equals("green")) {
                new JoinTeamTask(args[0], sender.getName()).runTaskAsynchronously(plugin);
                return true;
            }
            sender.sendMessage("Unknown command. Type \"/deathswap help\" for help.");
            return true;
        } else if (sender.hasPermission("deathswap.player") && args.length == 1) {
            if (args[0].equals("blue") || args[0].equals("green")) {
                new JoinTeamTask(args[0], sender.getName()).runTaskAsynchronously(plugin);
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
        }
        return result;
    }
}