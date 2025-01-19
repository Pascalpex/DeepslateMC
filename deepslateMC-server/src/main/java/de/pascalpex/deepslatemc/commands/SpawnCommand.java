package net.pascalpex.deepslatemc.commands;

import net.pascalpex.deepslatemc.Config;
import net.pascalpex.deepslatemc.MessagesFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
    public boolean execute(CommandSender sender, String label, String[] args) {
        String prefix = MessagesFile.getPrefix() + " ";
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (label.equalsIgnoreCase("setspawn")) {
                if (player.hasPermission("deepslate.setspawn")) {
                    Config.setSpawn(player.getLocation());
                    player.sendMessage(prefix + MessagesFile.getSpawnSet());
                } else {
                    player.sendMessage(prefix + MessagesFile.getNoPermissions());
                }
            }
            if (label.equalsIgnoreCase("lobby") || label.equalsIgnoreCase("spawn")) {
                if (player.hasPermission("deepslate.spawn")) {
                    try {
                        player.teleport(Config.getSpawn());
                        player.sendMessage(prefix + MessagesFile.getSpawnTeleport());
                    } catch (Exception e) {
                        player.sendMessage(prefix + MessagesFile.getSpawnNotSet());
                    }
                } else {
                    player.sendMessage(prefix + MessagesFile.getNoPermissions());
                }
            }
        } else {
            sender.sendMessage(prefix + MessagesFile.getOnlyForPlayers());
        }
        return true;
    }

}
