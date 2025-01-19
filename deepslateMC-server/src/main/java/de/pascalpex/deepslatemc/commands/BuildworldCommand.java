package de.pascalpex.deepslatemc.commands;
    
import de.pascalpex.deepslatemc.Config;
import de.pascalpex.deepslatemc.MessagesFile;
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
        String prefix = MessagesFile.getPrefix() + " ";
        if (sender instanceof Player player) {
            if(label.equalsIgnoreCase("setbuildworld")) {
                if(player.hasPermission("deepslate.setbuildworld")) {
                    World world = player.getLocation().getWorld();
                    Config.setBuildworld(world.getName());
                    player.sendMessage(prefix + MessagesFile.getBuildworldSet());
                } else {
                    player.sendMessage(prefix + MessagesFile.getNoPermissions());
                }
            }
            if(label.equalsIgnoreCase("buildworld")) {
                if(player.hasPermission("deepslate.buildworld")) {
                    try {
                        player.teleport(Bukkit.getWorld(Config.getBuildworld()).getSpawnLocation());
                        player.sendMessage(prefix + MessagesFile.getBuildworldWelcome());
                    } catch (Exception e) {
                        player.sendMessage(prefix + MessagesFile.getBuildworldNotSet());
                    }
                } else {
                    player.sendMessage(prefix + MessagesFile.getNoPermissions());
                }
            }
        } else {
            sender.sendMessage(prefix + MessagesFile.getOnlyForPlayers());
        }
        return true;
    }
}
