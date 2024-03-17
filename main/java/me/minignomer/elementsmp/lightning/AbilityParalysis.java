package me.minignomer.elementsmp.lightning;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.commands.TrustCommand;
import me.minignomer.elementsmp.element.Element;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AbilityParalysis implements Listener {

    final int disableTime = 15;

    private void disableElement(Player target) {
        for (ItemStack item : Element.getElementItems()) {
            target.setCooldown(item.getType(), 20 * disableTime + 2);
        }

        target.sendMessage("§e§lYour element has been disabled!");
    }

    @EventHandler
    public void onRightClick(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) return;
        Player damager = (Player) e.getDamager();
        Player hurtPlayer = (Player) e.getEntity();
        ItemStack item = damager.getInventory().getItemInMainHand();
        if (!item.isSimilar(ItemManager.lightning)) return;
        if (TrustCommand.isTrusted(damager, hurtPlayer)) return;
        if (damager.hasCooldown(ItemManager.lightning.getType())) return;
        if (!Energy.hasEnoughEnergy(damager, 350)) return;

        damager.setCooldown(ItemManager.lightning.getType(), 20 * 10);
        Energy.removeEnergy(damager, 350);

        disableElement(hurtPlayer);
        damager.sendMessage("§6§lYou disabled " + hurtPlayer.getName() + "'s element!");
        new BukkitRunnable() {
            @Override
            public void run() {
                damager.sendMessage("§e§l" + hurtPlayer.getName() + "'s element is now usable!");
            }
        }.runTaskLater(ElementSMP.plugin, 20 * disableTime);
    }
}
