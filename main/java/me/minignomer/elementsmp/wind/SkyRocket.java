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
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SkyRocket extends ParticleEffects implements Listener {

    @EventHandler
    public void onPlayerHurt(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof LivingEntity)) return;

        Player p = (Player) e.getDamager();
        LivingEntity hurtPlayer = (LivingEntity) e.getEntity();

        if (!p.getInventory().getItemInMainHand().isSimilar(ItemManager.wind) && !p.getInventory().getItemInOffHand().isSimilar(ItemManager.wind)) return;
        if (p.isSneaking()) return;
        if (p.hasCooldown(ItemManager.wind.getType())) return;

        if (!Energy.hasEnoughEnergy(p, 300)) return;

        if (TrustCommand.isTrusted(p, hurtPlayer)) return;

        p.setCooldown(ItemManager.wind.getType(), 20 * 8);
        Energy.removeEnergy(p, 300);

        hurtPlayer.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 30, true, false, false));

        new BukkitRunnable() {
            boolean alreadyShotDown = false;
            @Override
            public void run() {
                Location loc = hurtPlayer.getLocation().clone();
                Block block = loc.clone().subtract(0, 0.75, 0).getBlock();

                if (!alreadyShotDown) {
                    hurtPlayer.setVelocity(hurtPlayer.getVelocity().add(new Vector(0, -3, 0)));
                    alreadyShotDown = true;
                } else if (!block.getType().isAir()) {
                    TrueDamage.trueDamage(hurtPlayer, 2, p);

                    new BukkitRunnable() {
                        double radius = 0.5;
                        @Override
                        public void run() {
                            if (radius > 6) {
                                cancel();
                            }
                            spawnSphere(loc, radius, Particle.REDSTONE, new Particle.DustOptions(Color.WHITE, 4));
                            radius+= 0.75;
                        }
                    }.runTaskTimer(ElementSMP.plugin, 0, 1);

                    cancel();
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 35, 5);
    }

}
