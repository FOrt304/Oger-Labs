package net.al0046.ogerlabs.Commands;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCat extends JavaPlugin implements CommandExecutor {
@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawncat")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location location = player.getLocation();
                spawnCustomCat(location);
                player.sendMessage("A cat named King Yuki of Fort Yukon has been spawned!");
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return false;
    }

    private void spawnCustomCat(Location location) {
        Cat cat = (Cat) location.getWorld().spawnEntity(location, EntityType.CAT);
        cat.setCustomName("♚ᴋɪɴɢ ʏᴜᴋɪ\uD83D\uDC31");
        cat.setCustomNameVisible(true);
        cat.setCatType(Cat.Type.JELLIE);
        cat.setCollarColor(DyeColor.LIGHT_BLUE);
        cat.setTamed(true);
    }
}
