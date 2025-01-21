package de.pascalpex.deepslatemc.commands;

import de.pascalpex.deepslatemc.files.Config;
import de.pascalpex.deepslatemc.files.MessagesFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MaintenanceMode extends Command {
    public MaintenanceMode(String name) {
        super(name);
        this.description = "Toggles the maintenance mode";
        this.usageMessage = "/maintenance";
        setPermission("deepslate.maintenance");
    }

    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String @NotNull [] args) {
        String prefix = MessagesFile.getPrefix() + " ";
        if (sender instanceof Player player) {
            if (label.equalsIgnoreCase("maintenance") && player.hasPermission("deepslate.maintenance")) {
                if (Config.getMaintenanceMode()) {
                    player.sendMessage(prefix + MessagesFile.getMaintenanceOff());
                } else {
                    player.sendMessage(prefix + MessagesFile.getMaintenanceOn());
                }
                Config.toggleMaintenanceMode();
            } else {
                player.sendMessage(prefix + MessagesFile.getNoPermissions());
            }
        }
        else if (label.equalsIgnoreCase("maintenance")) {
            if (Config.getMaintenanceMode()) {
                sender.sendMessage(prefix + MessagesFile.getMaintenanceOff());
            } else {
                sender.sendMessage(prefix + MessagesFile.getMaintenanceOn());
            }
            Config.toggleMaintenanceMode();
        }

        return true;
    }
}
