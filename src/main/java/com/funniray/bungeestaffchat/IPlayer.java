package com.funniray.bungeestaffchat;

import net.md_5.bungee.api.chat.BaseComponent;

import java.util.UUID;

public interface IPlayer {

    String getDisplayName();
    String getServer();
    UUID getUUID();
    boolean hasPermission(String perm);
    void sendMessage(BaseComponent tc);

}
