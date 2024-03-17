package me.minignomer.elementsmp.commands;

import me.minignomer.elementsmp.element.Element;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ElementCommand implements CommandExecutor {

    // /element set <player> <element>
    // /element randomize <player>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can run this command!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 2 && args.length != 3) {
            p.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /element <set> <player> <element>");
            p.sendMessage("\n§r§cOr: /element <randomize> <player>");
            return true;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            p.sendMessage("§c" + args[1] + " is not a valid player!");
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length != 3) {
                p.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /element set <player> <element>");
                return true;
            }
            switch (args[2].toLowerCase()) {
                case "flame":
                    for (ItemStack item : Element.getElementItems()) {
                        if (!target.getInventory().contains(item)) {
                            continue;
                        }
                        target.getInventory().remove(item);
                    }
                    Element.setElement(target, "FLAME");
                    target.getInventory().addItem(ItemManager.flame);
                    break;
                case "hydro":
                    for (ItemStack item : Element.getElementItems()) {
                        if (!target.getInventory().contains(item)) {
                            continue;
                        }
                        target.getInventory().remove(item);
                    }
                    Element.setElement(target, "HYDRO");
                    target.getInventory().addItem(ItemManager.hydro);
                    break;
                case "geo":
                    for (ItemStack item : Element.getElementItems()) {
                        if (!target.getInventory().contains(item)) {
                            continue;
                        }
                        target.getInventory().remove(item);
                    }
                    Element.setElement(target, "GEO");
                    target.getInventory().addItem(ItemManager.geo);
                    break;
                case "wind":
                    for (ItemStack item : Element.getElementItems()) {
                        if (!target.getInventory().contains(item)) {
                            continue;
                        }
                        target.getInventory().remove(item);
                    }
                    Element.setElement(target, "WIND");
                    target.getInventory().addItem(ItemManager.wind);
                    break;
                case "lightning":
                    for (ItemStack item : Element.getElementItems()) {
                        if (!target.getInventory().contains(item)) {
                            continue;
                        }
                        target.getInventory().remove(item);
                    }
                    Element.setElement(target, "LIGHTNING");
                    target.getInventory().addItem(ItemManager.lightning);
                    break;
                default:
                    p.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /element set <player> <element>");
            }
            return true;
        } else if (args[0].equalsIgnoreCase("randomize")) {
            if (args.length != 2) {
                p.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /element set <player> <element>");
                return true;
            }

            Element.randomizeElement(target);
            return true;
        }

        p.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /element set/randomize <player> <element (if set)>");

        return true;
    }
}
