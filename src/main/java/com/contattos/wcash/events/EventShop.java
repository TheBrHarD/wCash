package com.contattos.wcash.events;

import com.contattos.wcash.api.ActionBar;
import com.contattos.wcash.api.Decimal;
import com.contattos.wcash.api.MsgError;
import com.contattos.wcash.database.MethodCash;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class EventShop implements Listener {

    HashMap<String, ItemInfo> itemInfo = new HashMap<>();

    @EventHandler
    public void onShop(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) {
            return;
        }

        itemInfo.put("§eLuckyBlock", new ItemInfo
                (10000000.0, "cash setar " + p.getName() + " 1000"));
        itemInfo.put("§2Boss Zombie", new ItemInfo
                (1000000.0, "cash add " + p.getName() + " 1000"));

        if (e.getInventory().getTitle().equalsIgnoreCase("Loja de cash")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (e.getCurrentItem().getType() == Material.AIR) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            // feito para uma verificação e sumir erro do console para a api msgError.
            if (e.getCurrentItem().getType() == Material.INK_SACK) return;

            String msgFull = "§cSeu inventário está cheio!";

            ItemInfo item = itemInfo.get(e.getCurrentItem().getItemMeta().getDisplayName());

            if (e.getRawSlot() == e.getSlot() && e.getInventory().getTitle()
                    .equalsIgnoreCase("Loja de cash")) {
                switch (e.getSlot()) {
                    case 29, 30:

                        if (isInventoryFull(p)) {
                            e.setCancelled(true);
                            p.closeInventory();
                            ActionBar.sendAction(p.getPlayer(), msgFull);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                            return;
                        }

                        double valor = item.getPrice();
                        String recompensa = item.getRewardCommand();

                        double cash = MethodCash.get(p.getName());
                        double missingBalance = valor - MethodCash.get(p.getName());

                        if (MethodCash.contains(p.getName()) && cash >= valor) {
                            ActionBar.sendAction(p, "§aVocê comprou §l1x " +
                                    e.getCurrentItem().getItemMeta().getDisplayName() + "§a por §6✪" +
                                    Decimal.formatSimbolo(valor) + " §acom sucesso!");
                            MethodCash.Remove(p.getName(), valor);
                            p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), recompensa);

                        } else {
                            MsgError.showErrorMessage(p.getPlayer(), e.getInventory(), e.getSlot(),
                                    "§cFalta + §6✪" + Decimal.formatSimbolo(missingBalance) + " §ccash");
                            return;
                        }
                        break;
                }
            }
        }
    }

    public boolean isInventoryFull(Player player) {
        Inventory inv = player.getInventory();
        int emptySlots = 0;
        for (ItemStack item : inv.getContents()) {
            if (item == null || item.getType() == Material.AIR) {
                emptySlots++;
            }
        }
        return emptySlots == 0;
    }

    public static class ItemInfo {
        public double price;
        public String rewardCommand;

        public ItemInfo(double price, String rewardCommand) {
            this.price = price;
            this.rewardCommand = rewardCommand;
        }

        public double getPrice() {
            return price;
        }

        public String getRewardCommand() {
            return rewardCommand;
        }

    }
}
