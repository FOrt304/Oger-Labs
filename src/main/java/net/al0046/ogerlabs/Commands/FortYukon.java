package net.al0046.ogerlabs.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FortYukon extends JavaPlugin implements CommandExecutor {
@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fortyukon")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("Fort Yukon is a city in the Yukon-Koyukuk Census Area, Alaska, United States. It is located on the north bank of the Yukon River.");
                player.sendMessage("Fort Yukon has a population of about 583.000 people.");
                player.sendMessage("It is known for its extreme weather conditions, often recording some of the highest and lowest temperatures in Alaska.");
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return false;
    }
}