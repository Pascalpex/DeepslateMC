package de.pascalpex.deepslatemc.commands;

import de.pascalpex.deepslatemc.files.Config;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand extends Command {

    public SpawnCommand(String name) {
        super(name);
        this.description = "Teleports you to the spawn";
        this.usageMessage = "/spawn";
        this.setPermission("deepslate.spawn");
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
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String @NotNull [] args) {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        if (sender instanceof Player player) {
            if (label.equalsIgnoreCase("setspawn")) {
                if (player.hasPermission("deepslate.setspawn")) {
                    Config.setSpawn(player.getLocation());
                    player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.SPAWN_SET)));
                } else {
                    player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_PERMISSIONS)));
                }
            }
            if (label.equalsIgnoreCase("lobby") || label.equalsIgnoreCase("spawn")) {
                if (player.hasPermission("deepslate.spawn")) {
                    try {
                        player.teleport(Config.getSpawn());
                        player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.SPAWN_TELEPORTED)));
                    } catch (IllegalArgumentException e) {
                        player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.SPAWN_NOT_SET)));
                    }
                } else {
                    player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_PERMISSIONS)));
                }
            }
        } else {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.ONLY_FOR_PLAYERS)));
        }
        return true;
    }

}
