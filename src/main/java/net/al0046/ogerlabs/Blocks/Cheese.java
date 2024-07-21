package net.al0046.ogerlabs.Blocks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Cheese extends JavaPlugin implements CommandExecutor {
@Override
    public void onEnable() {
        // Register custom item and recipe
        ItemStack cheese = createCheeseItem();
        NamespacedKey key = new NamespacedKey(this, "cheese");
        ShapedRecipe recipe = new ShapedRecipe(key, cheese);
        recipe.shape("MMM", "MMM", "MMM");
        recipe.setIngredient('M', Material.MILK_BUCKET);
        Bukkit.addRecipe(recipe);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cheese")) {
            if (sender instanceof org.bukkit.entity.Player) {
                org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
                player.getInventory().addItem(createCheeseItem());
                player.sendMessage("You have been given some cheese!");
                return true;
            } else {
                sender.sendMessage("This command can only be used by players.");
                return true;
            }
        }
        return false;
    }

    private ItemStack createCheeseItem() {
        ItemStack cheese = new ItemStack(Material.SPONGE);
        ItemMeta meta = cheese.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Cheese");
            List<String> lore = new ArrayList<>();
            lore.add("A tasty piece of cheese.");
            meta.setLore(lore);
            cheese.setItemMeta(meta);
        }
        return cheese;
    }
}
