package me.minignomer.elementsmp.commands;

import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCommand implements CommandExecutor {

    // /get <item>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can run this command!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 1) {
            p.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /get <item>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "level1multiplier":
                p.getInventory().addItem(ItemManager.energy1Multiplier);
                break;
            case "level2multiplier":
                p.getInventory().addItem(ItemManager.energy2Multiplier);
                break;
            case "level3multiplier":
                p.getInventory().addItem(ItemManager.energy3Multiplier);
                break;
            case "reroll":
                p.getInventory().addItem(ItemManager.reRoll);
                break;
            case "revive":
                p.getInventory().addItem(ItemManager.revive);
                break;
            case "life":
                p.getInventory().addItem(ItemManager.life);
                break;
            case "worldborder":
                p.getInventory().addItem(ItemManager.worldBorder);
                break;
            default:
                p.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /get <item>");
                break;
        }

        return true;
    }
}
