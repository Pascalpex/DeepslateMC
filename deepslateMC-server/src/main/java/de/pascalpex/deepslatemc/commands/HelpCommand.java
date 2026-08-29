package de.pascalpex.deepslatemc.commands;
    
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class HelpCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("help")
            .executes(new HelpCommand())
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.help"))
            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        commandContext.getSource().getSender().sendMessage(MessagesFile.getMessage(MessagesEntry.HELP_MESSAGE));
        return SINGLE_SUCCESS;
    }
}
