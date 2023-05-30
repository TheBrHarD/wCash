package com.contattos.wcash;

import com.contattos.wcash.api.RawTextAPI;
import com.contattos.wcash.api.Register;
import com.contattos.wcash.database.Connection;
import com.contattos.wcash.database.MethodBlock;
import com.contattos.wcash.database.MethodCash;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new Register();
        Connection.open();
        Connection.createTableCash();
        Connection.createTableBlocos();
        runnable();
    }

    @Override
    public void onDisable() {
        Connection.close();
    }

    private void runnable() {
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage("\n§aVeja os jogadores que se destacam no servidor!");
                    p.sendMessage("§7(Clique na mensagem para acessar o TOP escolhido).\n");
                    RawTextAPI.rawClickText(p,
                            "\n§6│ §7" + MethodCash.getTop().get(0) + " §8▶ " +
                                    MethodCash.getTop().get(1),"§fClique para abrir o TOP." ,
                            "/shopc");
                    RawTextAPI.rawClickText(p,
                            "§7│ §7" + MethodBlock.getTop().get(0) + " §8▶ " +
                                    MethodBlock.getTop().get(1) + " \n ", "§fClique para abrir o TOP.",
                            "/missoes");
                }
            }
        }.runTaskTimerAsynchronously(this, 20 * 60 * 5, 20 * 60 * 5);
    }

    public static Main getPlugin(){
        return getPlugin(Main.class);
    }

}
