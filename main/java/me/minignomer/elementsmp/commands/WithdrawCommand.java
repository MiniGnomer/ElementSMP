package me.minignomer.elementsmp.commands;

import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.lives.Lives;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WithdrawCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can run this command!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length < 1) {
            p.sendMessage("§cNot enough arguments!");
            return true;
        }
        if (p.getInventory().firstEmpty() < 0) {
            p.sendMessage("§cYou don't have any empty slots!");
            return true;
        }
        int removeAmount;
        try {
            removeAmount = Integer.parseInt(args[0]);

        } catch (Exception exception) {
            p.sendMessage("§cCannot withdraw " + args[0] + " lives!");
            return true;
        }

        if (removeAmount >= Lives.getLives(p)) {
            p.sendMessage("§cNot enough lives to run this command!");
            return true;
        }
        if (removeAmount <= 0) {
            p.sendMessage("§cYou must send a positive integer!");
            return true;
        }

        ItemStack item = ItemManager.life.clone();
        item.setAmount(removeAmount);
        p.getInventory().addItem(item);
        Lives.removeLives(p, removeAmount);
        return true;
    }
}
