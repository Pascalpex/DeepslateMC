package net.pascalpex.deepslatemc.commands;
    
import net.pascalpex.deepslatemc.Config;
import net.pascalpex.deepslatemc.MessagesFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
