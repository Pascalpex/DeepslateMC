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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetspawnCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("setspawn")
            .executes(new SetspawnCommand())
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.setspawn"))
            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        CommandSender sender = commandContext.getSource().getSender();
        Config.setSpawn(commandContext.getSource().getLocation());
        sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.SPAWN_SET)));
        return SINGLE_SUCCESS;
    }
}
