package me.minignomer.elementsmp.wind;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.particles.ParticleEffects;
import me.minignomer.elementsmp.truedamage.TrueDamage;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class Tornado extends ParticleEffects implements Listener {

    final private double defaultY = 6;
    final private double defaultRadius = 4;

    private void startTornado(Player p) {

        Location loc = p.getLocation().clone();

        pullNearbyPlayers(p, loc);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                switch (count) {
                    case 0:
                    case 3:
                        spawnParticles(loc, Particle.REDSTONE, new Particle.DustOptions(Color.WHITE, 4));
                        count++;
                        break;
                    case 1:
                    case 4:
                        spawnParticles(loc, Particle.REDSTONE, new Particle.DustOptions(Color.SILVER, 4));
                        count++;
                        break;
                    case 2:
                    case 5:
                        spawnParticles(loc, Particle.REDSTONE, new Particle.DustOptions(Color.GRAY, 4));
                        count++;
                        break;
                    default:
                        cancel();
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 0, 35);
    }

    private void pullNearbyPlayers(Player p, Location loc) {
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count / 4 < 22) {
                    Location newLoc = loc.clone().add(0, 3, 0);
                    List<Entity> entities = (List<Entity>) newLoc.getWorld().getNearbyEntities(newLoc, 10, 10, 10);
                    for (Entity entity : entities) {
                        if (!(entity instanceof LivingEntity) || entity instanceof Villager) continue;
                        LivingEntity le = (LivingEntity) entity;
                        if (entity.equals(p)) continue;
                        if (TrustCommand.isTrusted(p, le)) continue;
                        Location entityLoc = le.getLocation().clone();
                        Vector dir = newLoc.clone().subtract(entityLoc).toVector().normalize();
                        entity.setVelocity(dir);
                        if (entityLoc.distance(newLoc) < 1.5) {
                            TrueDamage.trueDamage(le, 0.25, p);
                        }
                    }
                    count++;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 20 * 5, 5);
    }

    private void spawnParticles(Location loc, Particle particle, Particle.DustOptions options) {

        new BukkitRunnable() {
            double radians = 0;
            double y = defaultY;
            double radius = defaultRadius;
            int count = 0;
            int fullCount = 1;

            @Override
            public void run() {
                if (fullCount <= 2) {
                    if (y > 0) {
                        double x = Math.cos(radians) * radius;
                        double z = Math.sin(radians) * radius;
                        Location newLoc = loc.clone().add(x, y, z);
                        newLoc.getWorld().spawnParticle(particle, newLoc, 1, 0, 0, 0, 0, options, true);

                        radians += Math.PI / 20;
                        count++;
                        if (count % 6 == 0) {
                            y -= 0.15;
                            radius -= 0.075;
                        }
                    } else {
                        fullCount++;
                        radians = 0;
                        y = defaultY;
                        radius = defaultRadius;
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 0, 1);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.wind)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (p.isSneaking()) return;
        if (p.hasCooldown(ItemManager.wind.getType())) return;
        if (!Energy.hasEnoughEnergy(p, 400)) return;

        p.setCooldown(ItemManager.wind.getType(), 20 * 40);
        Energy.removeEnergy(p, 400);

        startTornado(p);
    }
}
