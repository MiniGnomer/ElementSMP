package me.minignomer.elementsmp.flame;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.particles.ParticleEffects;
import me.minignomer.elementsmp.truedamage.TrueDamage;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RingOfFire extends ParticleEffects implements Listener {

    private void spawnRingOfFire(Player p) {

        new BukkitRunnable() {

            int count = 0;
            double radius = 0.5;

            @Override
            public void run() {
                if (p.isValid() && count < 67) {
                    if (count % 2 == 0) {
                        List<Entity> entities = p.getNearbyEntities(radius, radius / 2, radius);
                        for (Entity entity : entities) {
                            if (!(entity instanceof LivingEntity)) continue;
                            if (TrustCommand.isTrusted(p, (LivingEntity) entity)) continue;
                            TrueDamage.trueDamage(((LivingEntity) entity), 0.4, p);
                        }
                    }
                    spawnCircle(radius, Particle.FLAME, p.getLocation().clone().add(0, 0.5, 0), null);
                    count++;
                    radius = (radius < 4) ? radius + 0.25 : 0.5;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 0, 3);
    }

    @EventHandler
    public void onFlameItemRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.flame)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!p.isSneaking()) return;

        if (p.hasCooldown(ItemManager.flame.getType())) return;

        if (!Energy.hasEnoughEnergy(p, 300)) return;

        p.setCooldown(ItemManager.flame.getType(), 20 * 15);

        Energy.removeEnergy(p, 300);

        spawnRingOfFire(p);
    }

}
