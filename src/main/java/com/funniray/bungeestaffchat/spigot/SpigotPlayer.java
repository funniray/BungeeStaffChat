package com.funniray.bungeestaffchat.spigot;

import com.funniray.bungeestaffchat.IPlayer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotPlayer implements IPlayer {

    Player player;

    public SpigotPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public String getServer() {
        return player.getWorld().getName();
    }

    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public boolean hasPermission(String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public void sendMessage(BaseComponent tc) {
        player.sendMessage(tc);
    }
}
