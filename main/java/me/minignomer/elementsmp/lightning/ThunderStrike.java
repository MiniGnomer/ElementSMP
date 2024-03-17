package me.minignomer.elementsmp.lightning;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.geo.Corrosion;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.particles.ParticleEffects;
import me.minignomer.elementsmp.truedamage.TrueDamage;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

public class ThunderStrike extends ParticleEffects implements Listener {

    final double radius = 20;

    private void stunPlayer(Player target) {

        Corrosion.cannotMove.add(target);

        new BukkitRunnable() {
            @Override
            public void run() {
                Corrosion.cannotMove.remove(target);
            }
        }.runTaskLater(ElementSMP.plugin, 20);
    }

    private void spawnLightning(Player user, Location loc) {

        Location newLoc = loc.clone();

        double randX = ThreadLocalRandom.current().nextDouble(-radius, radius);
        double randY = ThreadLocalRandom.current().nextDouble(-2, 2);
        double randZ = ThreadLocalRandom.current().nextDouble(-radius, radius);

        newLoc.add(randX, randY, randZ);


        LightningStrike lightning = newLoc.getWorld().strikeLightning(newLoc);
        lightning.setCausingPlayer(user);
        lightning.addScoreboardTag("thunderstrike");

        for (Entity entity : newLoc.getWorld().getNearbyEntities(newLoc, 2.5, 5, 2.5)) {
            if (!(entity instanceof Player)) continue;
            Player target = (Player) entity;
            if (target.equals(user)) continue;
            TrueDamage.trueDamage(target, 1.75, user);
            if (Corrosion.cannotMove.contains(target)) continue;
            stunPlayer(target);
        }

    }

    private void spawnThunderStrike(Player user, Location loc) {

        Particle.DustOptions options = new Particle.DustOptions(Color.YELLOW, 4);

        new BukkitRunnable() {
            int repeatCount = 0;
            @Override
            public void run() {
                if (repeatCount > 15 * 2) cancel();
                spawnCircle(radius, Particle.REDSTONE, loc, options);
                for (int i = 0; i < radius; i++) {
                    spawnLightning(user, loc);
                }
                repeatCount++;
            }
        }.runTaskTimer(ElementSMP.plugin, 20, 10);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.lightning)) return;
        Player p = e.getPlayer();
        if (!p.isSneaking()) return;
        if (p.hasCooldown(ItemManager.lightning.getType())) return;
        if (!Energy.hasEnoughEnergy(p, 750)) return;

        p.setCooldown(ItemManager.lightning.getType(), 20 * 20);
        Energy.removeEnergy(p, 750);

        spawnThunderStrike(p, p.getLocation().clone());
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        if (e.getCause() != BlockIgniteEvent.IgniteCause.LIGHTNING) return;
        if (e.getIgnitingEntity().getScoreboardTags().contains("thunderstrike")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamageByLightning(EntityDamageByEntityEvent e) {
        if (!e.getDamager().getScoreboardTags().contains("thunderstrike")) return;
        LightningStrike lightning = (LightningStrike) e.getDamager();
        if (lightning.getCausingPlayer().equals(e.getEntity())) {
            e.setCancelled(true);
        }
    }

}
