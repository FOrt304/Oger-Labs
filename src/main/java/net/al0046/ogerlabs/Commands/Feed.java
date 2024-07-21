package net.al0046.ogerlabs.Commands;

import net.al0046.ogerlabs.OgerLabs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("feed.use")){
                if (args.length == 0){
                    player.setFoodLevel(20);
                    player.sendMessage(OgerLabs.prefix + OgerLabs.feedmessage);

                }else if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission("feed.target")){
                        if (target != null){
                            player.setFoodLevel(20);
                            target.sendMessage(OgerLabs.prefix + OgerLabs.gotfed);
                            player.sendMessage(OgerLabs.prefix + OgerLabs.fedother);
                        }else {
                            player.sendMessage(OgerLabs.prefix + OgerLabs.playeroffline);
                        }
                    }else {
                        sender.sendMessage(OgerLabs.prefix + OgerLabs.noperms);
                    }
                }else {
                    sender.sendMessage(OgerLabs.prefix + "§c/feed <player>");
                }
            }else {
                sender.sendMessage(OgerLabs.prefix + OgerLabs.noperms);
            }
        }else {
            sender.sendMessage(OgerLabs.prefix + "You can't use this command in the console!");
        }

        return false;
    }
}