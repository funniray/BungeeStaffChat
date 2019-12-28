package com.funniray.bungeestaffchat;

import com.earth2me.essentials.Essentials;
import com.funniray.bungeestaffchat.spigot.SpigotServer;
import com.funniray.bungeestaffchat.spigot.PlayerListener;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotMain extends JavaPlugin {

    public static SpigotMain instance;
    public Essentials ess;
    private LuckPerms lpapi;
    private Chat chat;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        reloadConfig();
        instance = this;
        Utils.setServer(new SpigotServer());
        getServer().getMessenger().registerOutgoingPluginChannel(this, "bsd:displayname");

        this.ess = (Essentials) getServer().getPluginManager().getPlugin("Essentials");

        PlayerListener playerListener = new PlayerListener();
        this.getServer().getPluginManager().registerEvents(playerListener, this);

        this.lpapi = LuckPermsProvider.get();
        this.lpapi.getEventBus().subscribe(UserDataRecalculateEvent.class, playerListener::onDataRecalculate);

        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        this.chat = rsp.getProvider();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isBungeeConnected() {
        return getServer().spigot().getConfig().getConfigurationSection("settings").getBoolean("settings.bungeecord");
    }

    public void sendDisplayname(String displayName, Player p) {
        if (p == null)
            return;

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(p.getUniqueId().toString());
        //out.writeUTF(this.chat.getPlayerPrefix(p)+displayName+this.chat.getPlayerSuffix(p));
        out.writeUTF(displayName);
        p.sendPluginMessage(this, "bsd:displayname",out.toByteArray());
    }
}
