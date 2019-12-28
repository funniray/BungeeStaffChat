package com.funniray.bungeestaffchat;

import com.funniray.bungeestaffchat.bungee.BungeeServer;
import com.funniray.bungeestaffchat.bungee.PlayerEventListener;
import com.funniray.bungeestaffchat.bungee.SCCommand;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;

public final class BungeeMain extends Plugin {

    public static BungeeMain instance;
    public Configuration config;
    public HashMap<UUID,String> displayNames;
    private HashMap<UUID,Boolean> toggled = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        Utils.setServer(new BungeeServer());
        getProxy().registerChannel("bsd:displayname");
        getProxy().getPluginManager().registerListener(this, new PlayerEventListener());
        getProxy().getPluginManager().registerCommand(this, new SCCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Configuration getConfig() {
        try {
            if (this.config != null)
                return config;

            File dataFolder = BungeeMain.instance.getDataFolder();

            if (!dataFolder.exists())
                dataFolder.mkdir();

            File file = new File(dataFolder, "config.yml");

            if (!file.exists()) {
                try {
                    InputStream in = getResourceAsStream("config.yml");
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config;
    }

    public boolean isToggled(UUID uuid) {
        if (this.toggled.get(uuid) == null) {
            return false;
        }
        return this.toggled.get(uuid);
    }

    public void toggleSC(UUID uuid) {
        this.toggled.put(uuid,!this.isToggled(uuid));
    }
}
