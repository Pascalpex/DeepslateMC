package de.pascalpex.deepslatemc.commands;

import de.pascalpex.deepslatemc.files.Config;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import de.pascalpex.deepslatemc.util.ActionbarUtil;
import de.pascalpex.deepslatemc.util.BossbarUtil;
import de.pascalpex.deepslatemc.util.TablistUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeepslateCommand extends Command {

    public DeepslateCommand(String name) {
        super(name);
        this.description = "Deepslate command";
        this.usageMessage = "/deepslate [reload | version]";
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, String[] args, Location location) throws IllegalArgumentException {
        if (args.length == 1 && sender.hasPermission("deepslate.command")) {
            return Stream.of("reload", "version")
                .filter(arg -> arg.startsWith(args[0].toLowerCase()))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, String commandLabel, String @NotNull [] args) {
        if (commandLabel.equalsIgnoreCase("deepslate")) {
            Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
            if (sender.hasPermission("deepslate.command")) {
                if (args.length == 0) {
                    sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.WRONG_SYNTAX)));
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        MessagesFile.load();
                        Config.load();
                        TablistUtil.reloadTablist();
                        BossbarUtil.reloadBossbar();
                        ActionbarUtil.reloadActionbar();
                        prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
                        sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.CONFIG_RELOADED)));
                    } else {
                        if (args[0].equalsIgnoreCase("version")) {
                            String deepslateVersion = this.getClass().getPackage().getImplementationVersion().replace('"', ' ').replace(" ", "");
                            sender.sendMessage(prefix.append(Component.text("This server is running DeepslateMC from Pascalpex: " + deepslateVersion).color(NamedTextColor.GOLD)));
                        } else {
                            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.WRONG_SYNTAX)));
                        }
                    }
                }
                if (args.length >= 2) {
                    sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.WRONG_SYNTAX)));
                }
            } else {
                sender.sendMessage(prefix.append(Component.text("This server is running DeepslateMC from Pascalpex").color(NamedTextColor.GOLD)));
            }
        }
        return true;
    }

}
