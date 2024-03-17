package me.minignomer.elementsmp.hydro;

import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class Riptide implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.hydro)) {
            return;
        }
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (p.isSneaking()) {
            return;
        }
        if (!p.isInWater()) {
            p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
            p.sendMessage("§c§lYou can't use ability outside of water!");
            return;
        }
        if (p.hasCooldown(ItemManager.hydro.getType())) {
            return;
        }

        if (!Energy.hasEnoughEnergy(p, 80)) return;

        p.setCooldown(ItemManager.hydro.getType(), 20 * 6);
        p.setCooldown(Material.ENDER_PEARL, 20 * 7);

        Energy.removeEnergy(p, 80);

        p.getWorld().playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0F, 1.0F);

        ServerPlayer newP = ((CraftPlayer) p).getHandle();

        newP.startAutoSpinAttack(20);

        Vector vector = p.getEyeLocation().getDirection();
        vector.multiply(3);
        p.setVelocity(vector);

    }
}
