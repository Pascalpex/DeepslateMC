package de.pascalpex.deepslatemc.commands;
    
import de.pascalpex.deepslatemc.files.Config;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BuildworldCommand extends Command {

    public BuildworldCommand(String name) {
        super(name);
        this.description = "Teleports builders to the buildworld";
        this.usageMessage = "/buildworld";
        this.setPermission("deepslate.buildworld");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String @NotNull [] args) {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        if (sender instanceof Player player) {
            if(label.equalsIgnoreCase("setbuildworld")) {
                if(player.hasPermission("deepslate.setbuildworld")) {
                    World world = player.getLocation().getWorld();
                    Config.setBuildworld(world.getName());
                    player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.BUILDWORLD_SET)));
                } else {
                    player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_PERMISSIONS)));
                }
            }
            if(label.equalsIgnoreCase("buildworld")) {
                if(player.hasPermission("deepslate.buildworld")) {
                    try {
                        player.teleport(Bukkit.getWorld(Config.getBuildworld()).getSpawnLocation());
                        player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.BUILDWORLD_WELCOME)));
                    } catch (IllegalArgumentException e) {
                        player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.BUILDWORLD_NOT_SET)));
                    }
                } else {
                    player.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_PERMISSIONS)));
                }
            }
        } else {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.ONLY_FOR_PLAYERS)));
        }
        return true;
    }
}
