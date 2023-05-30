package com.contattos.wcash.commands;

import com.contattos.wcash.api.Decimal;
import com.contattos.wcash.database.MethodBlock;
import com.contattos.wcash.inventories.InventoryShop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Block extends MethodBlock implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {


        if (cmd.getName().equalsIgnoreCase("blocos")) {

            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage("§fSaldo: " + prefixb + Decimal.formatSimbolo(get(p.getName())));
                } else {
                    sender.sendMessage("§cApenas jogadores podem executar este comando.");
                }
            }

            if (args.length >= 1) {
                Double quantia;

                if (sender.hasPermission("wcash.admin")) {
                    if (args[0].equalsIgnoreCase("setar")) {
                        if (args.length < 3) {
                            sender.sendMessage("§cUtilize: /blocos setar <nickname> <quantia>.");
                            return true;
                        }
                        if (args.length > 3) {
                            sender.sendMessage("§cUtilize: /blocos setar <nickname> <quantia>.");
                            return true;
                        }

                        try {
                            quantia = Decimal.parseSimbolo(args[2]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cDigite um valor válido.");
                            return true;
                        }

                        String target = String.valueOf(args[1]);

                        if (!contains(target)) {
                            sender.sendMessage("§cEste jogador não existe.");
                            return true;
                        }

                        if (quantia < 0.0) {
                            sender.sendMessage("§cVocê não pode setar um valor menor que 1.");
                            return true;
                        }


                        set(target, quantia);
                        sender.sendMessage("§aVocê setou o blocos do " + target + " §apara " + prefixb +
                                Decimal.formatSimbolo(quantia));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("add")) {
                        if (args.length < 3) {
                            sender.sendMessage("§cUtilize: /blocos add <nickname> <quantia>.");
                            return true;
                        }

                        if (args.length > 3) {
                            sender.sendMessage("§cUtilize: /blocos add <nickname> <quantia>.");
                            return true;
                        }


                        try {
                            quantia = Decimal.parseSimbolo(args[2]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cDigite um valor válido.");
                            return true;
                        }

                        String target = String.valueOf(args[1]);

                        if (!contains(target)) {
                            sender.sendMessage("§cEste jogador não existe.");
                            return true;
                        }

                        if (quantia < 1.0) {
                            sender.sendMessage("§cVocê não pode adicionar um valor menor que 1.");
                            return true;
                        }

                        Add(target, quantia);
                        sender.sendMessage("§aVocê adicionou " + prefixb + Decimal.formatSimbolo(quantia) +
                                " §apara o " + target);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("remover")) {
                        if (args.length < 3) {
                            sender.sendMessage("§cUtilize: /blocos remover <nickname> <quantia>.");
                            return true;
                        }

                        if (args.length > 3) {
                            sender.sendMessage("§cUtilize: /blocos remover <nickname> <quantia>.");
                            return true;
                        }

                        try {
                            quantia = Decimal.parseSimbolo(args[2]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cDigite um valor válido.");
                            return true;
                        }

                        String target = String.valueOf(args[1]);

                        if (!contains(target)) {
                            sender.sendMessage("§cEste jogador não existe.");
                            return true;
                        }

                        if (quantia < 1.0) {
                            sender.sendMessage("§cDigite um valor válido.");
                            return true;
                        }

                        Remove(target, quantia);
                        sender.sendMessage("§aVocê removeu " + prefixb + Decimal.formatSimbolo(quantia) +
                                " §ado " + target);
                        return true;
                    }
                }
                /*if (args[0].equalsIgnoreCase("top")) {
                    List<String> top = getTops();
                    sender.sendMessage("\n §7TOP §8- §6Blocos \n §aOs 10 jogadores mais viciados em minerar \n ");
                    for (String mensagens : top) {
                        sender.sendMessage("" + mensagens);
                    }
                    return true;
                }*/
                if (args[0].equalsIgnoreCase("top")){
                    Player p = (Player) sender;
                    Bukkit.dispatchCommand(p.getPlayer(), "missoes");
                    return true;
                }
                if (args[0].equalsIgnoreCase("?")) {
                    if (sender.hasPermission("wcash.admin")) {
                        sender.sendMessage("\n§7Comandos §8- §7■ \n");
                        sender.sendMessage("§7- §a/blocos setar");
                        sender.sendMessage("§7- §a/blocos add");
                        sender.sendMessage("§7- §a/blocos remover");
                        sender.sendMessage("§7- §a/blocos top");
                        sender.sendMessage("");
                    } else {
                        sender.sendMessage("\n§7Comandos §8- §7■ \n");
                        sender.sendMessage("§7- §a/blocos top \n");
                        sender.sendMessage("");
                    }
                    return true;
                }
                String user = String.valueOf(args[0]);
                if (!contains(user)) {
                    sender.sendMessage("§cEste usuário não existe.");
                    return true;
                }
                sender.sendMessage("§fSaldo de " + user + ": " + prefixb + Decimal.formatSimbolo(get(user)));
            }
        }
        return false;
    }
}
