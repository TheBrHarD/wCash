package com.contattos.wcash.inventories;

import com.contattos.wcash.api.Decimal;
import com.contattos.wcash.api.NewItens;
import com.contattos.wcash.database.MethodCash;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryShop implements Listener {
    public static void open(Player p){

        HashMap<String, Double> precos = new HashMap<String, Double>();

        Inventory inv = Bukkit.createInventory(null, 6*9, "Loja de cash");

        ItemStack info = NewItens.add(Material.GOLD_INGOT, "§eAdquira CASH!",
                new String[]{
                        "",
                        "§fSeu saldo: §6✪" + Decimal.formatSimbolo(MethodCash.get(p.getName())),
                        "",
                        "§7Com o cash você pode adquirir itens",
                        "§7exclusivos, não fique para trás.",
                        "",
                        "§bSaiba mais: §eloja.contattos.com",
                        });

        ItemStack lucky = NewItens.add(Material.SPONGE, "§eLuckyBlock",
                new String[]{
                        "",
                        "§7Vamos testar a sua sorte?",
                        "§7Há vários itens raros!!",
                        "",
                        " §8▶ §fPreço: §6✪10M",
                        "",
                        "§aClique para comprar."});

        ItemStack boss = NewItens.add(Material.MONSTER_EGG, "§2Boss Zombie", 1, 54,
                new String[]{
                        "",
                        "§7Invoque o boss para ganhar",
                        "§7diversas recompensas.",
                        "",
                        "§fVida: §4❤25.000",
                        "",
                        " §8▶ §fPreço: §6✪1M",
                        "",
                        "§aClique para invocar."
        });

        ItemStack top1;
        ItemStack top2;
        ItemStack top3;
        ItemStack top4;
        ItemStack top5;

        if (MethodCash.getTop().size() <= 0 || MethodCash.getTop().get(1) == null){
            top1 = NewItens.add(Material.SKULL_ITEM, "§8#§f1 §7Ninguém",
                    new String[]{"§fQuantia: §6✪0"});
        } else {
            top1 = NewItens.add(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) top1.getItemMeta();
            meta.setOwner(MethodCash.getTop().get(0));
            meta.setDisplayName("§8#§f1 §7" + MethodCash.getTop().get(0));
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§fQuantia: " + MethodCash.getTop().get(1));

            meta.setLore(lore);
            top1.setItemMeta(meta);
        }
        if (MethodCash.getTop().size() <= 2 || MethodCash.getTop().get(3) == null){
            top2 = NewItens.add(Material.SKULL_ITEM, "§8#§f2 §7Ninguém",
                    new String[]{"§fQuantia: §6✪0"});
        } else {
            top2 = NewItens.add(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) top2.getItemMeta();
            meta.setOwner(MethodCash.getTop().get(2));
            meta.setDisplayName("§8#§f2 §7" + MethodCash.getTop().get(2));
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§fQuantia: " + MethodCash.getTop().get(3));

            meta.setLore(lore);
            top2.setItemMeta(meta);
        }
        if (MethodCash.getTop().size() <= 4 || MethodCash.getTop().get(5) == null){
            top3 = NewItens.add(Material.SKULL_ITEM, "§8#§f3 §7Ninguém",
                    new String[]{"§fQuantia: §6✪0"});
        } else {
            top3 = NewItens.add(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) top3.getItemMeta();
            meta.setOwner(MethodCash.getTop().get(4));
            meta.setDisplayName("§8#§f3 §7" + MethodCash.getTop().get(4));
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§fQuantia: " + MethodCash.getTop().get(5));

            meta.setLore(lore);
            top3.setItemMeta(meta);
        }
        if (MethodCash.getTop().size() <= 6 || MethodCash.getTop().get(7) == null){
            top4 = NewItens.add(Material.SKULL_ITEM, "§8#§f4 §7Ninguém",
                    new String[]{"§fQuantia: §6✪0"});
        } else {
            top4 = NewItens.add(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) top4.getItemMeta();
            meta.setOwner(MethodCash.getTop().get(6));
            meta.setDisplayName("§8#§f4 §7" + MethodCash.getTop().get(6));
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§fQuantia: " + MethodCash.getTop().get(7));

            meta.setLore(lore);
            top4.setItemMeta(meta);
        }
        if (MethodCash.getTop().size() <= 8 || MethodCash.getTop().get(9) == null){
            top5 = NewItens.add(Material.SKULL_ITEM, "§8#§f5 §7Ninguém",
                    new String[]{"§fQuantia: §6✪0"});
        } else {

            top5 = NewItens.add(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) top5.getItemMeta();
            meta.setOwner(MethodCash.getTop().get(0));
            meta.setDisplayName("§8#§f5 §7" + MethodCash.getTop().get(8));
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§fQuantia: " + MethodCash.getTop().get(9));

            meta.setLore(lore);
            top5.setItemMeta(meta);
        }

        inv.setItem(4, info);
        inv.setItem(29, lucky);
        inv.setItem(30, boss);
        inv.setItem(11, top1);
        inv.setItem(12, top2);
        inv.setItem(13, top3);
        inv.setItem(14, top4);
        inv.setItem(15, top5);

        p.openInventory(inv);

    }
}
