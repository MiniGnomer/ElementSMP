package me.minignomer.elementsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class EventTabCompleter implements TabCompleter {

    // /event <pinata> <hand/reroll/revive/life/level1multiplier/level2multiplier/level3multiplier> <amount>

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("pinata");
            return options;
        }

        else if (args.length == 2 && args[0].equalsIgnoreCase("pinata")) {
            List<String> options = new ArrayList<>();
            options.add("hand");
            return options;
        }

        return null;
    }
}
