package net.al0046.ogerlabs.Items;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public class Oger extends JavaPlugin implements CommandExecutor {
@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("oger")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack opItem = createOPItem();
                player.getInventory().addItem(opItem);
                player.sendMessage("You have been given the most oger overpowered item in fort yukon! :)");
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return false;
    }

    private ItemStack createOPItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§4The Oger Blade");
        meta.addEnchant(Enchantment.SHARPNESS, 32767, true); // Sharpness enchantment
        meta.addEnchant(Enchantment.LOOTING, 32767, true); // Looting enchantment
        meta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true); // Fire Aspect enchantment
        meta.addEnchant(Enchantment.KNOCKBACK, 32767, true); // Knockback enchantment
        meta.addEnchant(Enchantment.UNBREAKING, 32767, true); // Unbreaking enchantment

        item.setItemMeta(meta);
        return item;
    }
}