package me.minignomer.elementsmp.hydro;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Hypothermia implements Listener {

    // LivingEntity = the frozen entity (obviously)
    // Double = damage to take
    public HashMap<LivingEntity, Double> frozenPlayers = new HashMap<>();

    @EventHandler
    public void onPlayerRightClick(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity) ||
                !(e.getDamager() instanceof Player)) {
            return;
        }
        LivingEntity target = (LivingEntity) e.getEntity();
        Player damager = (Player) e.getDamager();

        if (frozenPlayers.containsKey(target)) {
            frozenPlayers.put(target, frozenPlayers.get(target) + e.getFinalDamage());
            e.setCancelled(true);
            BlockData bd = Material.REDSTONE_BLOCK.createBlockData();
            target.getWorld().spawnParticle(Particle.BLOCK_DUST, target.getLocation().add(0, 0.6, 0), 20, 0.2, 0.6, 0.2, bd);
        } else {

            if (!damager.getInventory().getItemInMainHand().isSimilar(ItemManager.hydro) && !damager.getInventory().getItemInOffHand().isSimilar(ItemManager.hydro)) return;

            if (damager.hasCooldown(ItemManager.hydro.getType())) return;

            if (!Energy.hasEnoughEnergy(damager, 300)) return;

            if (TrustCommand.isTrusted(damager, target)) return;

            damager.setCooldown(ItemManager.hydro.getType(), 20 * 8);

            Energy.removeEnergy(damager, 300);

            BlockData bd = Material.REDSTONE_BLOCK.createBlockData();
            target.getWorld().spawnParticle(Particle.BLOCK_DUST, target.getLocation().add(0, 0.6, 0), 20, 0.2, 0.6, 0.2, bd);

            damager.sendMessage("§3§lYou froze " + target.getName() + "'s health!");

            target.sendMessage("§b§lYour health is frozen!");

            frozenPlayers.put(target, 0.0);

            e.setCancelled(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (target.isValid()) {
                        target.damage(0.1, damager);
                        target.damage(frozenPlayers.get(target));
                        frozenPlayers.remove(target);
                        target.sendMessage("§a§lYou're health is no longer frozen!");
                    }
                }
            }.runTaskLater(ElementSMP.plugin, 20L * 5L);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!frozenPlayers.containsKey(p)) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (p.isOnline()) {
                    p.damage(frozenPlayers.get(p));
                    frozenPlayers.remove(p);
                    p.sendMessage("§a§lYou're health is no longer frozen!");
                }
            }
        }.runTaskLater(ElementSMP.plugin, 20L * 5L);
    }
}
