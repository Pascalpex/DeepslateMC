package de.pascalpex.deepslatemc.util;

import de.pascalpex.deepslatemc.files.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class TablistUtil {

    private static Component header;
    private static Component footer;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void setTablist(Player player) {
        if (header == null) {
            reloadTablist();
        }
        player.sendPlayerListHeaderAndFooter(header, footer);
    }

    public static void reloadTablist() {
        if(!Config.getTablistEnabled()) {
            header = Component.empty();
            footer = Component.empty();
            Bukkit.getOnlinePlayers().forEach(TablistUtil::setTablist);
            return;
        }

        header = buildComponent(Config.getHeader());
        footer = buildComponent(Config.getFooter());
        Bukkit.getOnlinePlayers().forEach(TablistUtil::setTablist);
    }

    private static Component buildComponent(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return Component.empty();
        }

        List<Component> components = lines.stream()
            .map(miniMessage::deserialize)
            .collect(Collectors.toList());

        return Component.join(JoinConfiguration.newlines(), components);
    }

}
