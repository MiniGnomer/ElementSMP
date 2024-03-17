package me.minignomer.elementsmp.flame;

import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.truedamage.TrueDamage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class ShootFireball implements Listener {

    public void shootFireball(Player p) {
        Location loc = p.getEyeLocation().clone().add(p.getLocation().getDirection());
        Fireball fb = (Fireball) loc.getWorld().spawnEntity(loc, EntityType.FIREBALL);
        fb.setIsIncendiary(true);
        fb.setYield(5.0F);
        fb.setVelocity(p.getLocation().getDirection().multiply(1.3));
        fb.addScoreboardTag("flame");
        fb.addScoreboardTag(p.getUniqueId().toString());
    }

    @EventHandler
    public void onFlameItemRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.getInventory().getItemInMainHand().isSimilar(ItemManager.flame) && !p.getInventory().getItemInOffHand().isSimilar(ItemManager.flame)) {
            return;
        }
        if (e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (p.isSneaking()) {
            return;
        }

        if (p.hasCooldown(ItemManager.flame.getType())) {
            return;
        }

        if (!Energy.hasEnoughEnergy(p, 550)) return;

        p.setCooldown(ItemManager.flame.getType(), 20 * 5);

        Energy.removeEnergy(p, 550);

        shootFireball(p);
    }

    @EventHandler
    public void onFireballExplode(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Fireball)) {
            return;
        }
        Fireball fb = (Fireball) e.getDamager();
        if (!fb.getScoreboardTags().contains("flame")) {
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity p = (LivingEntity) e.getEntity();
        for (String tag : fb.getScoreboardTags()) {
            if (tag.equals("flame")) {
                continue;
            }
            UUID uuid = UUID.fromString(tag);
            Player shooter = Bukkit.getPlayer(uuid);
            if (shooter == null) {
                continue;
            }
            if (uuid.equals(p.getUniqueId())
             || TrustCommand.isTrusted(shooter, p)) {
                e.setCancelled(true);
            } else {
                e.setCancelled(true);
                TrueDamage.trueDamage(p, 8, shooter);
            }
        }
    }

    @EventHandler
    public void onFireballDirectHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Fireball)) return;

        Fireball fb = (Fireball) e.getEntity();

        if (!fb.getScoreboardTags().contains("flame")) return;

        if (e.getHitEntity() == null || !(e.getHitEntity() instanceof LivingEntity)) return;

        LivingEntity p = (LivingEntity) e.getHitEntity();

        for (String tag : fb.getScoreboardTags()) {
            if (tag.equals("flame")) continue;

            UUID uuid = UUID.fromString(tag);
            Player shooter = Bukkit.getPlayer(uuid);
            if (shooter == null) continue;

            if (uuid.equals(p.getUniqueId())
                    || TrustCommand.isTrusted(shooter, p)) {
                e.setCancelled(true);
            } else {
                e.setCancelled(true);
                TrueDamage.trueDamage(p, 3, shooter);
            }
        }
    }
}
