package de.pascalpex.deepslatemc.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.pascalpex.deepslatemc.files.MessagesEntry.*;

public class MessagesFile {

    public static File configFile = new File("deepslate", "messages.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    private static final Logger LOGGER = LogManager.getLogger(MessagesFile.class.getSimpleName());

    public static void load() {
        try {
            configFile.getParentFile().mkdirs();
            if (!configFile.exists()) {
                configFile.createNewFile();
                List<String> header = new ArrayList<>();
                header.add("This is the messages file of DeepslateMC");
                header.add("You can find some pre-translated files here: https://pascalpex.de/files/deepslate/translations/");
                config.options().setHeader(header);
                save();
            }
            config.load(configFile);
            for(MessagesEntry messagesEntry : MessagesEntry.values()) {
                if(!config.contains(messagesEntry.key)) {
                    config.set(messagesEntry.key, messagesEntry.defaultValue);
                }
            }
            save();
            LOGGER.info("Loaded DeepslateMC messages file");
        } catch (IOException | InvalidConfigurationException e) {
            LOGGER.error("Error loading DeepslateMC messages", e);
        }
    }

    public static void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            LOGGER.error("Error saving DeepslateMC messages", e);
        }
    }

    public static String getPrefix() {
        String prefix = config.getString(PREFIX.key);
        return prefix.replace("&", "§");
    }
    public static String getWrongSyntax() {
        String prefix = config.getString(WRONG_SYNTAX.key);
        return prefix.replace("&", "§");
    }
    public static String getConfigReloaded() {
        String prefix = config.getString(CONFIG_RELOADED.key);
        return prefix.replace("&", "§");
    }
    public static String getOnlyForPlayers() {
        String prefix = config.getString(ONLY_FOR_PLAYERS.key);
        return prefix.replace("&", "§");
    }
    public static String getDiscordMessage() {
        String prefix = config.getString(DISCORD_MESSAGE.key);
        return prefix.replace("&", "§");
    }
    public static String getNoPermissions() {
        String prefix = config.getString(NO_PERMISSIONS.key);
        return prefix.replace("&", "§");
    }
    public static String getClearedChat() {
        String prefix = config.getString(CLEARED_CHAT.key);
        return prefix.replace("&", "§");
    }
    public static String getBuildworldSet() {
        String prefix = config.getString(BUILDWORLD_SET.key);
        return prefix.replace("&", "§");
    }
    public static String getBuildworldWelcome() {
        String prefix = config.getString(BUILDWORLD_WELCOME.key);
        return prefix.replace("&", "§");
    }
    public static String getBuildworldNotSet() {
        String prefix = config.getString(BUILDWORLD_NOT_SET.key);
        return prefix.replace("&", "§");
    }
    public static String getMaintenanceKick() {
        String prefix = config.getString(MAINTENANCE_KICK.key);
        return prefix.replace("&", "§");
    }
    public static String getMaintenanceOn() {
        String prefix = config.getString(MAINTENANCE_ON.key);
        return prefix.replace("&", "§");
    }
    public static String getMaintenanceOff() {
        String prefix = config.getString(MAINTENANCE_OFF.key);
        return prefix.replace("&", "§");
    }
    public static String getHelpMessage() {
        List<String> messages = (List<String>) config.getList(HELP_MESSAGE.key);
        StringBuilder message = new StringBuilder();
        for(int i = 0; i < messages.size(); i++) {
            message.append(messages.get(i).replace("&", "§"));
            if(i < messages.size() - 1) {
                message.append("\n§r");
            }
        }
        return message.toString();
    }
    public static String getSpawnSet() {
        String prefix = config.getString(SPAWN_SET.key);
        return prefix.replace("&", "§");
    }
    public static String getSpawnNotSet() {
        String prefix = config.getString(SPAWN_NOT_SET.key);
        return prefix.replace("&", "§");
    }
    public static String getSpawnTeleport() {
        String prefix = config.getString(SPAWN_TELEPORTED.key);
        return prefix.replace("&", "§");
    }

}
