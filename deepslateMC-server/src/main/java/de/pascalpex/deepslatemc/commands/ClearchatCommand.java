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
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.Bukkit;

public class ClearchatCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("clearchat")
            .executes(new ClearchatCommand())
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.clearchat"))
            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        Bukkit.broadcast(Component.text("\n".repeat(150)));
        Component clearMessage = MessagesFile.getMessage(MessagesEntry.CLEARED_CHAT).replaceText(TextReplacementConfig.builder().match("%clearer%").replacement(commandContext.getSource().getSender().getName()).build());
        Bukkit.broadcast(prefix.append(clearMessage));
        return SINGLE_SUCCESS;
    }
}
