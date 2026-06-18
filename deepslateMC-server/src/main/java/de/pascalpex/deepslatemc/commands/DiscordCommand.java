package de.pascalpex.deepslatemc.commands;
    
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;

public class DiscordCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("discord")
            .executes(new DiscordCommand())
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.discord"))
            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        commandContext.getSource().getSender().sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.DISCORD_MESSAGE)));
        return SINGLE_SUCCESS;
    }
}
