package com.contattos.wcash.commands;

import com.contattos.wcash.api.Decimal;
import com.contattos.wcash.api.NewItens;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.contattos.wcash.database.MethodCash.contains;
import static com.contattos.wcash.database.MethodCash.getCheque;

public class Cheque implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        if (sender.hasPermission("wcash.admin")) {
        if (cmd.getName().equalsIgnoreCase("cheque")) {
            if (args.length == 0){
                sender.sendMessage("§cUtilize: /cheque cash <nickname> <quantia>.");
                return true;
            }
                if (args[0].equalsIgnoreCase("cash")) {
                    if (args.length < 3) {
                        sender.sendMessage("§cUtilize: /cheque cash <nickname> <quantia>.");
                        return true;
                    }

                    Double quantia;

                    try {
                        quantia = Decimal.parseSimbolo(args[2]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("§cDigite um valor válido.");
                        return true;
                    }

                    if (quantia <= 0.0){
                        sender.sendMessage("§cVocê não pode criar um cheque com valor menor que 1.");
                        return true;
                    }

                    String target = String.valueOf(args[1]);

                    if (!contains(target)) {
                        sender.sendMessage("§cEste jogador não existe.");
                        return true;
                    }

                    Player recebidor = Bukkit.getPlayerExact(target);
                    if (recebidor == null) {
                        sender.sendMessage("§cEste usuário está offline.");
                        return true;
                    }

                    ItemStack cheque = NewItens.add(Material.EMERALD, "§6Cheque de Cash",
                            new String[]{
                                    "",
                                    "§7Ganhe o cash armazenado",
                                    "§7neste item.",
                                    "",
                                    " §8▶ §fCash: §6✪" + Decimal.formatSimbolo(quantia),
                                    "",
                                    "§aClique para extrair."});
                    recebidor.getInventory().addItem(cheque);
                    recebidor.sendMessage("§aVocê ganhou um cheque de cash no valor de §6✪" +
                            Decimal.formatSimbolo(quantia));
                }
                if (args[0].equalsIgnoreCase("economia")){

                    if (args.length < 2) {
                        return true;
                    }

                    String target = String.valueOf(args[1]);

                    Player recebidor = Bukkit.getPlayerExact(target);
                    if (recebidor == null) {
                        sender.sendMessage("§cEste usuário está offline.");
                        return true;
                    }

                    ItemStack chequeEcon = NewItens.add(Material.EMERALD, "§6Cheque de Cash",
                            new String[]{
                                    "",
                                    "§7Ganhe o cash armazenado",
                                    "§7neste item.",
                                    "",
                                    " §8▶ §fCash: §6✪" + Decimal.formatSimbolo(getCheque() * 0.0001),
                                    "",
                                    "§aClique para extrair."});
                    recebidor.getInventory().addItem(chequeEcon);
                    recebidor.sendMessage("§aVocê ganhou um cheque de cash no valor de §6✪" +
                            Decimal.formatSimbolo(getCheque() * 0.0001));
                }
            }
        } else {
            sender.sendMessage("Unknown command. Type '/help' for help.");
        }
        return false;
    }
}
