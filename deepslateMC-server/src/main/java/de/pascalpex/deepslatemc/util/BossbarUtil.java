package net.pascalpex.deepslatemc.util;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.pascalpex.deepslatemc.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BossbarUtil {

    private static BossBar bossbar;

    public static void setBossbar(Player player) {
        if (bossbar == null) {
            reloadBossbar();
            return;
        }
        player.hideBossBar(bossbar);
        player.showBossBar(bossbar);
    }

    public static void reloadBossbar() {
        if (bossbar != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.hideBossBar(bossbar);
            }
        }
        if (Config.getBossbarEnabled()) {
            bossbar = BossBar.bossBar(Component.text(Config.getBossbarText().replace("&", "§")).asComponent(), Config.getBossbarProgress(), BossBar.Color.valueOf(Config.getBossbarColor()), BossBar.Overlay.valueOf(Config.getBossbarStyle()));
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.showBossBar(bossbar);
            }
        }
    }

}
