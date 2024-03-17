package me.minignomer.elementsmp.element;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.effects.Effects;
import me.minignomer.elementsmp.geo.Corrosion;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Element implements Listener {

    public static void receiveElementParticleEffect(Player p, ElementType et, Particle particle, Particle.DustOptions dustOptions) {
        Corrosion.cannotMove.add(p);

        new BukkitRunnable() {
            @Override
            public void run() {
                Corrosion.cannotMove.remove(p);
            }
        }.runTaskLater(ElementSMP.plugin, 120);

        Location loc = p.getEyeLocation().clone();

        new BukkitRunnable() {
            double radians = 0;
            double radius = 10;
            double y = 40;
            @Override
            public void run() {
                if (radians < (Math.PI) * 5) {
                    double x = Math.cos(radians) * radius;
                    double z = Math.sin(radians) * radius;
                    Location newLoc = loc.clone().add(x, y, z);

                    newLoc.getWorld().spawnParticle(particle, newLoc, 1, 0, 0, 0, 0, dustOptions, true);
                    y -= 0.4;
                    radius -= 0.1;
                    radians += Math.PI / 20;

                } else {
                    switch (et) {
                        case FLAME:
                            p.getInventory().addItem(ItemManager.flame);
                            setElement(p, "FLAME");
                            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                            p.sendTitle("§aYou got: §r§c§lFLAME", "§bElement", 5, 60, 10);
                            p.sendMessage("§aYou got: §r§c§lFLAME");
                            break;
                        case HYDRO:
                            p.getInventory().addItem(ItemManager.hydro);
                            setElement(p, "HYDRO");
                            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                            p.sendTitle("§aYou got: §r§9§lHYDRO", "§bElement", 5, 60, 10);
                            p.sendMessage("§aYou got: §r§9§lHYDRO");
                            break;
                        case GEO:
                            p.getInventory().addItem(ItemManager.geo);
                            setElement(p, "GEO");
                            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                            p.sendTitle("§aYou got: §r§6§lGEO", "§bElement", 5, 60, 10);
                            p.sendMessage("§aYou got: §r§6§lGEO");
                            break;
                        case WIND:
                            p.getInventory().addItem(ItemManager.wind);
                            setElement(p, "WIND");
                            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                            p.sendTitle("§aYou got: §r§f§lWIND", "§bElement", 5, 60, 10);
                            p.sendMessage("§aYou got: §r§f§lWIND");
                            break;
                        case LIGHTNING:
                            p.getInventory().addItem(ItemManager.lightning);
                            setElement(p, "LIGHTNING");
                            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                            p.sendTitle("§aYou got: §r§e§lLIGHTNING", "§bElement", 5, 60, 10);
                            p.sendMessage("§aYou got: §r§e§lLIGHTNING");
                            break;
                    }
                    Effects.giveEffects(p);
                    cancel();
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 10, 1);
    }

    public static void randomizeElement(Player p) {
        for (ItemStack item : Element.getElementItems()) {
            if (!p.getInventory().contains(item)) {
                continue;
            }
            p.getInventory().remove(item);
        }

        int rand = new Random().nextInt(1, 4 + 1);

        int currentElementInt = getElementInt(p);

        if (currentElementInt != 0) {
            while (rand == currentElementInt) {
                rand = new Random().nextInt(1, 4 + 1);
            }
        }


        switch (rand) {
            case 1:
                receiveElementParticleEffect(p, ElementType.FLAME, Particle.FLAME, null);
                break;
            case 2:
                receiveElementParticleEffect(p, ElementType.HYDRO, Particle.REDSTONE, new Particle.DustOptions(Color.AQUA, 3));
                break;
            case 3:
                receiveElementParticleEffect(p, ElementType.GEO, Particle.REDSTONE, new Particle.DustOptions(Color.ORANGE, 3));
                break;
            case 4:
                receiveElementParticleEffect(p, ElementType.WIND, Particle.CLOUD, null);
                break;
            case 5:
                receiveElementParticleEffect(p, ElementType.LIGHTNING, Particle.REDSTONE, new Particle.DustOptions(Color.YELLOW, 3));
                break;
        }
    }

    public enum ElementType {
        FLAME,
        HYDRO,
        GEO,
        WIND,
        LIGHTNING
    }

    public static List<ItemStack> getElementItems() {
        List<ItemStack> items = new ArrayList<>();
        items.add(ItemManager.flame);
        items.add(ItemManager.hydro);
        items.add(ItemManager.geo);
        items.add(ItemManager.wind);
        items.add(ItemManager.lightning);
        return items;
    }

    public static Configuration getConfig() {
        return ElementSMP.plugin.getConfig();
    }

    public static String getElement(Player p) {
        return getConfig().getString("Players." + p.getUniqueId());
    }

    public static int getElementInt(Player p) {
        if (!getConfig().contains("Players." + p.getUniqueId())) return 0;

        switch (ElementType.valueOf(getElement(p))) {
            case FLAME:
                return 1;
            case HYDRO:
                return 2;
            case GEO:
                return 3;
            case WIND:
                return 4;
            case LIGHTNING:
                return 5;
        }
        return 0;
    }

    public static void setElement(Player p, String str) {
        Configuration config = getConfig();
        UUID uuid = p.getUniqueId();
        config.set("Players." + uuid, str);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!getConfig().contains("Players." + p.getUniqueId())) {
            randomizeElement(p);
            ReRoll.rerollingPlayers.add(p.getUniqueId());
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (p.isOnline()) {
                        ReRoll.rerollingPlayers.remove(p.getUniqueId());
                    }
                }
            }.runTaskLater(ElementSMP.plugin, 120);
        }

        else if (ReRoll.rerollingPlayers.contains(p.getUniqueId())) {
            ElementType type = ElementType.valueOf(getElement(p));
            switch (type) {
                case FLAME:
                    p.getInventory().addItem(ItemManager.flame);
                    break;
                case HYDRO:
                    p.getInventory().addItem(ItemManager.hydro);
                    break;
                case GEO:
                    p.getInventory().addItem(ItemManager.geo);
                    break;
                case WIND:
                    p.getInventory().addItem(ItemManager.wind);
                    break;
                case LIGHTNING:
                    p.getInventory().addItem(ItemManager.lightning);
                    break;
            }
            ReRoll.rerollingPlayers.remove(p.getUniqueId());
        }
    }
}
