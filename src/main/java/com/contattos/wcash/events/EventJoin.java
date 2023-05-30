package com.contattos.wcash.events;

import com.contattos.wcash.database.MethodCash;
import com.contattos.wcash.database.MethodBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventJoin implements Listener {

    @EventHandler
    void evento(PlayerJoinEvent e ){
        Player p = e.getPlayer();

        if (!MethodCash.contains(p.getName())){
            MethodCash.setPlayer(p.getName());
        }
        if (!MethodBlock.contains(p.getName())){
            MethodBlock.setPlayer(p.getName());
        }
    }
}
