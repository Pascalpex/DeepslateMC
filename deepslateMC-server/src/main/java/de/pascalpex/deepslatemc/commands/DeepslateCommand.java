package de.pascalpex.deepslatemc.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.pascalpex.deepslatemc.files.Config;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import de.pascalpex.deepslatemc.util.ActionbarUtil;
import de.pascalpex.deepslatemc.util.BossbarUtil;
import de.pascalpex.deepslatemc.util.ServerLinkUtil;
import de.pascalpex.deepslatemc.util.TablistUtil;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class DeepslateCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("deepslate")
            .executes(new DeepslateCommand())
            .then(Commands.literal("reload")
                .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.command"))
                .executes(new DeepslateReloadCommand()))
            .then(Commands.literal("version")
                .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.command"))
                .executes(new DeepslateVersionCommand()))
            .build();
    }

    private static class DeepslateReloadCommand implements Command<CommandSourceStack> {
        @Override
        public int run(CommandContext<CommandSourceStack> commandContext) {
            MessagesFile.load();
            Config.load();
            TablistUtil.reloadTablist();
            BossbarUtil.reloadBossbar();
            ActionbarUtil.reloadActionbar();
            ServerLinkUtil.loadLinks();
            Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
            commandContext.getSource().getSender().sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.CONFIG_RELOADED)));
            return SINGLE_SUCCESS;
        }
    }

    private static class DeepslateVersionCommand implements Command<CommandSourceStack> {
        @Override
        public int run(CommandContext<CommandSourceStack> commandContext) {
            Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
            String deepslateVersion = Bukkit.getVersionMessage();
            commandContext.getSource().getSender().sendMessage(prefix.append(Component.text(deepslateVersion + " made by Pascalpex").color(NamedTextColor.GOLD)));
            return SINGLE_SUCCESS;
        }
    }

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        CommandSender sender = commandContext.getSource().getSender();
        if(commandContext.getSource().getSender().hasPermission("deepslate.command")) {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.WRONG_SYNTAX)));
        } else {
            sender.sendMessage(prefix.append(Component.text("This server is running DeepslateMC made by Pascalpex").color(NamedTextColor.GOLD)));
        }
        return SINGLE_SUCCESS;
    }
}
