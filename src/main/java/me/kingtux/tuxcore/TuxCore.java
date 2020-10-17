package me.kingtux.tuxcore;

import dev.nitrocommand.bukkit.BukkitCommandCore;
import dev.tuxjsql.core.TuxJSQLBuilder;
import me.kingtux.lava.PropertiesUtils;
import me.kingtux.tuxcore.commands.VerifyCommand;
import me.kingtux.tuxcore.discord.DiscordBot;
import me.kingtux.tuxcore.listeners.ChatListener;
import me.kingtux.tuxorm.TOConnection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public final class TuxCore extends JavaPlugin {
    private TOConnection commonConnection;
    private BukkitCommandCore commandCore;
    private DiscordBot discordBot;
    private VerifyManager verifyManager;

    private TCUserManager userManager;

    @Override
    public void onEnable() {
        loadListeners();
        saveDefaultConfig();
        loadConnection();
        userManager = new TCUserManager(this);
        discordBot = new DiscordBot(this);
        verifyManager = new VerifyManager(this);
        commandCore = new BukkitCommandCore(this);
        commandCore.registerCommand(new VerifyCommand(this));
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    private void loadConnection() {
        saveResource("db.properties", false);
        try {
            Properties properties = PropertiesUtils.loadPropertiesFromFile(new File(getDataFolder(), "db.properties"));
            commonConnection = new TOConnection(TuxJSQLBuilder.create(properties));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        if (commonConnection != null) commonConnection.close();
        if (discordBot != null) discordBot.close();
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    public TOConnection getCommonConnection() {
        return commonConnection;
    }

    public BukkitCommandCore getCommandCore() {
        return commandCore;
    }

    public DiscordBot getDiscordBot() {
        return discordBot;
    }

    public VerifyManager getVerifyManager() {
        return verifyManager;
    }
}
