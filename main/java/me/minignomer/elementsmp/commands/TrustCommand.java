package me.minignomer.elementsmp.commands;

import me.minignomer.elementsmp.ElementSMP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrustCommand implements CommandExecutor {

    public static void addTrusted(Player p, LivingEntity trusted) {
        Configuration config = ElementSMP.plugin.getConfig();
        if (!config.contains("Trusted." + p.getUniqueId())) {
            List<String> trustedPlayers = new ArrayList<>();
            trustedPlayers.add(trusted.getUniqueId().toString());
            config.set("Trusted." + p.getUniqueId(), trustedPlayers);
            ElementSMP.plugin.saveConfig();
            ElementSMP.plugin.reloadConfig();
            return;
        }
        List<String> trustedPlayers = config.getStringList("Trusted." + p.getUniqueId());
        trustedPlayers.add(trusted.getUniqueId().toString());
        config.set("Trusted." + p.getUniqueId(), trustedPlayers);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static void removeTrusted(Player p, LivingEntity trusted) {
        Configuration config = ElementSMP.plugin.getConfig();
        if (!config.contains("Trusted." + p.getUniqueId())) {
            List<String> trustedPlayers = new ArrayList<>();
            config.set("Trusted." + p.getUniqueId(), trustedPlayers);
            ElementSMP.plugin.saveConfig();
            ElementSMP.plugin.reloadConfig();
            return;
        }
        List<String> trustedPlayers = config.getStringList("Trusted." + p.getUniqueId());
        trustedPlayers.remove(trusted.getUniqueId().toString());
        config.set("Trusted." + p.getUniqueId(), trustedPlayers);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static boolean isTrusted(Player p, LivingEntity trusted) {
        Configuration config = ElementSMP.plugin.getConfig();
        if (!config.contains("Trusted." + p.getUniqueId())) {
            return false;
        }
        List<String> trustedPlayers = config.getStringList("Trusted." + p.getUniqueId());
        return trustedPlayers.contains(trusted.getUniqueId().toString());
    }

    public static List<String> getTrusted(Player p) {
        return ElementSMP.plugin.getConfig().getStringList("Trusted." + p.getUniqueId());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 1) {
            p.sendMessage("§c§lIncorrect usage!\n§r§cCorrect usage: /trust <player>");
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            List<String> playerNames = new ArrayList<>();
            for (String trustedUUID : getTrusted(p)) {
                playerNames.add(Bukkit.getPlayer(UUID.fromString(trustedUUID)).getName());
            }
            p.sendMessage("§2Current trusted players: " + playerNames);
            return true;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            p.sendMessage("§c" + args[0] + " is doesn't exist or is not online!");
            return true;
        }
        Player trusted = Bukkit.getPlayer(args[0]);
        if (trusted.equals(p)) {
            p.sendMessage("§cYou can't trust yourself!");
            return true;
        }

        if (isTrusted(p, trusted)) {
            removeTrusted(p, trusted);
            p.sendMessage("§c" + args[0] + " has been untrusted!");
            return true;
        }

        addTrusted(p, trusted);
        p.sendMessage("§a" + args[0] + " has been trusted!");

        return true;
    }
}
