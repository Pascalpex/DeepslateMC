package de.pascalpex.deepslatemc.util;

import net.kyori.adventure.text.Component;
import de.pascalpex.deepslatemc.files.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActionbarUtil {

    private static Component title;
    private static ScheduledExecutorService executor;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void reloadActionbar() {
        if (executor != null) {
            executor.shutdown();
        }
        if (!Config.getActionbarEnabled()) {
            executor = null;
            return;
        }

        title = miniMessage.deserialize(Config.getActionbarText());
        executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "DeepslateMC-Actionbar");
            t.setDaemon(true);
            return t;
        });
        executor.scheduleAtFixedRate(() -> {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendActionBar(title));
        }, 500, 1500, TimeUnit.MILLISECONDS);
    }
}
