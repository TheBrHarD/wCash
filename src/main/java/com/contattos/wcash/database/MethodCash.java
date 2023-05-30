package com.contattos.wcash.database;

import com.contattos.wcash.api.Decimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MethodCash extends Connection {

    public static String prefixc = "§6✪";
    static PreparedStatement stm = null;
    public static boolean contains(String p){

        try {
            stm = con.prepareStatement("SELECT * FROM `cash` WHERE `player` = ?");
            stm.setString(1, p);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void setPlayer(String p){
        try {
            stm = con.prepareStatement("INSERT INTO `cash`(`player`, `quantia`) VALUES (?, ?)");
            stm.setString(1, p);
            stm.setDouble(2, 0);
            stm.executeUpdate();
            sc.sendMessage(prefix + " " + p + " §acriado com sucesso");
        } catch (SQLException e) {
            sc.sendMessage(prefix + " §cNão foi possível inserir o usuário " + p);
        }
    }

    public static void set(String p, Double quantia){
        if (contains(p)){
            try {
                stm = con.prepareStatement("UPDATE `cash` SET `quantia` = ? WHERE `player` = ?");
                stm.setDouble(1, quantia);
                stm.setString(2, p);
                stm.executeUpdate();
            } catch (SQLException e) {
                sc.sendMessage(prefix + " §cNão foi possível setar o cash do usuário.");
            }
        } else {
            sc.sendMessage(prefix + " §cJogador não cadastrado");
        }
    }

    public static Double get(String p){
        if (contains(p)){
            try {
                stm = con.prepareStatement("SELECT * FROM `cash` WHERE `player` = ?");
                stm.setString(1, p);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getDouble("quantia");
                }
                return 0.0;
            } catch (SQLException e) {
                return 0.0;
            }
        } else {
            setPlayer(p);
            return 0.0;
        }
    }

    public static void Add(String p, Double quantia){
        if (contains(p)){
            set(p, get(p) + quantia);
        } else {
            setPlayer(p);
        }
    }

    public static void Remove(String p, Double quantia){
        if (contains(p)){
            set(p, get(p) - quantia);
        } else {
            setPlayer(p);
        }
    }

    /*public static void delete(String p){
        if (contains(p)){
            try {
                stm = con.prepareStatement("DELETE FROM `cash` WHERE `Player` = ?");
                stm.setString(1, p.toLowerCase());
                stm.executeUpdate();
            } catch (SQLException e) {
                sc.sendMessage(prefix + " §cNão foi possível remover o " + p + " do banco de dados");
            }
        }
    } */

    /*public static List<String> getTops(){

        List<String> tops = new ArrayList<String>();

        try {
            stm = con.prepareStatement("SELECT * FROM `cash` ORDER BY `quantia` DESC LIMIT 10");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                    tops.add("§8➟§7 " + rs.getString("player") + " §8▶ " + prefixc +
                            decimal.formatSimbolo(rs.getDouble("quantia")));
            }
        } catch (SQLException e) {
            sc.sendMessage(prefix + " §cNão foi possível gerar a lista de top cash.");
        }
        return tops;
    }*/

    public static List<String> getTop(){

        List<String> tops = new ArrayList<String>();

        try {
            stm = con.prepareStatement("SELECT * FROM `cash` ORDER BY `quantia` DESC LIMIT 5");
            ResultSet rs = stm.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                tops.add(rs.getString("player"));
                tops.add(prefixc + Decimal.formatSimbolo(rs.getDouble("quantia")));
            }
        } catch (SQLException e) {
            sc.sendMessage(prefix + " §cNão foi possível gerar a lista de top cash.");
        }
        return tops;
    }

    public static Double getCheque(){

        try {
            stm = con.prepareStatement("SELECT SUM(`quantia`) AS quantia\n" +
                    "FROM (\n" +
                    "  SELECT `quantia`\n" +
                    "  FROM cash\n" +
                    "  ORDER BY `quantia` DESC\n" +
                    "  LIMIT 5\n" +
                    ") AS subquery;");
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            } else {
                return 0.0;
            }

        } catch (SQLException e) {
            return 0.0;
        }
    }

}
