package me.minignomer.elementsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class LivesTabCompleter implements TabCompleter {

    // /lives <add/set/remove> <player> <amount>

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 1) {
            List<String> operation = new ArrayList<>();
            operation.add("add");
            operation.add("set");
            operation.add("remove");

            return operation;
        }

        return null;
    }


}
