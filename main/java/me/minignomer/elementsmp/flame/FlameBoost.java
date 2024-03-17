package me.minignomer.elementsmp.flame;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlameBoost implements Listener {

    List<Player> flameBoosting = new ArrayList<>();

    public static void spawnParticleExplosion(Location loc, Particle particle) {
        for (int i = 0; i < 80; i++) {

            double randX = new Random().nextDouble(-0.5, 0.6);
            double randY = new Random().nextDouble(-0.5, 0.6);
            double randZ = new Random().nextDouble(-0.5, 0.6);

            loc.getWorld().spawnParticle(particle, loc, 0, randX, randY, randZ, 1);
        }
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.flame)) {
            return;
        }
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (p.isSneaking()) {
            return;
        }

        if (p.hasCooldown(ItemManager.flame.getType())) {
            return;
        }

        if (!Energy.hasEnoughEnergy(p, 60)) return;

        p.setCooldown(ItemManager.flame.getType(), 20 * 5);
        p.setCooldown(Material.ENDER_PEARL, 20 * 7);

        Energy.removeEnergy(p, 60);

        spawnParticleExplosion(e.getPlayer().getLocation(), Particle.FLAME);
        spawnParticleExplosion(e.getPlayer().getLocation(), Particle.SMOKE_LARGE);
        spawnParticleExplosion(e.getPlayer().getLocation(), Particle.LAVA);
        spawnParticleExplosion(e.getPlayer().getLocation(), Particle.CAMPFIRE_COSY_SMOKE);

        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);

        Vector vector = p.getEyeLocation().getDirection();
        vector.multiply(new Vector(1.7, 0, 1.7));
        vector.add(new Vector(0, 1.5, 0));
        p.setVelocity(vector);


        flameBoosting.add(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                flameBoosting.remove(p);
            }
        }.runTaskLater(ElementSMP.plugin, 50);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if (flameBoosting.contains((Player) e.getEntity())) {
            e.setDamage(0);
        }
    }
}
