package me.minignomer.elementsmp.items;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateElementItems implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().contains(ItemManager.oldHydro) || p.getInventory().contains(ItemManager.oldHydro2)) {
            p.getInventory().remove(ItemManager.oldHydro);
            p.getInventory().remove(ItemManager.oldHydro2);
            p.getInventory().addItem(ItemManager.hydro);
        }
        else if (p.getInventory().contains(ItemManager.oldGeo)) {
            p.getInventory().remove(ItemManager.oldGeo);
            p.getInventory().addItem(ItemManager.geo);
        }
        else if (p.getInventory().contains(ItemManager.oldWind)) {
            p.getInventory().remove(ItemManager.oldWind);
            p.getInventory().addItem(ItemManager.wind);
        }
    }
}
