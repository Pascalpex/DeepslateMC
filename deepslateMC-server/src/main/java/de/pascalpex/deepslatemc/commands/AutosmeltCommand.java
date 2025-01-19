package net.pascalpex.deepslatemc.commands;

import net.pascalpex.deepslatemc.Config;
import net.pascalpex.deepslatemc.MessagesFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AutosmeltCommand extends Command {

    public AutosmeltCommand(String name) {
        super(name);
        this.description = "Enchants a pickaxe with autosmelt";
        this.usageMessage = "/autosmelt";
        this.setPermission("deepslate.autosmelt");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        String prefix = MessagesFile.getPrefix() + " ";
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(label.equalsIgnoreCase("autosmelt")) {
                if(player.hasPermission("deepslate.autosmelt")) {
                    if (Config.getAutoSmeltWorlds().contains(player.getWorld().getName())) {
                        if (player.getLevel() >= Config.getAutoSmeltPrice()) {
                            if (player.getItemInHand().getType().toString().contains("PICKAXE")) {
                                if (!player.getItemInHand().getItemMeta().hasLore()) {
                                    ItemStack item = player.getItemInHand();
                                    ItemMeta meta = item.getItemMeta();
                                    ArrayList<String> lore = new ArrayList<>();
                                    lore.add("Auto Smelt");
                                    meta.setLore(lore);
                                    item.setItemMeta(meta);
                                    player.getInventory().setItemInHand(item);
                                    player.sendMessage(prefix + MessagesFile.getAutoSmeltEnchanted());
                                    player.giveExpLevels(-Config.getAutoSmeltPrice());
                                } else {
                                    player.sendMessage(prefix + MessagesFile.getAutoSmeltUnable());
                                }
                            } else {
                                player.sendMessage(prefix + MessagesFile.getAutoSmeltNoPickaxe());
                            }
                        } else {
                            player.sendMessage(prefix + MessagesFile.getAutoSmeltLevels().replace("%price%", String.valueOf(Config.getAutoSmeltPrice())));
                        }
                    } else {
                        player.sendMessage(prefix + MessagesFile.getAutoSmeltNotHere());
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
