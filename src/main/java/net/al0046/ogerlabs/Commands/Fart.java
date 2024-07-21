package net.al0046.ogerlabs.Commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fart extends JavaPlugin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fart")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("You farted!");
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_COW_AMBIENT, 1.0f, 1.0f); // Choose a more appropriate sound
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return false;
    }
}