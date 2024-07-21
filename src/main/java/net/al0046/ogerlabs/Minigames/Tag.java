package net.al0046.ogerlabs.Minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Tag extends JavaPlugin implements Listener {

    private final Set<UUID> playersInGame = new HashSet<>();
    private UUID itPlayer;
@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("starttag")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (playersInGame.isEmpty()) {
                    playersInGame.add(player.getUniqueId());
                    itPlayer = player.getUniqueId();
                    player.sendMessage(ChatColor.GREEN + "You are 'It'! Tag someone!");
                } else {
                    player.sendMessage(ChatColor.RED + "A game is already in progress.");
                }
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("jointag")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!playersInGame.contains(player.getUniqueId())) {
                    playersInGame.add(player.getUniqueId());
                    player.sendMessage(ChatColor.GREEN + "You have joined the Tag game!");
                } else {
                    player.sendMessage(ChatColor.RED + "You are already in the game.");
                }
                return true;
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("endtag")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (playersInGame.contains(player.getUniqueId())) {
                    playersInGame.clear();
                    itPlayer = null;
                    Bukkit.broadcastMessage(ChatColor.RED + "The Tag game has ended!");
                } else {
                    player.sendMessage(ChatColor.RED + "You are not in the game.");
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
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player tagger = event.getPlayer();
            Player tagged = (Player) event.getRightClicked();

            if (playersInGame.contains(tagger.getUniqueId()) && playersInGame.contains(tagged.getUniqueId())) {
                if (tagger.getUniqueId().equals(itPlayer)) {
                    itPlayer = tagged.getUniqueId();
                    tagged.sendMessage(ChatColor.RED + "You are now 'Luluta Mannen'! Tag someone else like a oger!");
                    tagger.sendMessage(ChatColor.GREEN + "You tagged " + tagged.getName() + "!");
                } else {
                    tagger.sendMessage(ChatColor.RED + "You are not 'Luluta Mannen'!");
                }
            }
        }
    }
}