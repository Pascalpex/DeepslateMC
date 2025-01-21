package de.pascalpex.deepslatemc.util;

import de.pascalpex.deepslatemc.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TablistUtil {

    private static boolean initiated = false;
    private static String header = "";
    private static String footer = "";

    public static void setTablist(Player player) {
        if (!initiated) {
            reloadTablist();
        }
        player.setPlayerListHeader(header);
        player.setPlayerListFooter(footer);
    }

    public static void reloadTablist() {
        if(!Config.getTablistEnabled()) {
            header = null;
            footer = null;
            initiated = true;
            for (Player player : Bukkit.getOnlinePlayers()) {
                setTablist(player);
            }
            return;
        }

        header = "";
        footer = "";
        for (String s : Config.getHeader()) {
            if (!header.isEmpty()) {
                header += "\n";
            }
            header += s;
        }
        for (String s : Config.getFooter()) {
            if (!footer.isEmpty()) {
                footer += "\n";
            }
            footer += s;
        }
        header = header.replace("&", "§");
        footer = footer.replace("&", "§");

        initiated = true;

        for (Player player : Bukkit.getOnlinePlayers()) {
            setTablist(player);
        }
    }

}
