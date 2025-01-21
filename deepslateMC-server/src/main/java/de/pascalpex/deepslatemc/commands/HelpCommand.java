package de.pascalpex.deepslatemc.commands;
    
import de.pascalpex.deepslatemc.files.MessagesFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HelpCommand extends Command {

    public HelpCommand(String name) {
        super(name);
        this.description = "Shows the configured help page";
        this.usageMessage = "/help";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, String label, String @NotNull [] args) {
        if (label.equalsIgnoreCase("help")) {
            sender.sendMessage(MessagesFile.getHelpMessage());
        }
        return true;
    }

}
