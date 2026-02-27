package de.pascalpex.deepslatemc.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import org.bukkit.command.SimpleCommandMap;

import java.util.List;
import java.util.Set;

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

    public static void registerBrigadierCommands() {
        registerInternalCommand(UnbreakableCommand.create(), "Makes an item unbreakable", List.of("unbreakable"));
    }

    private static void registerInternalCommand(final LiteralCommandNode<CommandSourceStack> node, final String description, final List<String> aliases) {
        io.papermc.paper.command.brigadier.PaperCommands.INSTANCE.registerWithFlagsInternal(
            null,
            "deepslate",
            "Paper",
            node,
            description,
            aliases,
            Set.of()
        );
    }

}
