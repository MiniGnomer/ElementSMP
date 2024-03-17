package me.minignomer.elementsmp.geo;

import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class Cauterize implements Listener {

    public HashMap<UUID, Integer> hits = new HashMap<>();

    @EventHandler
    public void onPlayerHurt(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity p = (LivingEntity) e.getEntity();
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (hits.get(p.getUniqueId()) == null) {
            return;
        }
        if (hits.get(p.getUniqueId()) >= 7) {
            hits.remove(p.getUniqueId());
            return;
        }
        e.setCancelled(true);
        hits.put(p.getUniqueId(), hits.get(p.getUniqueId()) + 1);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.geo)) {
            return;
        }
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!p.isSneaking()) {
            return;
        }

        if (p.hasCooldown(ItemManager.geo.getType())) {
            return;
        }

        if (!Energy.hasEnoughEnergy(p, 300)) return;

        p.setCooldown(ItemManager.geo.getType(), 20 * 10);

        Energy.removeEnergy(p, 300);

        Particle.DustOptions options = new Particle.DustOptions(Color.SILVER, 2.3F);

        p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation().clone().add(0, 0.5, 0), 25, 0.3, 0.5, 0.3, options);

        hits.put(p.getUniqueId(), 0);

        p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 2.0F, 1.0F);


    }
}
