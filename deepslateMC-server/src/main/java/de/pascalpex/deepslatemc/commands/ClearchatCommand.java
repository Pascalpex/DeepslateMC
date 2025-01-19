package de.pascalpex.deepslatemc.commands;
    
import de.pascalpex.deepslatemc.MessagesFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ClearchatCommand extends Command {
    public ClearchatCommand(String name) {
        super(name);
        this.description = "Clears the chat";
        this.usageMessage = "/clearchat";
        this.setPermission("deepslate.clearchat");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, String commandLabel, String @NotNull [] args) {
        String prefix = MessagesFile.getPrefix() + " ";
        if (commandLabel.equalsIgnoreCase("cc") || commandLabel.equalsIgnoreCase("clearchat")) {
            if(sender.hasPermission("deepslate.clearchat")) {
                for (int x = 0; x < 150; x++){
                    Bukkit.broadcastMessage("");
                }
                Bukkit.broadcastMessage(prefix + MessagesFile.getClearedChat().replace("%clearer%", sender.getName()));
            } else {
                sender.sendMessage(prefix + MessagesFile.getNoPermissions());
            }
        }
        return true;
    }
}
