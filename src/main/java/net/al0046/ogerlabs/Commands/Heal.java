package net.al0046.ogerlabs.Commands;

import net.al0046.ogerlabs.OgerLabs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("heal.use")){
                if (args.length == 0){
                    player.setHealthScale(20);
                    player.setHealth(20);
                    player.sendMessage(OgerLabs.prefix + OgerLabs.healmessage);

                }else if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission("heal.target")){
                        if (target != null){
                            target.setHealthScale(20);
                            target.setHealth(20);
                            target.sendMessage(OgerLabs.prefix + OgerLabs.gothealed);
                            player.sendMessage(OgerLabs.prefix + OgerLabs.healedother);
                        }else {
                            player.sendMessage(OgerLabs.prefix + OgerLabs.playeroffline);
                        }
                    }else {
                        sender.sendMessage(OgerLabs.prefix + OgerLabs.noperms);
                    }
                }else {
                    sender.sendMessage(OgerLabs.prefix + "§c/heal <player>");
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