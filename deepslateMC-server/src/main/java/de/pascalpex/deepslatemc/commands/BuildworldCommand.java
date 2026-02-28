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

public class BuildworldCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("buildworld")
            .executes(new BuildworldCommand())
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.buildworld"))
            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        CommandSender sender = commandContext.getSource().getSender();
        try {
            commandContext.getSource().getExecutor().teleport(Bukkit.getWorld(Config.getBuildworld()).getSpawnLocation());
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.BUILDWORLD_WELCOME)));
        } catch (IllegalArgumentException e) {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.BUILDWORLD_NOT_SET)));
        }
        return SINGLE_SUCCESS;
    }
}
