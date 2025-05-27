package de.pascalpex.deepslatemc.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static de.pascalpex.deepslatemc.files.ConfigEntry.*;

public class Config {

    public static final File configFile = new File("deepslate", "deepslate.yml");
    public static final FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    private static final Logger LOGGER = LogManager.getLogger(Config.class.getSimpleName());

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
            for(ConfigEntry configEntry : ConfigEntry.values()) {
                if(configEntry.defaultValue == null) {
                    continue;
                }
                if(!config.contains(configEntry.key)) {
                    config.set(configEntry.key, configEntry.defaultValue);
                }
            }
            save();
            LOGGER.info("Loaded DeepslateMC config file");
        } catch (IOException | InvalidConfigurationException e) {
            LOGGER.error("Error loading DeepslateMC config", e);
        }
    }

    public static void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            LOGGER.error("Error saving DeepslateMC config", e);
        }
    }

    public static boolean getOpActive() {
        return config.getBoolean(OP_COMMAND_ACTIVE.key);
    }
    public static String getDiscordLink() {
        return config.getString(DISCORD_LINK.key);
    }
    public static String getBuildworld() {
        return config.getString(BUILDWORLD.key);
    }
    public static void setBuildworld(String worldName) {
        config.set(BUILDWORLD.key, worldName);
        save();
    }
    public static boolean getMaintenanceMode() {
        return config.getBoolean(MAINTENANCE_MODE.key);
    }
    public static void toggleMaintenanceMode() {
        config.set(MAINTENANCE_MODE.key, !getMaintenanceMode());
        save();
    }
    public static boolean getSpawnOnJoin() {
        return config.getBoolean(SPAWN_ON_JOIN.key);
    }
    public static void setSpawn(Location loc) {
        config.set(SPAWN_WORLD.key, loc.getWorld().getName());
        config.set(SPAWN_X.key, loc.getX());
        config.set(SPAWN_Y.key, loc.getY());
        config.set(SPAWN_Z.key, loc.getZ());
        config.set(SPAWN_PITCH.key, (double) loc.getPitch());
        config.set(SPAWN_YAW.key, (double) loc.getYaw());
        save();
    }
    public static Location getSpawn() {
        World world = Bukkit.getWorld(config.getString(SPAWN_WORLD.key));
        double x = config.getDouble(SPAWN_X.key);
        double y = config.getDouble(SPAWN_Y.key);
        double z = config.getDouble(SPAWN_Z.key);
        float pitch = (float) config.getDouble(SPAWN_PITCH.key);
        float yaw = (float) config.getDouble(SPAWN_YAW.key);
        return new Location(world, x, y, z, yaw, pitch);
    }
    public static boolean getPreventKnowledgebookClick() {
        return config.getBoolean(KNOWLEDGE_BOOK_CLICK.key);
    }
    public static boolean getMovedQuickly() {
        return config.getBoolean(MOVED_QUICKLY_MESSAGES.key);
    }
    public static boolean getMovedWrongly() {
        return config.getBoolean(MOVED_WRONGLY_MESSAGES.key);
    }
    public static String getF3name() {
        return config.getString(F3_NAME.key).replace("&", "§");
    }
    public static boolean getTablistEnabled() {
        return config.getBoolean(TABLIST_ENABLED.key);
    }
    public static List<String> getHeader() {
        return config.getList(TABLIST_HEADER.key).stream().map(Object::toString).toList();
    }
    public static List<String> getFooter() {
        return config.getList(TABLIST_FOOTER.key).stream().map(Object::toString).toList();
    }
    public static boolean getPlayerlistHoverEnabled() {
        return config.getBoolean(PLAYERLIST_HOVER_ENABLED.key);
    }
    public static List<String> getPlayerlistHoverLines() {
        return config.getList(PLAYERLIST_HOVER_LINES.key).stream().map(Object::toString).toList();
    }
    public static boolean getBossbarEnabled() {
        return config.getBoolean(BOSSBAR_ENABLED.key);
    }
    public static String getBossbarColor() {
        return config.getString(BOSSBAR_COLOR.key);
    }
    public static String getBossbarText() {
        return config.getString(BOSSBAR_TEXT.key);
    }
    public static String getBossbarStyle() {
        return config.getString(BOSSBAR_STYLE.key);
    }
    public static float getBossbarProgress() {
        return (float) config.getDouble(BOSSBAR_PROGRESS.key);
    }
    public static boolean getActionbarEnabled() {
        return config.getBoolean(ACTIONBAR_ENABLED.key);
    }
    public static String getActionbarText() {
        return config.getString(ACTIONBAR_TEXT.key);
    }
    public static boolean getMinimessageMotd() {
        return config.getBoolean(MINIMESSAGE_MOTD.key);
    }
    public static boolean getMinimessageMessages() {
        return config.getBoolean(MINIMESSAGE_MESSAGES.key);
    }
    public static boolean getSendSpectatorModePackets() {
        return config.getBoolean(SPECTATOR_MODE_PACKETS.key);
    }

}
