package me.minignomer.elementsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GetTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> items = new ArrayList<>();
            items.add("level1multiplier");
            items.add("level2multiplier");
            items.add("level3multiplier");
            items.add("reroll");
            items.add("revive");
            items.add("life");
            items.add("worldborder");
            return items;
        }
        return null;
    }
}
