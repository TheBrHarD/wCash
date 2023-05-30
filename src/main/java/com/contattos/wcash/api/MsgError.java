package com.contattos.wcash.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MsgError {
    public static void showErrorMessage(Player player, Inventory inventory, int slot, String message) {
        ItemStack item = inventory.getItem(slot);

        ItemStack semCoins = new ItemStack(Material.INK_SACK, 1, (short) 8);
        ItemMeta metaSemCash = semCoins.getItemMeta();
        metaSemCash.setDisplayName(message);
        semCoins.setItemMeta(metaSemCash);
        inventory.setItem(slot, semCoins);
        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("wEmprestimo"), () -> {
            inventory.setItem(slot, item);
        }, 40L); // 20 ticks = 1 segundo
    }
}
