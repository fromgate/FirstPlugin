package me.fromgate.firstplugin;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.TextFormat;

public class FirstListener implements Listener {

    @EventHandler (ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerJoin (PlayerJoinEvent event){
        event.getPlayer().sendMessage(TextFormat.colorize("&3[NEWS] &b"+FirstPlugin.getPlugin().helloMessage));
    }
}