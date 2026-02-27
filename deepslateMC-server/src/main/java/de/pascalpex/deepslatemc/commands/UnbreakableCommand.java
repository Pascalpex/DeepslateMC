package de.pascalpex.deepslatemc.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.pascalpex.deepslatemc.files.MessagesEntry;
import de.pascalpex.deepslatemc.files.MessagesFile;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UnbreakableCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("unbreakable")
            .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("deepslate.unbreakable"))
            .executes(new UnbreakableCommand())
            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Component prefix = MessagesFile.getMessage(MessagesEntry.PREFIX).appendSpace();
        Entity executor = context.getSource().getExecutor();
        CommandSender sender = context.getSource().getSender();
        if(executor instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if(item.getType() != Material.AIR) {
                ItemMeta meta = item.getItemMeta();
                if(meta != null) {
                    meta.setUnbreakable(true);
                    item.setItemMeta(meta);

                    sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.ITEM_UNBREAKABLE)));
                } else {
                    sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_ITEM)));
                }
            } else {
                sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.NO_ITEM)));
            }
        } else {
            sender.sendMessage(prefix.append(MessagesFile.getMessage(MessagesEntry.ONLY_FOR_PLAYERS)));
        }
        return SINGLE_SUCCESS;
    }
}
