package com.funniray.bungeestaffchat.spigot;

import com.earth2me.essentials.User;
import com.funniray.bungeestaffchat.SpigotMain;
import net.ess3.api.events.NickChangeEvent;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotMain.instance, ()-> {
            User u = SpigotMain.instance.ess.getUser(e.getPlayer());
            SpigotMain.instance.sendDisplayname(u.getNick(true, false, false), e.getPlayer());
        },80L);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onNickChange(NickChangeEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotMain.instance, ()->{
            User u = SpigotMain.instance.ess.getUser(e.getAffected().getBase());
            SpigotMain.instance.sendDisplayname(u.getNick(true,false,false), e.getAffected().getBase());
        },20L);
    }

    public void onDataRecalculate(UserDataRecalculateEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotMain.instance, ()->{
            User u = SpigotMain.instance.ess.getUser(e.getUser().getUniqueId());
            SpigotMain.instance.sendDisplayname(u.getNick(true,false,false), Bukkit.getPlayer(e.getUser().getUniqueId()));
        },20L);
    }
}
