package me.minignomer.elementsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ElementTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("set");
            options.add("randomize");
            return options;
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("set")) {
                List<String> elements = new ArrayList<>();
                elements.add("flame");
                elements.add("hydro");
                elements.add("geo");
                elements.add("wind");
                elements.add("lightning");
                return elements;
            }
        }

        return null;
    }
}
