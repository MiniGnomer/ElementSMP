package me.minignomer.elementsmp.lives;

import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LifeItem implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.life)) return;
        Player p = e.getPlayer();
        if (Lives.getLives(p) >= 10) {
            p.sendMessage("§c§l10 lives is the maximum!");
            return;
        }
        e.getItem().setAmount(e.getItem().getAmount() - 1);
        Lives.addLives(p, 1);
    }
}
