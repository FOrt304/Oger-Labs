package net.al0046.ogerlabs.Minigames;

import net.al0046.ogerlabs.Minigames.Logic.ChessGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Chess extends JavaPlugin implements Listener {

    private final Map<UUID, ChessGame> activeGames = new HashMap<>();

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("startchess")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (activeGames.containsKey(player.getUniqueId())) {
                    player.sendMessage("You are already in a game!");
                } else {
                    ChessGame game = new ChessGame(player);
                    activeGames.put(player.getUniqueId(), game);
                    player.sendMessage("Chess game started! Right-click on a block to place the board.");
                }
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("endchess")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (activeGames.containsKey(player.getUniqueId())) {
                    activeGames.remove(player.getUniqueId());
                    player.sendMessage("Your chess game has ended.");
                } else {
                    player.sendMessage("You are not in a game!");
                }
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (activeGames.containsKey(player.getUniqueId())) {
            ChessGame game = activeGames.get(player.getUniqueId());
            game.placeBoard(player);
            event.setCancelled(true);
        }
    }
}
