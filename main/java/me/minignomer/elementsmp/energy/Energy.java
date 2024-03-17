package me.minignomer.elementsmp.energy;

import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.element.Element;
import me.minignomer.elementsmp.lives.Lives;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Energy extends EnergyMultiplier implements Listener {

    public static int getEnergy(Player p) {
        UUID uuid = p.getUniqueId();

        return Element.getConfig().getInt("Energy." + uuid);
    }

    public static void setEnergy(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        Element.getConfig().set("Energy." + uuid, amount);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static void addEnergy(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        Element.getConfig().set("Energy." + uuid, getEnergy(p) + amount);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static void removeEnergy(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        Element.getConfig().set("Energy." + uuid, getEnergy(p) - amount);
        ElementSMP.plugin.saveConfig();
        ElementSMP.plugin.reloadConfig();
    }

    public static boolean hasEnoughEnergy(Player p, int amount) {
        if ((getEnergy(p) - amount) < 0) {
            p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
            p.sendMessage("§c§lNot enough energy!");
            return false;
        }

        return true;
    }
    
    public static int calculateMaxEnergy(Player p) {
        return Lives.getLives(p) * 100;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!Element.getConfig().contains("Energy." + p.getUniqueId())) {
            setEnergy(p, 1);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) {

                    energy3Multiplier.remove(p);

                    if (playerBossBar.containsKey(p)) {
                        Bukkit.getBossBar(playerBossBar.get(p)).setVisible(false);
                        Bukkit.removeBossBar(playerBossBar.get(p));
                        playerBossBar.remove(p);
                    }

                    cancel();
                    return;
                }

                int energyAmount = getEnergy(p);
                int maxEnergy = calculateMaxEnergy(p);

                if (energy3Multiplier.containsKey(p)) {

                    long secondsSinceUsed = (System.currentTimeMillis() - energy3Multiplier.get(p)) / 1000;

                    if (secondsSinceUsed >= 300) {
                        energy3Multiplier.remove(p);

                        Bukkit.getBossBar(playerBossBar.get(p)).setVisible(false);
                        Bukkit.removeBossBar(playerBossBar.get(p));

                        playerBossBar.remove(p);

                        if (energyAmount < maxEnergy) {
                            addEnergy(p, 1);
                        }
                    } else {

                        BossBar bb = Bukkit.getBossBar(playerBossBar.get(p));
                        bb.setProgress(bb.getProgress() - removeAmount);

                        if (energyAmount < maxEnergy) {
                            addEnergy(p, 4);
                            p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation().clone().add(0, 1, 0), 10, 0.25, 0.5, 0.25, 0);
                        }
                    }
                } else if (energy2Multiplier.containsKey(p)) {

                    long secondsSinceUsed = (System.currentTimeMillis() - energy2Multiplier.get(p)) / 1000;

                    if (secondsSinceUsed >= 300) {
                        energy2Multiplier.remove(p);

                        Bukkit.getBossBar(playerBossBar.get(p)).setVisible(false);
                        Bukkit.removeBossBar(playerBossBar.get(p));

                        playerBossBar.remove(p);

                        if (energyAmount < maxEnergy) {
                            addEnergy(p, 1);
                        }
                    } else {

                        BossBar bb = Bukkit.getBossBar(playerBossBar.get(p));
                        bb.setProgress(bb.getProgress() - removeAmount);

                        if (energyAmount < maxEnergy) {
                            addEnergy(p, 3);
                            p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation().clone().add(0, 1, 0), 10, 0.25, 0.5, 0.25, 0);
                        }
                    }
                } else if (energy1Multiplier.containsKey(p)) {

                    long secondsSinceUsed = (System.currentTimeMillis() - energy1Multiplier.get(p)) / 1000;

                    if (secondsSinceUsed >= 300) {
                        energy1Multiplier.remove(p);

                        Bukkit.getBossBar(playerBossBar.get(p)).setVisible(false);
                        Bukkit.removeBossBar(playerBossBar.get(p));

                        playerBossBar.remove(p);

                        if (energyAmount < maxEnergy) {
                            addEnergy(p, 1);
                        }
                    } else {

                        BossBar bb = Bukkit.getBossBar(playerBossBar.get(p));
                        bb.setProgress(bb.getProgress() - removeAmount);

                        if (energyAmount < maxEnergy) {
                            addEnergy(p, 2);
                            p.getWorld().spawnParticle(Particle.EGG_CRACK, p.getLocation().clone().add(0, 1, 0), 10, 0.25, 0.5, 0.25, 0);
                        }
                    }
                } else if (energyAmount < maxEnergy) {
                    addEnergy(p, 1);
                }

                if (energyAmount > maxEnergy) {
                    setEnergy(p, maxEnergy);
                }
            }
        }.runTaskTimer(ElementSMP.plugin, 5L, 20L);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        setEnergy(p, 0);
    }
}
