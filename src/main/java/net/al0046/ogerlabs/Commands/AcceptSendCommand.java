package net.al0046.ogerlabs.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.al0046.ogerlabs.OgerLabs;
import net.al0046.ogerlabs.Utils.ColorUtils;
import net.al0046.ogerlabs.Utils.GeneralUtils;
import net.al0046.ogerlabs.Utils.SqliteDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AcceptSendCommand implements CommandExecutor {
    private SqliteDriver sql;
    public AcceptSendCommand(SqliteDriver sql) {
        this.sql = sql;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        if (args.length != 0) {
            return false;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        try {
            if (OgerLabs.getInstance().offlineReceiver.containsKey(uuid) && OgerLabs.getInstance().offlineMessages.containsKey(uuid)) {
                String playerReceiver = OgerLabs.getInstance().offlineReceiver.get(uuid);
                String msgOffline = OgerLabs.getInstance().offlineMessages.get(uuid);

                Map<String, Object> insertMap = new HashMap<>();
                insertMap.put("Sender", player.getName());
                insertMap.put("Receiver", playerReceiver);
                insertMap.put("Message", msgOffline);
                sql.sqlInsertData("OFFLINE_MSG", insertMap);

                sender.sendMessage(ColorUtils.translateColorCodes(OgerLabs.getInstance().getConfig().getString("messages.msgsendofflinesuccessfully")));
                GeneralUtils.msgPlaySound(sql, player);

                OgerLabs.getInstance().offlineReceiver.remove(uuid, playerReceiver);
                OgerLabs.getInstance().offlineMessages.remove(uuid, msgOffline);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return true;
    }
}