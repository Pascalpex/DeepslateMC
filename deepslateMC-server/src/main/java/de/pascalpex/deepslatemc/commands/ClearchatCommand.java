package de.pascalpex.deepslatemc.commands;
    
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
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
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        if (commandLabel.equalsIgnoreCase("cc") || commandLabel.equalsIgnoreCase("clearchat")) {
            if(sender.hasPermission("deepslate.clearchat")) {
                for (int x = 0; x < 150; x++){
                    Bukkit.broadcast(Component.empty());
                }
                Component clearMessage = MessagesFile.getMessage(MessagesEntry.CLEARED_CHAT).replaceText(TextReplacementConfig.builder().match("%clearer%").replacement(sender.getName()).build());
                Bukkit.broadcast(prefix.append(clearMessage));
            } else {
                sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_PERMISSIONS)));
            }
        }
        return true;
    }
}
