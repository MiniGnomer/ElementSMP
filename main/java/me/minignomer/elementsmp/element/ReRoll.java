package me.minignomer.elementsmp.element;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReRoll implements Listener {

    public static List<UUID> rerollingPlayers = new ArrayList<>();

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getItem() == null ||!e.getItem().isSimilar(ItemManager.reRoll)) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (p.hasCooldown(e.getItem().getType())) {
            p.sendMessage("§c§lYou can't reroll yet!");
            return;
        }
        p.setCooldown(ItemManager.reRoll.getType(), 120);

        e.getItem().setAmount(e.getItem().getAmount() - 1);
        Element.randomizeElement(p);

        rerollingPlayers.add(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (p.isOnline()) {
                    rerollingPlayers.remove(p.getUniqueId());
                }
            }
        }.runTaskLater(ElementSMP.plugin, 120);
    }
}
