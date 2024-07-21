package net.al0046.ogerlabs.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Hug extends JavaPlugin implements CommandExecutor {
@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hug")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length > 0) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null && target.isOnline()) {
                        target.sendMessage(player.getDisplayName() + " gives you a hug!");
                        player.sendMessage("You gave " + target.getDisplayName() + " a hug!");
                    } else {
                        player.sendMessage("Player not found or not online.");
                    }
                } else {
                    player.sendMessage("Usage: /hug <player>");
                }
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return false;
    }
}
