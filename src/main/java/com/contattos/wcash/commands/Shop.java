package com.contattos.wcash.commands;

import com.contattos.wcash.inventories.InventoryShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lb, final String[] args) {

        if (cmd.getName().equalsIgnoreCase("shopc")) {

            if (!(sender instanceof Player)) {
                return true;
            }
            if (args.length == 0) {
                Player p = (Player) sender;
                InventoryShop.open(p);
                return true;
            }

        }
        return false;
    }
}
