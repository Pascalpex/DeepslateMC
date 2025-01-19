package de.pascalpex.deepslatemc.commands;

import de.pascalpex.deepslatemc.Config;
import de.pascalpex.deepslatemc.MessagesFile;
import de.pascalpex.deepslatemc.util.ActionbarUtil;
import de.pascalpex.deepslatemc.util.BossbarUtil;
import de.pascalpex.deepslatemc.util.TablistUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        if (args.length == 1) {
            return Stream.of("reload", "version")
                .filter(arg -> arg.startsWith(args[0].toLowerCase()))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, String commandLabel, String @NotNull [] args) {
        if (commandLabel.equalsIgnoreCase("deepslate")) {
            String prefix = MessagesFile.getPrefix() + " ";
            if (sender instanceof Player player) {
                if (player.hasPermission("deepslate.command")) {
                    if (args.length == 0) {
                        player.sendMessage(prefix + MessagesFile.getWrongSyntax().replace("%usage%", "/deepslate [reload | version]"));
                    }
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            MessagesFile.load();
                            Config.load();
                            TablistUtil.reloadTablist();
                            BossbarUtil.reloadBossbar();
                            ActionbarUtil.reloadActionbar();
                            player.sendMessage(prefix + MessagesFile.getConfigReloaded());
                        } else {
                            if (args[0].equalsIgnoreCase("version")) {
                                String deepslateVersion = this.getClass().getPackage().getImplementationVersion().replace('"', ' ').replace(" ", "");
                                player.sendMessage(prefix + ChatColor.GOLD + "This server is running DeepslateMC from Pascalpex: " + deepslateVersion);
                            } else {
                                player.sendMessage(prefix + MessagesFile.getWrongSyntax().replace("%usage%", "/deepslate [reload | version]"));
                            }
                        }
                    }
                    if (args.length >= 2) {
                        player.sendMessage(prefix + MessagesFile.getWrongSyntax().replace("%usage%", "/deepslate [reload | version]"));
                    }
                } else {
                    player.sendMessage(prefix + ChatColor.GOLD + "This server is running DeepslateMC from Pascalpex");
                }
            } else {
                if (args.length == 0) {
                    sender.sendMessage(prefix + MessagesFile.getWrongSyntax().replace("%usage%", "/deepslate [reload | version]"));
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        MessagesFile.load();
                        Config.load();
                        TablistUtil.reloadTablist();
                        BossbarUtil.reloadBossbar();
                        ActionbarUtil.reloadActionbar();
                        sender.sendMessage(prefix + MessagesFile.getConfigReloaded());
                    } else {
                        if (args[0].equalsIgnoreCase("version")) {
                            String deepslateVersion = this.getClass().getPackage().getImplementationVersion().replace('"', ' ').replace(" ", "");
                            sender.sendMessage(prefix + ChatColor.GOLD + "This server is running DeepslateMC from Pascalpex: " + deepslateVersion);
                        } else {
                            sender.sendMessage(prefix + MessagesFile.getWrongSyntax().replace("%usage%", "/deepslate [reload | version]"));
                        }
                    }
                }
                if (args.length >= 2) {
                    sender.sendMessage(prefix + MessagesFile.getWrongSyntax().replace("%usage%", "/deepslate [reload | version]"));
                }
            }
        }
        return true;
    }

}
