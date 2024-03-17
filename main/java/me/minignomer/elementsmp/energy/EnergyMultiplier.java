package me.minignomer.elementsmp.energy;

import me.minignomer.elementsmp.ElementSMP;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class EnergyMultiplier implements Listener {

    final public double removeAmount = 0.003333333;

    public HashMap<Player, NamespacedKey> playerBossBar = new HashMap<>();

    public HashMap<Player, Long> energy1Multiplier = new HashMap<>();
    public HashMap<Player, Long> energy2Multiplier = new HashMap<>();
    public HashMap<Player, Long> energy3Multiplier = new HashMap<>();

    @EventHandler
    public void onPlayerRightClickItem(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getItem() == null || !e.getItem().hasItemMeta() || !e.getItem().getItemMeta().hasCustomModelData()) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        switch (e.getItem().getItemMeta().getCustomModelData()) {
            case 5:
                if (playerBossBar.containsKey(p)) {
                    p.sendMessage("§c§lYou already have an energy multiplier enabled!");
                    break;
                }

                energy1Multiplier.put(p, System.currentTimeMillis());

                NamespacedKey key1 = new NamespacedKey(ElementSMP.plugin, p.getUniqueId().toString());
                BossBar bb1 = Bukkit.createBossBar(key1, "§5§lLevel 1 Energy Multiplier", BarColor.PURPLE, BarStyle.SEGMENTED_20);
                bb1.setProgress(1);
                bb1.setVisible(true);
                bb1.addPlayer(p);

                playerBossBar.put(p, key1);

                e.getItem().setAmount(e.getItem().getAmount() - 1);
                break;
            case 6:
                if (playerBossBar.containsKey(p)) {
                    p.sendMessage("§c§lYou already have an energy multiplier enabled!");
                    break;
                }

                energy2Multiplier.put(p, System.currentTimeMillis());

                NamespacedKey key2 = new NamespacedKey(ElementSMP.plugin, p.getUniqueId().toString());
                BossBar bb2 = Bukkit.createBossBar(key2, "§5§lLevel 2 Energy Multiplier", BarColor.PURPLE, BarStyle.SEGMENTED_20);
                bb2.setProgress(1);
                bb2.setVisible(true);
                bb2.addPlayer(p);

                playerBossBar.put(p, key2);

                e.getItem().setAmount(e.getItem().getAmount() - 1);
                break;
            case 7:
                if (playerBossBar.containsKey(p)) {
                    p.sendMessage("§c§lYou already have an energy multiplier enabled!");
                    break;
                }

                energy3Multiplier.put(p, System.currentTimeMillis());

                NamespacedKey key3 = new NamespacedKey(ElementSMP.plugin, p.getUniqueId().toString());
                BossBar bb3 = Bukkit.createBossBar(key3, "§5§lLevel 3 Energy Multiplier", BarColor.PURPLE, BarStyle.SEGMENTED_20);
                bb3.setProgress(1);
                bb3.setVisible(true);
                bb3.addPlayer(p);

                playerBossBar.put(p, key3);

                e.getItem().setAmount(e.getItem().getAmount() - 1);
                break;
        }
    }
}
