package net.al0046.ogerlabs.Minigames.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SupplyCrates extends JavaPlugin implements CommandExecutor {

    private final List<Location> crateLocations = new ArrayList<>();
    private final Random random = new Random();
@Override
    public void onEnable() {

        // Define some fixed locations for crates (this can be expanded or randomized)
        crateLocations.add(new Location(Bukkit.getWorld("world"), 100, 64, 100));
        crateLocations.add(new Location(Bukkit.getWorld("world"), 200, 64, 200));
        crateLocations.add(new Location(Bukkit.getWorld("world"), 300, 64, 300));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawncrate")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location location = crateLocations.get(random.nextInt(crateLocations.size()));
                spawnCrate(location);
                player.sendMessage("A supply crate has been spawned at: " + location.toString());
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return false;
    }

    private void spawnCrate(Location location) {
        Block block = location.getBlock();
        block.setType(Material.CHEST);
        if (block.getState() instanceof Chest) {
            Chest chest = (Chest) block.getState();
            Inventory inventory = chest.getBlockInventory();
            inventory.clear();
            inventory.addItem(new ItemStack(Material.DIAMOND, 5));
            inventory.addItem(new ItemStack(Material.GOLDEN_APPLE, 3));
            inventory.addItem(new ItemStack(Material.IRON_SWORD, 1));
            // Add more items as needed
        }
    }
}
