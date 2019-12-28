package com.funniray.bungeestaffchat.spigot;

import com.funniray.bungeestaffchat.IPlayer;
import com.funniray.bungeestaffchat.IServer;
import com.funniray.bungeestaffchat.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class SpigotServer implements IServer {
    @Override
    public Collection<IPlayer> getPlayersOnline() {
        ArrayList<IPlayer> players = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            players.add(new SpigotPlayer(player));
        }

        return players;
    }

    @Override
    public String getMessageFormat() {
        return SpigotMain.instance.getConfig().getString("messageformat");
    }

    @Override
    public String getHoverFormat() {
        return SpigotMain.instance.getConfig().getString("hoverformat");
    }
}
