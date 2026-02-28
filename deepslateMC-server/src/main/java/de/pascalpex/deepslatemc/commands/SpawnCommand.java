package de.pascalpex.deepslatemc.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.pascalpex.deepslatemc.files.Config;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("spawn")
            .executes(new SpawnCommand())
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.spawn"))
            .build();
    }

    public static void playerJoin(Player player) {
        if (Config.getSpawnOnJoin()) {
            try {
                player.teleport(Config.getSpawn());
            } catch (Exception e) {
                Bukkit.getLogger().warning("The option spawnOnJoin in the deepslate.yml file is turned on but there is no spawn set!");
            }
        }
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        CommandSender sender = commandContext.getSource().getSender();
        try {
            commandContext.getSource().getExecutor().teleport(Config.getSpawn());
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.SPAWN_TELEPORTED)));
        } catch (IllegalArgumentException e) {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.SPAWN_NOT_SET)));
        }
        return SINGLE_SUCCESS;
    }
}
