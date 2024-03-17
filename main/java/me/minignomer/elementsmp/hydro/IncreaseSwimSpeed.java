package me.minignomer.elementsmp.hydro;

import me.minignomer.elementsmp.element.Element;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class IncreaseSwimSpeed implements Listener {
    final double defaultMultiplyAmount = 0.1;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!p.isSwimming()) return;

        if (Element.getElement(p).equalsIgnoreCase("hydro")) {

            try {
                Location to = e.getTo();
                Location from = e.getFrom();

                Vector changed = to.clone().subtract(from).toVector().normalize();

                changed = changed.add(p.getLocation().getDirection().clone());

                Vector finalChanged = changed;

                if (p.getInventory().getBoots() != null) {

                    ItemStack boots = p.getInventory().getBoots();

                    if (!boots.getEnchantments().isEmpty() && boots.getEnchantments().containsKey(Enchantment.DEPTH_STRIDER)) {


                        if (boots.getEnchantments().get(Enchantment.DEPTH_STRIDER) == 1) {
                            p.setVelocity(finalChanged.multiply(defaultMultiplyAmount + 0.05));
                            return;
                        }

                        if (boots.getEnchantments().get(Enchantment.DEPTH_STRIDER) == 2) {
                            p.setVelocity(finalChanged.multiply(defaultMultiplyAmount + 0.1));
                            return;
                        }

                        if (boots.getEnchantments().get(Enchantment.DEPTH_STRIDER) == 3) {
                            p.setVelocity(finalChanged.multiply(defaultMultiplyAmount + 0.15));
                            return;
                        }
                        p.setVelocity(finalChanged.multiply(defaultMultiplyAmount));
                        return;
                    }

                    p.setVelocity(finalChanged.multiply(defaultMultiplyAmount));

                    return;
                }
                p.setVelocity(finalChanged.multiply(defaultMultiplyAmount));
            } catch (Exception ignored) {}
        }
    }
}
