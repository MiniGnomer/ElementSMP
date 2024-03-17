package me.minignomer.elementsmp.lives;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.element.Element;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Lives implements Listener {

    public static int getLives(Player p) {
        UUID uuid = p.getUniqueId();

        return Element.getConfig().getInt("Lives." + uuid);
    }

    public static int getLives(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();

        return Element.getConfig().getInt("Lives." + uuid);
    }

    public static void setLives(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        Element.getConfig().set("Lives." + uuid, amount);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static void setLives(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();

        Element.getConfig().set("Lives." + uuid, amount);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static void addLives(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        Element.getConfig().set("Lives." + uuid, getLives(p) + amount);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static void removeLives(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        Element.getConfig().set("Lives." + uuid, getLives(p) - amount);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!Element.getConfig().contains("Lives." + p.getUniqueId())) {
            setLives(p, 5);
        }
        if (getLives(p) <= 0) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    e.setJoinMessage(null);
                    p.kickPlayer("§c§lYou have no more lives!");
                }
            }.runTaskLater(ElementSMP.plugin, 1L);
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        removeLives(p, 1);
        if (getLives(p) < 1) {
            setLives(p, -1);
            Bukkit.broadcastMessage("§c§l" + p.getName() + " has been §r§4§lELIMINATED");
            p.getInventory().clear();
            p.kickPlayer("§c§lYou have no more lives!");
        }
        if (e.getEntity().getKiller() != null) {
            Player k = e.getEntity().getKiller();
            if (getLives(k) >= 10) {
                k.sendMessage("§c§l10 lives is the maximum!");
            } else {
                addLives(k, 1);
            }
        }
    }
}
