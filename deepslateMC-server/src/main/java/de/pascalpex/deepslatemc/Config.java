package net.pascalpex.deepslatemc;

import net.kyori.adventure.bossbar.BossBar;
import net.pascalpex.deepslatemc.util.BossbarUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static File configFile = new File("deepslate", "deepslate.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static void load() {
        try {
            configFile.getParentFile().mkdirs();
            if (!configFile.exists()) {
                configFile.createNewFile();
                List<String> header = new ArrayList<>();
                header.add("This is the main configuration file of DeepslateMC");
                header.add("You can find an explanation of all options here: https://github.com/Pascalpex/DeepslateMC/wiki");
                header.add("You can get support on our Discord server: https://discord.gg/BGrhNnVczp");
                config.options().setHeader(header);
                save();
            }
            config.load(configFile);
            if(!config.contains("discordLink")) {
                config.set("discordLink", "https://discord.gg/BGrhNnVczp");
            }
            if(!config.contains("maintenance")) {
                config.set("maintenance", false);
            }
            if(!config.contains("opCommandActive")) {
                config.set("opCommandActive", true);
            }
            if(!config.contains("spawnOnJoin")) {
                config.set("spawnOnJoin", false);
            }
            if(!config.contains("preventKnowledgebookClick")) {
                config.set("preventKnowledgebookClick", false);
            }
            if(!config.contains("autoSmelt")) {
                config.set("autoSmelt" + ".price", 30);
                List<String> worldsList = new ArrayList<>();
                worldsList.add("FantasyWorld");
                config.set("autoSmelt" + ".enabledWorlds", worldsList);
            }
            if (!config.contains("messages")) {
                config.set("messages" + ".movedWrongly", true);
                config.set("messages" + ".movedQuickly", true);
            }
            if (!config.contains("f3name")) {
                config.set("f3name", "&eDeepslateMC");
            }
            if (!config.contains("tablist")) {
                config.set("tablist" + ".enabled", false);
                List<String> headerList = new ArrayList<>();
                List<String> footerList = new ArrayList<>();
                headerList.add("&4This is the");
                headerList.add("&4default header");
                footerList.add("&6This is the");
                footerList.add("&6default footer");
                config.set("tablist" + ".header", headerList);
                config.set("tablist" + ".footer", footerList);
            }
            if(!config.contains("playerlistHover")) {
                config.set("playerlistHover" + ".enabled", false);
                List<String> lines = new ArrayList<>();
                lines.add("&6This is the");
                lines.add("&6default text");
                config.set("playerlistHover" + ".lines", lines);
            }
            if (!config.contains("bossbar")) {
                config.set("bossbar" + ".enabled", false);
                config.set("bossbar" + ".color", BossBar.Color.WHITE.name());
                config.set("bossbar" + ".progress", 1.0f);
                config.set("bossbar" + ".style", BossBar.Overlay.PROGRESS.name());
                config.set("bossbar" + ".text", "&6Custom Bossbar");
            }
            if (!config.contains("actionbar")) {
                config.set("actionbar" + ".enabled", false);
                config.set("actionbar" + ".text", "&6Custom Actionbar");
            }
            save();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean getOpActive() {
        return config.getBoolean("opCommandActive");
    }
    public static String getDiscordLink() {
        return config.getString("discordLink");
    }
    public static String getBuildworld() {
        return config.getString("buildworld");
    }
    public static void setBuildworld(String worldName) {
        config.set("buildworld", worldName);
        save();
    }
    public static boolean getMaintenanceMode() {
        return config.getBoolean("maintenance");
    }
    public static void toggleMaintenanceMode() {
        config.set("maintenance", !getMaintenanceMode());
        save();
    }
    public static boolean getSpawnOnJoin() {
        return config.getBoolean("spawnOnJoin");
    }
    public static void setSpawn(Location loc) {
        config.set("spawn" + ".world", loc.getWorld().getName());
        config.set("spawn" + ".X", loc.getX());
        config.set("spawn" + ".Y", loc.getY());
        config.set("spawn" + ".Z", loc.getZ());
        config.set("spawn" + ".pitch",(double) loc.getPitch());
        config.set("spawn" + ".yaw",(double) loc.getYaw());
        save();
    }
    public static Location getSpawn() {
        World world = Bukkit.getWorld(config.getString("spawn" + ".world"));
        double x = config.getDouble("spawn" + ".X");
        double y = config.getDouble("spawn" + ".Y");
        double z = config.getDouble("spawn" + ".Z");
        float pitch = (float) config.getDouble("spawn" + ".pitch");
        float yaw = (float) config.getDouble("spawn" + ".yaw");
        return new Location(world, x, y, z, yaw, pitch);
    }
    public static boolean getPreventKnowledgebookClick() {
        return config.getBoolean("preventKnowledgebookClick");
    }
    public static int getAutoSmeltPrice() {
        return config.getInt("autoSmelt" + ".price");
    }
    public static List<String> getAutoSmeltWorlds() {
        return (List<String>) config.getList("autoSmelt" + ".enabledWorlds");
    }
    public static boolean getMovedQuickly() {
        return config.getBoolean("messages" + ".movedQuickly");
    }
    public static boolean getMovedWrongly() {
        return config.getBoolean("messages" + ".movedWrongly");
    }
    public static String getF3name() {
        return config.getString("f3name").replace("&", "§");
    }
    public static boolean getTablistEnabled() {
        return config.getBoolean("tablist" + ".enabled");
    }
    public static List<String> getHeader() {
        return (List<String>) config.getList("tablist" + ".header");
    }
    public static List<String> getFooter() {
        return (List<String>) config.getList("tablist" + ".footer");
    }
    public static boolean getPlayerlistHoverEnabled() {
        return config.getBoolean("playerlistHover" + ".enabled");
    }
    public static List<String> getPlayerlistHoverLines() {
        return (List<String>) config.getList("playerlistHover" + ".lines");
    }
    public static boolean getBossbarEnabled() {
        return config.getBoolean("bossbar" + ".enabled");
    }
    public static String getBossbarColor() {
        return config.getString("bossbar" + ".color");
    }
    public static String getBossbarText() {
        return config.getString("bossbar" + ".text");
    }
    public static String getBossbarStyle() {
        return config.getString("bossbar" + ".style");
    }
    public static float getBossbarProgress() {
        return (float) config.getDouble("bossbar" + ".progress");
    }
    public static boolean getActionbarEnabled() {
        return config.getBoolean("actionbar" + ".enabled");
    }
    public static String getActionbarText() {
        return config.getString("actionbar" + ".text");
    }

}
