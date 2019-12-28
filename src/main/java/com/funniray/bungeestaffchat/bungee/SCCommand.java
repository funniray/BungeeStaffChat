package com.funniray.bungeestaffchat.bungee;

import com.funniray.bungeestaffchat.BungeeMain;
import com.funniray.bungeestaffchat.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;

public class SCCommand extends Command implements TabExecutor {

    public SCCommand() {
        super("sc","bsc.command.sc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("You must do this from in game");
            return;
        }

        if (args.length == 0) {
            sender.sendMessage("Toggled staff chat");
            BungeeMain.instance.toggleSC(((ProxiedPlayer) sender).getUniqueId());
            return;
        }

        if (!sender.hasPermission("bsc.command.sc"))
            return;

        Utils.broadcastMessage(new BungeePlayer((ProxiedPlayer) sender), String.join(" ",args));
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        //We don't want a tab completion
        return new ArrayList<>();
    }
}
