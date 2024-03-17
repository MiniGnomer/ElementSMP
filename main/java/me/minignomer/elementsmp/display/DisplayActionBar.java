package me.minignomer.elementsmp.display;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.element.Element;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.lives.Lives;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DisplayActionBar implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) {
                    cancel();
                    return;
                }
                if (Element.getElement(p) != null) {
                    switch (Element.ElementType.valueOf(Element.getElement(p))) {
                        case FLAME:
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                    "§4§lElement: " + "§r§cFlame" +
                                            "§r§0§l ┃ §r§5§lEnergy: §r§d" + Energy.getEnergy(p) + "/" + Energy.calculateMaxEnergy(p) +
                                            "§r§0§l ┃ §r§2§lLives: §r§a" + Lives.getLives(p)));
                            break;
                        case HYDRO:
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                    "§1§lElement: " + "§r§9Hydro" +
                                            "§r§0§l ┃ §r§5§lEnergy: §r§d" + Energy.getEnergy(p) + "/" + Energy.calculateMaxEnergy(p) +
                                            "§r§0§l ┃ §r§2§lLives: §r§a" + Lives.getLives(p)));
                            break;
                        case GEO:
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                    "§6§lElement: " + "§r§eGeo" +
                                            "§r§0§l ┃ §r§5§lEnergy: §r§d" + Energy.getEnergy(p) + "/" + Energy.calculateMaxEnergy(p) +
                                            "§r§0§l ┃ §r§2§lLives: §r§a" + Lives.getLives(p)));
                            break;
                        case WIND:
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                    "§7§lElement: " + "§r§fWind" +
                                            "§r§0§l ┃ §r§5§lEnergy: §r§d" + Energy.getEnergy(p) + "/" + Energy.calculateMaxEnergy(p) +
                                            "§r§0§l ┃ §r§2§lLives: §r§a" + Lives.getLives(p)));
                            break;
                        case LIGHTNING:
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                    "§e§lElement: " + "§r§6Lightning" +
                                            "§r§0§l ┃ §r§5§lEnergy: §r§d" + Energy.getEnergy(p) + "/" + Energy.calculateMaxEnergy(p) +
                                            "§r§0§l ┃ §r§2§lLives: §r§a" + Lives.getLives(p)));
                            break;
                    }
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 5L, 10L);
    }
}
