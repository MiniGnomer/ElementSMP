package me.minignomer.elementsmp.geo;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.truedamage.TrueDamage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class EarthTurret implements Listener {

    public double calculateDamage(Material material) {
        double hardness = material.getHardness() * 1.5;

        if (hardness >= Material.COAL_BLOCK.getHardness()) {
            return 7;
        }
        return hardness;
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.geo)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (p.isSneaking()) return;
        if (!p.getInventory().getItemInOffHand().getType().isBlock() || p.getInventory().getItemInOffHand().getType().isAir()) {
            p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
            p.sendMessage("§c§lYou need a block in your offhand to use this ability!");
            return;
        }

        if (p.hasCooldown(ItemManager.geo.getType())) {
            return;
        }

        if (!Energy.hasEnoughEnergy(p, 380)) return;

        p.setCooldown(ItemManager.geo.getType(), 20 * 7);

        Energy.removeEnergy(p, 380);

        ItemStack item = p.getInventory().getItemInOffHand();
        Material material = item.getType();
        Location loc = p.getEyeLocation();

        FallingBlock fb = p.getWorld().spawnFallingBlock(loc, material.createBlockData());

        fb.setVelocity(loc.getDirection().multiply(2));
        fb.setPersistent(true);
        fb.setHurtEntities(true);
        fb.addScoreboardTag(p.getUniqueId().toString());
        fb.addScoreboardTag("turret");

        item.setAmount(item.getAmount() - 1);
        double damage = calculateDamage(material);


        new BukkitRunnable() {
            @Override
            public void run() {
                if (fb.isValid()) {
                    Location loc = fb.getLocation();
                    List<Entity> entities = (List<Entity>) fb.getWorld().getNearbyEntities(loc, 0.75, 0.75, 0.75);
                    for (Entity entity : entities) {
                        if (!(entity instanceof LivingEntity)) {
                            continue;
                        }
                        LivingEntity damaged = (LivingEntity) entity;
                        if (damaged.equals(p) || TrustCommand.isTrusted(p, damaged)) {
                            continue;
                        }
                        TrueDamage.trueDamage(damaged, damage, p);

                        fb.remove();
                        break;
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 0L, 1L);
    }
}
