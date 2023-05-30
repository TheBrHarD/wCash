package com.contattos.wcash.events;

import com.contattos.wcash.api.Decimal;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.contattos.wcash.database.MethodCash.*;

public class EventCheque implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();

            if (!p.isSneaking()) {
                if (p.getItemInHand().getType() == Material.EMERALD && e.getAction() == Action.RIGHT_CLICK_AIR ||
                        e.getAction() == Action.RIGHT_CLICK_BLOCK &&
                                e.getItem() != null && e.getItem().getType() != null) {
                    if (p.getInventory().getItemInHand().getItemMeta().getDisplayName() == null) return;
                    if (p.getInventory().getItemInHand().getItemMeta().getDisplayName()
                            .equalsIgnoreCase("§6Cheque de Cash")) {

                        if (!contains(p.getName())) {
                            setPlayer(p.getName());
                        }

                        ItemStack item = p.getInventory().getItemInHand();
                        if (item != null && item.getType() == Material.EMERALD) {
                            ItemMeta meta = item.getItemMeta();
                            if (meta != null) {
                                List<String> lore = meta.getLore();
                                if (lore != null) {
                                    for (String line : lore) {
                                        if (line.startsWith(" §8▶ §fCash: §6✪")) {
                                            String valueString = line.substring(line.indexOf("✪") + 1)
                                                    .replace("✪", "");
                                            p.sendMessage("§aVocê ativou §f'1x' §acheque de §6✪" + valueString);
                                            Add(p.getName(), Decimal.parseSimbolo(valueString));
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        int quant = p.getInventory().getItemInHand().getAmount();

                        if (quant == 1) {
                            p.setItemInHand(new ItemStack(Material.AIR));
                        } else {
                            p.getInventory().getItemInHand().setAmount(quant - 1);
                        }
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);

                    }
                }
            }

        if (!p.isSneaking()) {
            return;
        }
        if (p.getItemInHand().getType() == Material.EMERALD && e.getAction() == Action.RIGHT_CLICK_AIR ||
                e.getAction() == Action.RIGHT_CLICK_BLOCK && p.isSneaking() &&
                        e.getItem() != null && e.getItem().getType() != null) {
            if (p.getInventory().getItemInHand().getItemMeta().getDisplayName() == null) return;
            if (p.getInventory().getItemInHand().getItemMeta().getDisplayName()
                    .equalsIgnoreCase("§6Cheque de Cash")) {

                if (!contains(p.getName())) {
                    setPlayer(p.getName());
                }

                int quant = p.getInventory().getItemInHand().getAmount();

                ItemStack item = p.getInventory().getItemInHand();
                if (item != null && item.getType() == Material.EMERALD) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        List<String> lore = meta.getLore();
                        if (lore != null) {
                            for (String line : lore) {
                                if (line.startsWith(" §8▶ §fCash: §6✪")) {
                                    String valueString = line.substring(line.indexOf("✪") + 1)
                                            .replace("✪", "");
                                    p.sendMessage("§aVocê ativou §f'" + quant + "x' §acheque de §6✪" + valueString);
                                    Add(p.getName(), Decimal.parseSimbolo(valueString) * quant);
                                    break;
                                }
                            }
                        }
                    }
                }

                p.setItemInHand(new ItemStack(Material.AIR));

                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);

            }
            return;
        }
    }
}
