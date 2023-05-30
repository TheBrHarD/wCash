package com.contattos.wcash.api;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class RawTextAPI {

    public static void rawShowText(Player player, String mensagem, String textojson){

        TextComponent nome = new TextComponent(mensagem);
        nome.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(textojson).create()));

        player.getPlayer().spigot().sendMessage(nome);

    }

    public static void rawClickText(Player player, String mensagem, String textojson, String comando){

        TextComponent nome = new TextComponent(mensagem);
        nome.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(textojson).create()));

        nome.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, comando));

        player.getPlayer().spigot().sendMessage(nome);

    }

    public static void rawUrlText(Player player, String nomeTexto, String mensagem2, String url){

        TextComponent nome = new TextComponent(nomeTexto);
        nome.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(mensagem2).create()));

        nome.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));

        player.getPlayer().spigot().sendMessage(nome);

    }

}
