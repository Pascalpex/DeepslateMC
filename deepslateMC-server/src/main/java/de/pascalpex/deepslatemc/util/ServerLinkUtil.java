package de.pascalpex.deepslatemc.util;

import de.pascalpex.deepslatemc.files.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.logging.log4j.LogManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ServerLinkUtil {

    private static Map<Component, URI> links = null;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void loadLinks() {
        if (links == null) {
            links = new HashMap<>();
        } else {
            links.clear();
        }
        Config.getServerLinks().forEach((key, value) -> {
            if(key.isBlank() || value.isBlank()) {
                return;
            }
            try {
                links.put(miniMessage.deserialize(key), new URI(value));
            } catch (URISyntaxException e) {
                LogManager.getLogger(ServerLinkUtil.class.getSimpleName()).warn("Invalid URL in DeepslateMC server links: {}", value);
            }
        });
    }

    public static Map<Component, URI> getLinks() {
        if (links == null) {
            loadLinks();
        }
        return links;
    }
}
