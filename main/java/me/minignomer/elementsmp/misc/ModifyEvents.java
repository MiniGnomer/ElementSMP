package me.minignomer.elementsmp.misc;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.element.Element;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ModifyEvents implements Listener {

    public static List<Material> beds = new ArrayList<>();

    // Elytra:
    @EventHandler
    public void onElytraFly(EntityToggleGlideEvent e) {
        if (e.isGliding()) {
            e.setCancelled(true);
            e.getEntity().sendMessage("§c§lYou cannot use elytras!");
        }
    }

    // End crystals
    @EventHandler
    public void onEndCrystalPlace(EntityPlaceEvent e) {
        if (e.getEntity().getType() == EntityType.ENDER_CRYSTAL) {
            e.setCancelled(true);
        }
    }

    // Respawn anchors + beds
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        if (e.getBlock().getType() == Material.RESPAWN_ANCHOR) {
            if (e.getBlock().getWorld().getEnvironment() == World.Environment.NORMAL || e.getBlock().getWorld().getEnvironment() == World.Environment.THE_END) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§c§lYou cannot use respawn anchors!");
            }
        }

        else if (beds.contains(e.getBlock().getType())) {
            if (e.getBlock().getWorld().getEnvironment() == World.Environment.NETHER || e.getBlock().getWorld().getEnvironment() == World.Environment.THE_END) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§c§lYou cannot use beds in the nether or end!");
            }
        }
    }

    // Increase pearl cooldown
    @EventHandler
    public void onEnderPearlThrow(ProjectileLaunchEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player) || !(e.getEntity() instanceof EnderPearl)) return;
        Player p = (Player) e.getEntity().getShooter();
        new BukkitRunnable() {
            @Override
            public void run() {
                p.setCooldown(Material.ENDER_PEARL, 20 * 8);
            }
        }.runTaskLater(ElementSMP.plugin, 1L);
    }

    // Stop dying sheep with elements
    @EventHandler
    public void onSheepDyeEvent(SheepDyeWoolEvent e) {
        if (Element.getElementItems().contains(e.getPlayer().getInventory().getItemInMainHand())
         || Element.getElementItems().contains(e.getPlayer().getInventory().getItemInOffHand())) {
            e.setCancelled(true);
        }
    }

    // Stop dying signs with elements
    @EventHandler
    public void onSignDyeEvent(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!(e.getClickedBlock().getState() instanceof Sign)) return;
        if (Element.getElementItems().contains(e.getPlayer().getInventory().getItemInMainHand())
                || Element.getElementItems().contains(e.getPlayer().getInventory().getItemInOffHand())) {
            e.setCancelled(true);
        }
    }

    // Stop putting elements in item frames
    @EventHandler
    public void onItemFrameClickEvent(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.ITEM_FRAME && e.getRightClicked().getType() != EntityType.GLOW_ITEM_FRAME) return;
        if (Element.getElementItems().contains(e.getPlayer().getInventory().getItemInMainHand())
                || Element.getElementItems().contains(e.getPlayer().getInventory().getItemInOffHand())) {
            e.setCancelled(true);
        }
    }
}
