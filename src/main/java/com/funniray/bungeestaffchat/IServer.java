package com.funniray.bungeestaffchat;

import java.util.Collection;

public interface IServer {

    Collection<IPlayer> getPlayersOnline();
    String getMessageFormat();
    String getHoverFormat();
}
