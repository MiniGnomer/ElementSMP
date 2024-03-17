package me.minignomer.elementsmp.effects;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.element.Element;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Effects implements Listener {

    public static void giveEffects(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) {
                    cancel();
                }

                if (Element.getElement(p) != null) {

                    Element.ElementType et = Element.ElementType.valueOf(Element.getElement(p));
                    switch (et) {
                        case FLAME:
                            // Fire res
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0, false, false, true));
                            break;
                        case HYDRO:
                            // Water breathing
                            p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0, false, false, true));
                            break;
                        case GEO:
                            // Haste
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0, false, false, true));
                            break;
                        case WIND:
                            // Speed
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, false, false, true));
                            break;
                        case LIGHTNING:
                            // Haste
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0, false, false, true));
                            // Speed
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, false, false, true));
                            break;
                    }
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 0L, 20L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        giveEffects(p);
    }
}
