package me.minignomer.elementsmp.commands;

import me.minignomer.elementsmp.energy.Energy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnergyCommand implements CommandExecutor {

    // /energy <add/set/remove> <player> <amount>

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /energy <add/set/remove> <player> <amount>");
            return true;
        }
        if (Bukkit.getPlayer(args[1]) == null || !Bukkit.getPlayer(args[1]).isValid()) {
            sender.sendMessage("§c§lPlayer: " + args[1] + " not found!");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        int amount;

        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException exception) {
            sender.sendMessage("§c§l" + args[2] + " is an invalid amount of energy!");
            return true;
        }

        if (amount < 0) {
            sender.sendMessage("§c§l" + amount + " must be a positive integer!");
            return true;
        }



        switch (args[0].toLowerCase()) {
            case "add":
                Energy.addEnergy(target, amount);
                break;
            case "set":
                Energy.setEnergy(target, amount);
                break;
            case "remove":
                Energy.removeEnergy(target, amount);
                break;
            default:
                sender.sendMessage("§c§lIncorrect command usage!\n§r§cCorrect usage: /energy <add/set/remove> <player> <amount>");
                break;
        }

        return true;
    }
}
