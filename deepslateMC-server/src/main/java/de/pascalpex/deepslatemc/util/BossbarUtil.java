package de.pascalpex.deepslatemc.util;

import net.kyori.adventure.bossbar.BossBar;
import de.pascalpex.deepslatemc.files.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BossbarUtil {

    private static BossBar bossbar;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void setBossbar(Player player) {
        if (bossbar == null) {
            reloadBossbar();
            return;
        }
        player.showBossBar(bossbar);
    }

    public static void reloadBossbar() {
        if (bossbar != null) {
            Bukkit.getOnlinePlayers().forEach(player -> player.hideBossBar(bossbar));
        }
        if (Config.getBossbarEnabled()) {
            bossbar = BossBar.bossBar(
                miniMessage.deserialize(Config.getBossbarText()),
                Config.getBossbarProgress(),
                BossBar.Color.valueOf(Config.getBossbarColor().toUpperCase()),
                BossBar.Overlay.valueOf(Config.getBossbarStyle().toUpperCase()));

            Bukkit.getOnlinePlayers().forEach(player -> player.showBossBar(bossbar));
        }
    }

}
