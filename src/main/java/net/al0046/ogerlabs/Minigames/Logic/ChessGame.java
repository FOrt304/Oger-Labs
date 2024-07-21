package net.al0046.ogerlabs.Minigames.Logic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChessGame extends JavaPlugin implements CommandExecutor {

    private final Player player;

    public ChessGame(Player player) {
        this.player = player;
    }

    public void placeBoard(Player player) {
        Location location = player.getLocation();
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                Material material = (x + z) % 2 == 0 ? Material.BLACK_CONCRETE : Material.WHITE_CONCRETE;
                location.clone().add(x, 0, z).getBlock().setType(material);
            }
        }
        player.sendMessage("Chess board placed!");
    }
}