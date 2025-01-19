package net.pascalpex.deepslatemc.util;

import net.kyori.adventure.text.Component;
import net.pascalpex.deepslatemc.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.Timer;
import java.util.TimerTask;

public class ActionbarUtil {

    private static String title;
    private static final Timer timer = new Timer();
    private static TimerTask task;

    public static void sendNow(Player player) {
        player.sendActionBar(Component.text(title));
    }

    public static void reloadActionbar() {
        if (task != null) {
            task.cancel();
        }
        task = new TimerTask() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendActionBar(Component.text(title));
                }
            }
        };
        if (Config.getActionbarEnabled()) {
            title = Config.getActionbarText().replace("&", "§");
            timer.scheduleAtFixedRate(task, 500, 1500);
        }
    }
}
