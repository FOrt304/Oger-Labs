package net.al0046.ogerlabs.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import net.al0046.ogerlabs.OgerLabs;
import net.al0046.ogerlabs.Utils.ColorUtils;

public class MSGReloadConfigCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    public MSGReloadConfigCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            sender.sendMessage(ColorUtils.translateColorCodes(OgerLabs.getInstance().getConfig().getString("messages.error")));
            return false;
        }
        plugin.reloadConfig();
        sender.sendMessage(ColorUtils.translateColorCodes(OgerLabs.getInstance().getConfig().getString("messages.configreloadsuccessfully")));
        return true;
    }
}