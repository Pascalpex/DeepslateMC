package de.pascalpex.deepslatemc.commands;

import net.minecraft.server.MinecraftServer;
import org.bukkit.command.SimpleCommandMap;

public class DeepslateCmdRegisterer {

    public static void registerCommands(final MinecraftServer server) {
        SimpleCommandMap commandMap = server.server.getCommandMap();

        commandMap.register("deepslate", "Deepslate", new DeepslateCommand("deepslate"));
        commandMap.register("discord", "Deepslate", new DiscordCommand("discord"));
        commandMap.register("dc", "Deepslate", new DiscordCommand("dc"));
        commandMap.register("cc", "Deepslate", new ClearchatCommand("cc"));
        commandMap.register("clearchat", "Deepslate", new ClearchatCommand("clearchat"));
        commandMap.register("setbuildworld", "Deepslate", new BuildworldCommand("setbuildworld"));
        commandMap.register("buildworld", "Deepslate", new BuildworldCommand("buildworld"));
        commandMap.register("maintenance", "Deepslate", new MaintenanceMode("maintenance"));
        commandMap.register("help", "Deepslate", new HelpCommand("help"));
        commandMap.register("setspawn", "Deepslate", new SpawnCommand("setspawn"));
        commandMap.register("spawn", "Deepslate", new SpawnCommand("spawn"));
        commandMap.register("lobby", "Deepslate", new SpawnCommand("lobby"));
    }

}
