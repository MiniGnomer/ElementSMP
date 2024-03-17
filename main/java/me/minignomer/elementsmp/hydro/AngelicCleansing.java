package me.minignomer.elementsmp.hydro;

import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class AngelicCleansing implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.hydro)) {
            return;
        }
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!p.isSneaking()) {
            return;
        }

        if (p.hasCooldown(ItemManager.hydro.getType())) {
            return;
        }

        if (!Energy.hasEnoughEnergy(p, 250)) return;

        p.setCooldown(ItemManager.hydro.getType(), 20 * 5);

        Energy.removeEnergy(p, 250);

        List<Entity> entities = p.getNearbyEntities(10, 7 ,10);
        entities.add(p);

        List<PotionEffectType> goodEffects = new ArrayList<>();
        goodEffects.add(PotionEffectType.ABSORPTION);
        goodEffects.add(PotionEffectType.CONDUIT_POWER);
        goodEffects.add(PotionEffectType.DAMAGE_RESISTANCE);
        goodEffects.add(PotionEffectType.DOLPHINS_GRACE);
        goodEffects.add(PotionEffectType.FAST_DIGGING);
        goodEffects.add(PotionEffectType.FIRE_RESISTANCE);
        goodEffects.add(PotionEffectType.HEALTH_BOOST);
        goodEffects.add(PotionEffectType.HERO_OF_THE_VILLAGE);
        goodEffects.add(PotionEffectType.INCREASE_DAMAGE);
        goodEffects.add(PotionEffectType.INVISIBILITY);
        goodEffects.add(PotionEffectType.LUCK);
        goodEffects.add(PotionEffectType.NIGHT_VISION);
        goodEffects.add(PotionEffectType.REGENERATION);
        goodEffects.add(PotionEffectType.SATURATION);
        goodEffects.add(PotionEffectType.SPEED);
        goodEffects.add(PotionEffectType.WATER_BREATHING);

        List<PotionEffectType> badEffects = new ArrayList<>();
        badEffects.add(PotionEffectType.BAD_OMEN);
        badEffects.add(PotionEffectType.BLINDNESS);
        badEffects.add(PotionEffectType.CONFUSION);
        badEffects.add(PotionEffectType.DARKNESS);
        badEffects.add(PotionEffectType.GLOWING);
        badEffects.add(PotionEffectType.HUNGER);
        badEffects.add(PotionEffectType.JUMP);
        badEffects.add(PotionEffectType.LEVITATION);
        badEffects.add(PotionEffectType.POISON);
        badEffects.add(PotionEffectType.SLOW);
        badEffects.add(PotionEffectType.SLOW_DIGGING);
        badEffects.add(PotionEffectType.SLOW_FALLING);
        badEffects.add(PotionEffectType.UNLUCK);
        badEffects.add(PotionEffectType.WEAKNESS);
        badEffects.add(PotionEffectType.WITHER);

        Location pLoc = p.getLocation().clone();
        pLoc.add(0, 0.8, 0);

        for (Entity entity : entities) {
            if (!(entity instanceof Player)) {
                return;
            }
            Player target = (Player) entity;

            Location targetLoc = target.getLocation().clone();
            targetLoc.add(0, 0.8, 0);

            double distance = targetLoc.distance(pLoc);

            Vector dir = targetLoc.subtract(pLoc).toVector();

            if (TrustCommand.isTrusted(p, target) || target.equals(p)) {
                Particle.DustOptions options = new Particle.DustOptions(Color.GREEN, 2.0F);
                for (double i = 0.5; i < distance; i += 0.5) {
                    dir.multiply(i);
                    pLoc.add(dir);
                    pLoc.getWorld().spawnParticle(Particle.REDSTONE, pLoc, 1, 0, 0, 0, options);
                    pLoc.subtract(dir);
                    dir.normalize();
                }
                for (PotionEffectType type : badEffects) {
                    target.removePotionEffect(type);
                }
            } else {
                Particle.DustOptions options = new Particle.DustOptions(Color.RED, 2.0F);
                for (double i = 0.5; i < distance; i += 0.5) {
                    dir.multiply(i);
                    pLoc.add(dir);
                    pLoc.getWorld().spawnParticle(Particle.REDSTONE, pLoc, 1, 0, 0, 0, options);
                    pLoc.subtract(dir);
                    dir.normalize();
                }
                for (PotionEffectType type : goodEffects) {
                    target.removePotionEffect(type);
                }
            }
        }
    }
}
