package de.pascalpex.deepslatemc.util; // oder ein anderer passender package-Name

import de.pascalpex.deepslatemc.files.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;

public class MaintenanceUtil implements Listener {

    @EventHandler
    public void onServerPing(ServerListPingEvent event) {
        if (Config.getMaintenanceMode() && Config.useCustomMaintenanceMotd()) {
            List<String> motdLines = Config.getMaintenanceMotd();
            String motdString = String.join("\n", motdLines);

            Component motd;
            if (Config.getMinimessageMotd()) {
                motd = MiniMessage.miniMessage().deserialize(motdString);
            } else {
                motd = LegacyComponentSerializer.legacyAmpersand().deserialize(motdString);
            }

            event.motd(motd);
        }
    }
}
