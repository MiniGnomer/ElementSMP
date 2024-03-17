package me.minignomer.elementsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TrustTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("list");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.equals(sender)) {
                    continue;
                }
                options.add(p.getName());
            }
            return options;
        }

        return null;
    }
}
