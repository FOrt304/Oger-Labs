package net.al0046.ogerlabs;

// import net.al0046.ogerlabs.HelloWorld.HelloWorld;
import net.al0046.ogerlabs.Blocks.Cheese;
import net.al0046.ogerlabs.Commands.*;
import net.al0046.ogerlabs.Minigames.Chess;
import net.al0046.ogerlabs.Minigames.Events.SupplyCrates;
import net.al0046.ogerlabs.Minigames.Tag;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import net.al0046.ogerlabs.Handler.SimpleEventHandler;
import net.al0046.ogerlabs.Utils.SqliteDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Optional;

public final class OgerLabs extends JavaPlugin {

    private static OgerLabs instance;
    private SqliteDriver sql;
    public Map<UUID, String> offlineReceiver = new HashMap<>();
    public Map<UUID, String> offlineMessages = new HashMap<>();
    public static String prefix;
    public static String healmessage;
    public static String gothealed;
    public static String healedother;
    public static String feedmessage;
    public static String gotfed;
    public static String fedother;
    public static String playeroffline;
    public static String noperms;

    @Override
    public void onEnable() {
        getLogger().info("Oger-Labs Plugin has been enabled");

        instance = this;
        saveDefaultConfig();

        try {
            sql = new SqliteDriver(getDataFolder() + "/smpdatabase.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        this.getCommand("kit").setExecutor(new Kit());
        this.getCommand("fart").setExecutor(new Fart());
        this.getCommand("fortyukon").setExecutor(new FortYukon());
        this.getCommand("hug").setExecutor(new Hug());
        this.getCommand("spawncat").setExecutor(new CustomCat());
        this.getCommand("cheese").setExecutor(new Cheese());
        this.getCommand("spawncrate").setExecutor(new SupplyCrates());
        this.getCommand("starttag").setExecutor(new Tag());
        this.getCommand("jointag").setExecutor(new Tag());
        this.getCommand("endtag").setExecutor(new Tag());
        this.getCommand("startchess").setExecutor(new Chess());
        this.getCommand("endchess").setExecutor(new Chess());
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("feed").setExecutor(new Feed());
        getServer().getPluginManager().registerEvents(new SimpleEventHandler(sql), this);
        getServer().getPluginCommand("msghelp").setExecutor(new MSGHelpCommand(this));
        getServer().getPluginCommand("msghelp").setTabCompleter(new MSGHelpTabCompleter());
        getServer().getPluginCommand("msgreloadconfig").setExecutor(new MSGReloadConfigCommand(this));
        getServer().getPluginCommand("msgreloadconfig").setTabCompleter(new MSGReloadConfigTabCompleter());
        getServer().getPluginCommand("showblacklist").setExecutor(new ShowBlacklistCommand(sql));
        getServer().getPluginCommand("showblacklist").setTabCompleter(new ShowBlacklistTabCompleter());
        getServer().getPluginCommand("addblacklist").setExecutor(new AddBlacklistCommand(this, sql));
        getServer().getPluginCommand("addblacklist").setTabCompleter(new AddBlacklistTabCompleter());
        getServer().getPluginCommand("removeblacklist").setExecutor(new RemoveBlacklistCommand(this, sql));
        getServer().getPluginCommand("removeblacklist").setTabCompleter(new RemoveBlacklistTabCompleter());
        getServer().getPluginCommand("changesound").setExecutor(new ChangeSoundCommand(sql));
        getServer().getPluginCommand("changesound").setTabCompleter(new ChangeSoundTabCompleter());
        getServer().getPluginCommand("changevolume").setExecutor(new ChangeVolumeCommand(sql));
        getServer().getPluginCommand("changevolume").setTabCompleter(new ChangeVolumeTabCompleter());
        getServer().getPluginCommand("playermsg").setExecutor(new PlayerMsgCommand(this, sql));
        getServer().getPluginCommand("playermsg").setTabCompleter(new PlayerMsgTabCompleter(sql));
        getServer().getPluginCommand("acceptsend").setExecutor(new AcceptSendCommand(sql));
        getServer().getPluginCommand("acceptsend").setTabCompleter(new AcceptSendTabCompleter());
        loadConfig();
        loadStrings();

//        Bukkit.getPluginManager().registerEvents((Listener) new HelloWorld(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Oger-Labs Plugin has been disabled");
        try {
            sql.connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public static OgerLabs getInstance() {
        return instance;
    }

    public void loadConfig() {
        FileConfiguration cfg = getConfig();
        cfg.addDefault("Config.Prefix", "&7&l[&e&lOger-Labs&7&l] ");
        cfg.addDefault("Config.HealMessage", "&aYou healed yourself.");
        cfg.addDefault("Config.GotHealed", "&aYou got healed.");
        cfg.addDefault("Config.HealedOther", "&aYou healed this person.");
        cfg.addDefault("Config.FeedMessage", "&aYou fed yourself.");
        cfg.addDefault("Config.GotFed", "&aYou got fed.");
        cfg.addDefault("Config.FedOther", "&aYou fed the appritate of this person.");
        cfg.addDefault("Config.PlayerOffline", "&cThis player is offline.");
        cfg.addDefault("Config.NoPerms", "&cYou don't have permissions to use this command.");
        cfg.options().copyDefaults(true);
        saveConfig();
        reloadConfig();
    }
    public void loadStrings() {
        FileConfiguration cfg = getConfig();
        prefix = cfg.getString("Config.Prefix").replace("&", "§");
        healmessage = cfg.getString("Config.HealMessage").replace("&", "§");
        gothealed = cfg.getString("Config.GotHealed").replace("&", "§");
        healedother = cfg.getString("Config.HealedOther").replace("&", "§");
        feedmessage = cfg.getString("Config.FeedMessage").replace("&", "§");
        gotfed = cfg.getString("Config.GotFed").replace("&", "§");
        fedother = cfg.getString("Config.FedOther").replace("&", "§");
        playeroffline = cfg.getString("Config.PlayerOffline").replace("&", "§");
        noperms = cfg.getString("Config.PlayerOffline").replace("&", "§");
    }
}