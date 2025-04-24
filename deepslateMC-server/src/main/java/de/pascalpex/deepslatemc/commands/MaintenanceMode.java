package de.pascalpex.deepslatemc.commands;

import de.pascalpex.deepslatemc.files.Config;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MaintenanceMode extends Command {
    public MaintenanceMode(String name) {
        super(name);
        this.description = "Toggles the maintenance mode";
        this.usageMessage = "/maintenance";
        setPermission("deepslate.maintenance");
    }

    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String @NotNull [] args) {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
            if (label.equalsIgnoreCase("maintenance") && sender.hasPermission("deepslate.maintenance")) {
                if (Config.getMaintenanceMode()) {
                    sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.MAINTENANCE_OFF)));
                } else {
                    sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.MAINTENANCE_ON)));
                }
                Config.toggleMaintenanceMode();
            } else {
                sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_PERMISSIONS)));
            }

        return true;
    }
}
