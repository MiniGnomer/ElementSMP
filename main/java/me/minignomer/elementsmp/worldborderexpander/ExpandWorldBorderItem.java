package me.minignomer.elementsmp.worldborderexpander;

import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ExpandWorldBorderItem implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.worldBorder)) return;
        e.getItem().setAmount(0);
        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
        wb.setCenter(0, 0);
        wb.setSize(10000, 50);
    }
}
