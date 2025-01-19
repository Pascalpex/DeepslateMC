package de.pascalpex.deepslatemc.commands;
    
import de.pascalpex.deepslatemc.Config;
import de.pascalpex.deepslatemc.MessagesFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DiscordCommand extends Command {

    public DiscordCommand(String name) {
        super(name);
        this.description = "Discord link command";
        this.usageMessage = "/discord";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        String prefix = MessagesFile.getPrefix() + " ";
        if (commandLabel.equalsIgnoreCase("dc") || commandLabel.equalsIgnoreCase("discord")) {
            String link = Config.getDiscordLink();
            sender.sendMessage(prefix + MessagesFile.getDiscordMessage().replace("%link%", link));
        }
        return true;
    }
}
