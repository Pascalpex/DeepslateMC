package de.pascalpex.deepslatemc.files;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.ComponentDecoder;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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

    public static final File configFile = new File("deepslate", "messages.yml");
    public static final FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    private static final Logger LOGGER = LogManager.getLogger(MessagesFile.class.getSimpleName());
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.legacyAmpersand();

    private static final String COMMAND_USAGE = "/deepslate [reload | version]";

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

    public static Component getMessage(MessagesEntry key) {
        return switch (key) {
            case HELP_MESSAGE -> getHelpMessage();
            case DISCORD_MESSAGE -> getDiscordMessage();
            case WRONG_SYNTAX -> getWrongSyntaxMessage();
            default -> processColors(config.getString(key.key));
        };
    }

    private static Component processColors(String message) {
        ComponentDecoder<String, ?> decoder = Config.getMinimessageMessages() ? miniMessage : legacySerializer;
        return decoder.deserialize(message);
    }

    private static Component getDiscordMessage() {
        String message = config.getString(DISCORD_MESSAGE.key);
        message = message.replace("%link%", Config.getDiscordLink());
        return processColors(message);
    }

    private static Component getWrongSyntaxMessage() {
        String message = config.getString(WRONG_SYNTAX.key);
        message = message.replace("%usage%", COMMAND_USAGE);
        return processColors(message);
    }

    private static Component getHelpMessage() {
        List<String> messages = config.getList(HELP_MESSAGE.key).stream().map(Object::toString).toList();
        List<Component> components = messages.stream()
            .map(MessagesFile::processColors)
            .toList();

        return Component.join(JoinConfiguration.separator(Component.newline()), components);
    }


}
