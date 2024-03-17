package me.minignomer.elementsmp.lightning;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.truedamage.TrueDamage;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ThunderCloud implements Listener {

    private void findTargets(Player p) {
        for (Entity entity : p.getNearbyEntities(7.5, 7.5, 7.5)) {
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity target = (LivingEntity) entity;
            if (TrustCommand.isTrusted(p, target)) continue;
            spawnCloud(p, target);
        }
    }

    private void spawnCloud(Player cause, LivingEntity target) {

        new BukkitRunnable() {
            int repeatCount = 0;
            @Override
            public void run() {
                if (!target.isValid()) cancel();
                if ((repeatCount / 4) > 10) cancel();

                Location loc = target.getEyeLocation().clone();

                if (repeatCount % 6 == 0) {
                    LightningStrike lightning = loc.getWorld().strikeLightning(loc);
                    lightning.setCausingPlayer(cause);
                    TrueDamage.trueDamage(target, 1, cause);
                }

                loc = loc.add(0, 3, 0);

                target.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, loc, 10, 0.25, 0.25, 0.25, 0);
                target.getWorld().spawnParticle(Particle.FALLING_WATER, loc, 5, 0.25, 0.25, 0.25, 0);
                target.getWorld().spawnParticle(Particle.DRIP_WATER, loc, 5, 0.25, 0.25, 0.25, 0);

                repeatCount++;
            }
        }.runTaskTimer(ElementSMP.plugin, 5, 5);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.lightning)) return;
        Player p = e.getPlayer();
        if (p.isSneaking()) return;
        if (p.hasCooldown(ItemManager.lightning.getType())) return;
        if (!Energy.hasEnoughEnergy(p, 300)) return;
        if (p.getNearbyEntities(7.5, 7.5, 7.5).isEmpty()) {
            p.sendMessage("§c§lNo nearby players!");
            return;
        }

        p.setCooldown(ItemManager.lightning.getType(), 20 * 15);
        Energy.removeEnergy(p, 300);

        findTargets(p);
    }
}
