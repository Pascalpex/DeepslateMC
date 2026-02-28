package de.pascalpex.deepslatemc.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;

import java.util.List;
import java.util.Set;

public class DeepslateCmdRegisterer {

    public static void registerCommands() {
        registerInternalCommand(UnbreakableCommand.create(), "Makes an item unbreakable", List.of("unbreakable"));
        registerInternalCommand(HelpCommand.create(), "Shows the configured help page", List.of("help"));
        registerInternalCommand(DeepslateCommand.create(), "Main DeepslateMC command", List.of("deepslate"));
        registerInternalCommand(DiscordCommand.create(), "Shows the Discord link of this server", List.of("discord", "dc"));
        registerInternalCommand(ClearchatCommand.create(), "Clears the chat", List.of("clearchat", "cc"));
        registerInternalCommand(SpawnCommand.create(), "Teleports you to the spawn", List.of("spawn"));
        registerInternalCommand(SetspawnCommand.create(), "Clears the chat", List.of("setspawn"));
        registerInternalCommand(BuildworldCommand.create(), "Teleports builders to the buildworld", List.of("buildworld"));
        registerInternalCommand(SetbuildworldCommand.create(), "Sets the buildworld for builders", List.of("setbuildworld"));
        registerInternalCommand(MaintenanceMode.create(), "Toggles the maintenance mode", List.of("maintenance"));
    }

    private static void registerInternalCommand(final LiteralCommandNode<CommandSourceStack> node, final String description, final List<String> aliases) {
        io.papermc.paper.command.brigadier.PaperCommands.INSTANCE.registerWithFlagsInternal(
            null,
            "deepslate",
            "DeepslateMC",
            node,
            description,
            aliases,
            Set.of()
        );
    }

}
