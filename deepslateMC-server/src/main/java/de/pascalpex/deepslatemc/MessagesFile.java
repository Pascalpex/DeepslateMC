package de.pascalpex.deepslatemc;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessagesFile {

    public static File configFile = new File("deepslate", "messages.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

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
            if(!config.contains("prefix")) {
                config.set("prefix", "&0[&8DeepslateMC&0]");
            }
            if(!config.contains("wrongSyntax")) {
                config.set("wrongSyntax", "&cWrong usage, use: &b%usage%");
            }
            if(!config.contains("configReloaded")) {
                config.set("configReloaded", "&aConfig got reloaded successfully");
            }
            if(!config.contains("onlyForPlayers")) {
                config.set("onlyForPlayers", "&cThis command can only be used by players");
            }
            if(!config.contains("discordMessage")) {
                config.set("discordMessage", "&6The link to our Discord server: &b%link%");
            }
            if(!config.contains("noPermissions")) {
                config.set("noPermissions", "&cYou have no permission to do this");
            }
            if(!config.contains("clearedChat")) {
                config.set("clearedChat", "&6The chat got cleared by &b%clearer%");
            }
            if(!config.contains("buildworldSet")) {
                config.set("buildworldSet", "&aThe buildworld got set successfully");
            }
            if(!config.contains("buildworldWelcome")) {
                config.set("buildworldWelcome", "&aWelcome to the buildworld");
            }
            if(!config.contains("buildworldNotSet")) {
                config.set("buildworldNotSet", "&cThe buildworld is not set");
            }
            if (!config.contains("maintenanceKick")) {
                config.set("maintenanceKick", "&cThe server is currently in maintenance mode");
            }
            if (!config.contains("maintenanceOn")) {
                config.set("maintenanceOn", "&aYou turned on the maintenance mode");
            }
            if (!config.contains("maintenanceOff")) {
                config.set("maintenanceOff", "&aYou turned off the maintenance mode");
            }
            if (!config.contains("helpMessage")) {
                List<String> message = new ArrayList<>();
                message.add("&2&l--Help page--");
                message.add("&6You can find the most important commands at");
                message.add("&9&l/warp &9&lInfo&r&6. If you have more");
                message.add("&6questions, please ask a &9team member.");
                config.set("helpMessage", message);
            }
            if(!config.contains("spawnSet")) {
                config.set("spawnSet", "&aThe spawn got set successfully");
            }
            if(!config.contains("spawnNotSet")) {
                config.set("spawnNotSet", "&cThe spawn is not set");
            }
            if(!config.contains("spawnTeleport")) {
                config.set("spawnTeleport", "&aYou got teleported to the spawn");
            }
            if(!config.contains("autoSmeltEnchanted")) {
                config.set("autoSmeltEnchanted", "&aYour pickaxe now has autosmelt");
            }
            if(!config.contains("autoSmeltUnable")) {
                config.set("autoSmeltUnable", "&cThis pickaxe cannot be enchanted");
            }
            if(!config.contains("autoSmeltNoPickaxe")) {
                config.set("autoSmeltNoPickaxe", "&cYou have to hold a pickaxe in your hand");
            }
            if(!config.contains("autoSmeltLevels")) {
                config.set("autoSmeltLevels", "&cYou need %price% experience levels");
            }
            if(!config.contains("autoSmeltNotHere")) {
                config.set("autoSmeltNotHere", "&cAutosmelt does not work here");
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

    public static String getPrefix() {
        String prefix = config.getString("prefix");
        return prefix.replace("&", "§");
    }
    public static String getWrongSyntax() {
        String prefix = config.getString("wrongSyntax");
        return prefix.replace("&", "§");
    }
    public static String getConfigReloaded() {
        String prefix = config.getString("configReloaded");
        return prefix.replace("&", "§");
    }
    public static String getOnlyForPlayers() {
        String prefix = config.getString("onlyForPlayers");
        return prefix.replace("&", "§");
    }
    public static String getDiscordMessage() {
        String prefix = config.getString("discordMessage");
        return prefix.replace("&", "§");
    }
    public static String getNoPermissions() {
        String prefix = config.getString("noPermissions");
        return prefix.replace("&", "§");
    }
    public static String getClearedChat() {
        String prefix = config.getString("clearedChat");
        return prefix.replace("&", "§");
    }
    public static String getBuildworldSet() {
        String prefix = config.getString("buildworldSet");
        return prefix.replace("&", "§");
    }
    public static String getBuildworldWelcome() {
        String prefix = config.getString("buildworldWelcome");
        return prefix.replace("&", "§");
    }
    public static String getBuildworldNotSet() {
        String prefix = config.getString("buildworldNotSet");
        return prefix.replace("&", "§");
    }
    public static String getMaintenanceKick() {
        String prefix = config.getString("maintenanceKick");
        return prefix.replace("&", "§");
    }
    public static String getMaintenanceOn() {
        String prefix = config.getString("maintenanceOn");
        return prefix.replace("&", "§");
    }
    public static String getMaintenanceOff() {
        String prefix = config.getString("maintenanceOff");
        return prefix.replace("&", "§");
    }
    public static String getHelpMessage() {
        List<String> messages = (List<String>) config.getList("helpMessage");
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
        String prefix = config.getString("spawnSet");
        return prefix.replace("&", "§");
    }
    public static String getSpawnNotSet() {
        String prefix = config.getString("spawnNotSet");
        return prefix.replace("&", "§");
    }
    public static String getSpawnTeleport() {
        String prefix = config.getString("spawnTeleport");
        return prefix.replace("&", "§");
    }
    public static String getAutoSmeltEnchanted() {
        String prefix = config.getString("autoSmeltEnchanted");
        return prefix.replace("&", "§");
    }
    public static String getAutoSmeltUnable() {
        String prefix = config.getString("autoSmeltUnable");
        return prefix.replace("&", "§");
    }
    public static String getAutoSmeltNoPickaxe() {
        String prefix = config.getString("autoSmeltNoPickaxe");
        return prefix.replace("&", "§");
    }
    public static String getAutoSmeltLevels() {
        String prefix = config.getString("autoSmeltLevels");
        return prefix.replace("&", "§");
    }
    public static String getAutoSmeltNotHere() {
        String prefix = config.getString("autoSmeltNotHere");
        return prefix.replace("&", "§");
    }

}
