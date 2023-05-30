package com.contattos.wcash.database;

import com.contattos.wcash.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.contattos.wcash.database.MethodCash.prefix;

public class Connection {
    public static String prefix = "§7[wEconomia]";

    protected static java.sql.Connection con = null;

    public static ConsoleCommandSender sc = Bukkit.getConsoleSender();

    static PreparedStatement stm = null;

    public static void open(){
        String url = "jdbc:mysql://localhost:3306/contattos";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            sc.sendMessage(prefix + " §aConexão com o MySQL foi aberta!");
        } catch (SQLException e) {
            sc.sendMessage(prefix + " §cConexão com o MySQL não foi aberta!");
            Main.getPlugin().getPluginLoader().disablePlugin(Main.getPlugin());
        }
    }

    public static void close(){
        if (con != null){
            try {
                con.close();
                sc.sendMessage(prefix + " §aConexão fechada com sucesso!");
            } catch (SQLException e) {
                sc.sendMessage(prefix + " §cConexão não foi possível fechar a conexão!");
            }
        }
    }

    public static void createTableCash(){
        if (con != null){
            try {
                stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `cash` (`id` INT NOT NULL AUTO_INCREMENT,`player` VARCHAR(24) NULL,`quantia` DOUBLE NULL,PRIMARY KEY (`id`));");
                stm.executeUpdate();
                sc.sendMessage(prefix + " §aTabela de Cash carregada com sucesso!");
            } catch (SQLException e) {
                sc.sendMessage(prefix + " §cNão foi possível carregar a tabela de cash!");
            }

        }
    }
    public static void createTableBlocos() {
        if (con != null) {
            try {
                stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `blocos` (`id` INT NOT NULL AUTO_INCREMENT,`player` VARCHAR(24) NULL,`quantia` DOUBLE NULL,PRIMARY KEY (`id`));");
                stm.executeUpdate();
                sc.sendMessage(prefix + " §aTabela de Blocos carregada com sucesso!");
            } catch (SQLException e) {
                sc.sendMessage(prefix + " §cNão foi possível carregar a tabela de blocos!");
            }

        }
    }
}
