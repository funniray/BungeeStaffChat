package com.funniray.bungeestaffchat;

import com.funniray.bungeestaffchat.bungee.BungeeServer;
import com.funniray.bungeestaffchat.spigot.SpigotServer;
import net.md_5.bungee.api.chat.*;

public class Utils {

    private static IServer server;

    public static void setServer(IServer server) {
        Utils.server = server;
    }

    public static IServer getServer() {

        if (server != null)
            return server;

        try {
            //Should create an error
            Class.forName("net.md_5.bungee.api.ProxyServer");
            server = new BungeeServer();
        } catch (ClassNotFoundException e) {
            server = new SpigotServer();
        }

        return server;
    }

    public static void broadcastMessage(IPlayer player, String message) {

        String m = getServer().getMessageFormat();
        m = m.replaceAll("%displayname%", player.getDisplayName()).replaceAll("%message%",message).replaceAll("%server%",player.getServer());

        String h = getServer().getHoverFormat();
        h = h.replaceAll("%displayname%", player.getDisplayName()).replaceAll("%message%",message).replaceAll("%server%",player.getServer());

        BaseComponent component = new TextComponent(TextComponent.fromLegacyText(m.replaceAll("&","§")));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,TextComponent.fromLegacyText(h.replaceAll("&","§"))));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/server "+player.getServer()));

        for (IPlayer p : getServer().getPlayersOnline()) {
            if (!p.hasPermission("bsc.receive"))
                continue;

            p.sendMessage(component);
        }


    }
}
