package me.minignomer.elementsmp.geo;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Corrosion implements Listener {

    public static List<LivingEntity> cannotMove = new ArrayList<>();

    public List<Block> blocksWithNoDrops = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!cannotMove.contains(p)) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (blocksWithNoDrops.contains(e.getBlock())) {
            e.setDropItems(false);
        }
    }

    @EventHandler
    public void onPlayerHurtPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity) || !(e.getDamager() instanceof Player)) {
            return;
        }

        Player damager = (Player) e.getDamager();

        LivingEntity p = (LivingEntity) e.getEntity();

        ItemStack mainHandItem = damager.getInventory().getItemInMainHand();
        ItemStack offHandItem = damager.getInventory().getItemInOffHand();

        if (!mainHandItem.isSimilar(ItemManager.geo) && !offHandItem.isSimilar(ItemManager.geo)) {
            return;
        }

        if (damager.hasCooldown(ItemManager.geo.getType())) {
            return;
        }

        if (!Energy.hasEnoughEnergy(damager, 280)) return;

        if (damager.isSneaking()) {
            return;
        }

        if (TrustCommand.isTrusted(damager, p)) return;

        damager.setCooldown(ItemManager.geo.getType(), 20 * 10);

        Energy.removeEnergy(damager, 280);

        cannotMove.add(p);

        Location loc = p.getLocation();

        for (int i = 0; i < 5; i++) {
            double randX = ThreadLocalRandom.current().nextDouble(-0.7, 0.7);
            double randZ = ThreadLocalRandom.current().nextDouble(-0.7, 0.7);
            int randBlock = ThreadLocalRandom.current().nextInt(1, 6);
            switch (randBlock) {
                case 1:
                    Block b1 = loc.getWorld().getBlockAt(loc.clone().add(randX, 0.5, randZ));
                    if (!b1.getType().isSolid()) {
                        b1.setType(Material.MOSSY_COBBLESTONE);
                        blocksWithNoDrops.add(b1);
                    }
                    break;
                case 2:
                    Block b2 = loc.getWorld().getBlockAt(loc.clone().add(randX, 0.5, randZ));
                    if (!b2.getType().isSolid()) {
                        b2.setType(Material.COBBLESTONE);
                        blocksWithNoDrops.add(b2);
                    }
                    break;
                case 3:
                    Block b3 = loc.getWorld().getBlockAt(loc.clone().add(randX, 0.5, randZ));
                    if (!b3.getType().isSolid()) {
                        b3.setType(Material.STONE);
                        blocksWithNoDrops.add(b3);
                    }
                    break;
                case 4:
                    Block b4 = loc.getWorld().getBlockAt(loc.clone().add(randX, 0.5, randZ));
                    if (!b4.getType().isSolid()) {
                        b4.setType(Material.TUFF);
                        blocksWithNoDrops.add(b4);
                    }
                    break;
                case 5:
                    Block b5 = loc.getWorld().getBlockAt(loc.clone().add(randX, 0.5, randZ));
                    if (!b5.getType().isSolid()) {
                        b5.setType(Material.DEAD_HORN_CORAL_BLOCK);
                        blocksWithNoDrops.add(b5);
                    }
                    break;

            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                cannotMove.remove(p);
                cancel();
            }
        }.runTaskLater(ElementSMP.plugin, 20L * 5L);

    }

}
