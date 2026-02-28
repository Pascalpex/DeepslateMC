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

public class MaintenanceMode implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("maintenance")
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.maintenance"))
            .executes(new MaintenanceMode())
            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        CommandSender sender = commandContext.getSource().getSender();

        Config.toggleMaintenanceMode();
        if (Config.getMaintenanceMode()) {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.MAINTENANCE_ON)));
        } else {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.MAINTENANCE_OFF)));
        }
        return SINGLE_SUCCESS;
    }
}
