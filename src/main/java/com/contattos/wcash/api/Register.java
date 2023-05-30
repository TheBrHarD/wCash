package com.contattos.wcash.api;

import com.contattos.wcash.commands.Block;
import com.contattos.wcash.commands.Cash;
import com.contattos.wcash.commands.Cheque;
import com.contattos.wcash.events.EventJoin;
import com.contattos.wcash.Main;
import com.contattos.wcash.commands.Shop;
import com.contattos.wcash.events.EventCheque;
import com.contattos.wcash.events.EventShop;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Register {

    public Register(){
        Comandos("cash", new Cash());
        Comandos("shopc", new Shop());
        Comandos("cheque", new Cheque());
        Comandos("blocos", new Block());
        Eventos(new EventJoin());
        Eventos(new EventShop());
        Eventos(new EventCheque());
    }

    void Comandos(String comando, CommandExecutor classe){
        Main pl = Main.getPlugin();
        pl.getCommand(comando).setExecutor(classe);
    }

    void Eventos(Listener classe){
        Main pl = Main.getPlugin();
        Bukkit.getPluginManager().registerEvents(classe, pl);
    }
}
