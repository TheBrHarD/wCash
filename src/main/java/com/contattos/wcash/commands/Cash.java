package com.contattos.wcash.commands;

import com.contattos.wcash.api.Decimal;
import com.contattos.wcash.database.MethodCash;
import com.contattos.wcash.inventories.InventoryShop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cash extends MethodCash implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {


        if (cmd.getName().equalsIgnoreCase("cash")) {

            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage("§fSaldo: " + prefixc + Decimal.formatSimbolo(get(p.getName())));
                } else {
                    sender.sendMessage("§cApenas jogadores podem executar este comando.");
                }
            }

            if (args.length >= 1) {
                Double quantia;

                if (sender.hasPermission("wcash.admin")) {
                    if (args[0].equalsIgnoreCase("setar")) {
                        if (args.length < 3) {
                            sender.sendMessage("§cUtilize: /cash setar <nickname> <quantia>.");
                            return true;
                        }
                        if (args.length > 3) {
                            sender.sendMessage("§cUtilize: /cash setar <nickname> <quantia>.");
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
                        sender.sendMessage("§aVocê setou o cash do " + target + " §apara " + prefixc +
                                Decimal.formatSimbolo(quantia));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("add")) {
                        if (args.length < 3) {
                            sender.sendMessage("§cUtilize: /cash add <nickname> <quantia>.");
                            return true;
                        }

                        if (args.length > 3) {
                            sender.sendMessage("§cUtilize: /cash add <nickname> <quantia>.");
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
                        sender.sendMessage("§aVocê adicionou " + prefixc + Decimal.formatSimbolo(quantia) +
                                " §apara o " + target);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("remover")) {
                        if (args.length < 3) {
                            sender.sendMessage("§cUtilize: /cash remover <nickname> <quantia>.");
                            return true;
                        }

                        if (args.length > 3) {
                            sender.sendMessage("§cUtilize: /cash remover <nickname> <quantia>.");
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
                        sender.sendMessage("§aVocê removeu " + prefixc + Decimal.formatSimbolo(quantia) +
                                " §ado " + target);
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("pagar")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        if (args.length < 3) {
                            sender.sendMessage("§cUtilize: /cash pagar <nickname> <quantia>.");
                            return true;
                        }

                        if (args.length > 3) {
                            sender.sendMessage("§cUtilize: /cash pagar <nickname> <quantia>.");
                            return true;
                        }

                        String target = String.valueOf(args[1]);

                        if (!contains(target)) {
                            p.sendMessage("§cEste jogador não existe.");
                            return true;
                        }

                        if (target.equals(p.getName())) {
                            p.sendMessage("§cSério que você quer enviar para você mesmo?");
                            return true;
                        }

                        try {
                            quantia = Decimal.parseSimbolo(args[2]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cDigite um valor válido.");
                            return true;
                        }
                        double total = get(p.getName()) - quantia;
                        if (total < 0.0) {
                            p.sendMessage("§cVocê não tem cash suficiente.");
                            return true;
                        }
                        if (quantia < 1.0) {
                            p.sendMessage("§cVocê não tem cash suficiente.");
                            return true;
                        }
                        Add(target, quantia);
                        Remove(p.getName(), quantia);

                        p.sendMessage("§aVocê enviou " + prefixc + Decimal.formatSimbolo(quantia) +
                                " §apara o " + target);
                        Player recebidor = Bukkit.getPlayerExact(target);
                        if (recebidor != null && recebidor.isOnline()) {
                            recebidor.sendMessage("§aVocê recebeu " + prefixc + Decimal.formatSimbolo(quantia) +
                                    " §ade " + target);
                        }
                    } else {
                        sender.sendMessage("§cEste comando só pode ser executado por jogadores.");
                    }
                    return true;
                }
                /*if (args[0].equalsIgnoreCase("top")) {
                    List<String> top = getTops();
                    sender.sendMessage("\n §7TOP §8- §6Cash \n §aOs 10 jogadores mais ricos do servidor \n ");
                    for (String mensagens : top) {
                        sender.sendMessage("" + mensagens);
                    }
                    return true;
                }*/
                if (args[0].equalsIgnoreCase("top")){
                    Player p = (Player) sender;
                    InventoryShop.open(p);
                    return true;
                }
                if (args[0].equalsIgnoreCase("?")) {
                    if (sender.hasPermission("wcash.admin")) {
                        sender.sendMessage("\n§7Comandos §8- §6✪ \n");
                        sender.sendMessage("§7- §a/cash setar");
                        sender.sendMessage("§7- §a/cash add");
                        sender.sendMessage("§7- §a/cash remover");
                        sender.sendMessage("§7- §a/cash pagar");
                        sender.sendMessage("§7- §a/cash top ");
                        sender.sendMessage("");
                    } else {
                        sender.sendMessage("\n§7Comandos §8- §6✪ \n");
                        sender.sendMessage("§7- §a/cash top \n");
                        sender.sendMessage("§7- §a/cash pagar <nickname> <quantia>.");
                        sender.sendMessage("");
                    }
                    return true;
                }
                String user = String.valueOf(args[0]);
                if (!contains(user)) {
                    sender.sendMessage("§cEste usuário não existe.");
                    return true;
                }
                sender.sendMessage("§fSaldo de " + user + ": " + prefixc + Decimal.formatSimbolo(get(user)));
            }
        }
        return false;
    }
}
