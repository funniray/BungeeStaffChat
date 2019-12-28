package com.funniray.bungeestaffchat.bungee;

import com.funniray.bungeestaffchat.BungeeMain;
import com.funniray.bungeestaffchat.IPlayer;
import com.funniray.bungeestaffchat.IServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Collection;


public class BungeeServer implements IServer {

    @Override
    public Collection<IPlayer> getPlayersOnline() {
        ArrayList<IPlayer> players = new ArrayList<>();

        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            players.add(new BungeePlayer(player));
        }

        return players;
    }

    @Override
    public String getMessageFormat() {
        return BungeeMain.instance.getConfig().getString("messageformat");
    }

    @Override
    public String getHoverFormat() {
        return BungeeMain.instance.getConfig().getString("hoverformat");
    }
}
