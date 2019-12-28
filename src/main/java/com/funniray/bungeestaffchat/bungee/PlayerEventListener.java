package com.funniray.bungeestaffchat.bungee;

import com.funniray.bungeestaffchat.BungeeMain;
import com.funniray.bungeestaffchat.Utils;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e) {
        if (!e.getTag().equalsIgnoreCase("bsd:displayname"))

            return;

        ByteArrayDataInput in = ByteStreams.newDataInput(e.getData());
        ProxiedPlayer p = ProxyServer.getInstance().getPlayer(UUID.fromString(in.readUTF()));
        p.setDisplayName(in.readUTF());
    }

    @EventHandler
    public void onChat(ChatEvent e) {
        if (e.getMessage().startsWith("/"))
            return;
        if (BungeeMain.instance.isToggled(((ProxiedPlayer) e.getSender()).getUniqueId())) {
            e.setCancelled(true);
            Utils.broadcastMessage(new BungeePlayer((ProxiedPlayer) e.getSender()), e.getMessage());
        }
    }
}
