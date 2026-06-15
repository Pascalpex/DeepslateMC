package de.pascalpex.deepslatemc.util;

import net.kyori.adventure.text.Component;
import de.pascalpex.deepslatemc.files.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import java.util.Timer;
import java.util.TimerTask;

public class ActionbarUtil {

    private static Component title;
    private static final Timer timer = new Timer();
    private static TimerTask task;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void reloadActionbar() {
        if (task != null) {
            task.cancel();
        }
        if (!Config.getActionbarEnabled()) {
            return;
        }

        task = new TimerTask() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendActionBar(title));
            }
        };

        title = miniMessage.deserialize(Config.getActionbarText());
        timer.scheduleAtFixedRate(task, 500, 1500);
    }
}
