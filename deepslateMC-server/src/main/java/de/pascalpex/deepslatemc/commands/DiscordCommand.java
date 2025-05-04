package de.pascalpex.deepslatemc.commands;
    
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand extends Command {

    public DiscordCommand(String name) {
        super(name);
        this.description = "Discord link command";
        this.usageMessage = "/discord";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, String commandLabel, String @NotNull [] args) {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        if (commandLabel.equalsIgnoreCase("dc") || commandLabel.equalsIgnoreCase("discord")) {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.DISCORD_MESSAGE)));
        }
        return true;
    }
}
