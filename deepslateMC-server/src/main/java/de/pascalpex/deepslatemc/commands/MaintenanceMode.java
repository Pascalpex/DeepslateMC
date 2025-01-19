package net.pascalpex.deepslatemc.commands;

import net.pascalpex.deepslatemc.Config;
import net.pascalpex.deepslatemc.MessagesFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaintenanceMode extends Command {
    public MaintenanceMode(String name) {
        super(name);
        this.description = "Toggles the maintenance mode";
        this.usageMessage = "/maintenance";
        setPermission("deepslate.maintenance");
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        String prefix = MessagesFile.getPrefix() + " ";
        if (sender instanceof Player) {
            Player player = (Player)sender;
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
