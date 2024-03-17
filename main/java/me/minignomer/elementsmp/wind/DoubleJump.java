package me.minignomer.elementsmp.wind;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.element.Element;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.particles.ParticleEffects;
import me.minignomer.elementsmp.truedamage.TrueDamage;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DoubleJump extends ParticleEffects implements Listener {

    List<Player> doubleJumping = new ArrayList<>();

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();

        if (!p.getInventory().getItemInMainHand().isSimilar(ItemManager.wind) && !p.getInventory().getItemInOffHand().isSimilar(ItemManager.wind)) return;

        if (!e.isSneaking()) return;
        if (!p.getGameMode().equals(GameMode.SURVIVAL) && !p.getGameMode().equals(GameMode.ADVENTURE)) return;
        Location loc = p.getLocation().clone().subtract(0, 0.2, 0);
        if (loc.getBlock().getType().isSolid()) return;
        if (Element.ElementType.valueOf(Element.getElement(p)) != Element.ElementType.WIND) return;
        if (p.hasCooldown(ItemManager.wind.getType())) return;

        if (!Energy.hasEnoughEnergy(p, 60)) return;

        p.setCooldown(ItemManager.wind.getType(), 20 * 4);

        Energy.removeEnergy(p, 60);

        Particle.DustOptions options = new Particle.DustOptions(Color.WHITE, 4);
        spawnCloud(1, Particle.REDSTONE, loc, options);

        for (Entity entity : p.getNearbyEntities(2, 2, 2)) {
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity le = (LivingEntity) entity;
            if (TrustCommand.isTrusted(p, le)) continue;
            TrueDamage.trueDamage(le, 2, p);
        }

        Vector dir = p.getEyeLocation().getDirection();
        p.setVelocity(dir.add(new Vector(0, 0.75, 0)));

        doubleJumping.add(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                doubleJumping.remove(p);
            }
        }.runTaskLater(ElementSMP.plugin, 50);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if (doubleJumping.contains((Player) e.getEntity())) {
            e.setDamage(0);
        }
    }
}
