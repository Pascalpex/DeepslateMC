package de.pascalpex.deepslatemc.listeners;

import de.pascalpex.deepslatemc.files.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.jetbrains.annotations.NotNull;

public class ServerPingListener implements Listener {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @EventHandler
    public void onServerPing(@NotNull ServerListPingEvent event) {
        if (Config.getMaintenanceMode()) {
            String motdString = Config.getMaintenanceMotd();
            event.motd(deserializeMotd(motdString));
            event.setMaxPlayers(0);
        } else {
            String motdString = Config.getNormalMotd();
            event.motd(deserializeMotd(motdString));
        }
    }

    private Component deserializeMotd(String motdString) {
        if (Config.getMinimessageMotd()) {
            return miniMessage.deserialize(motdString);
        } else {
            return LegacyComponentSerializer.legacyAmpersand().deserialize(motdString);
        }
    }
}
