package me.minignomer.elementsmp.lives;

import com.samjakob.spigui.SpiGUI;
import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import me.minignomer.elementsmp.ElementSMP;
import me.minignomer.elementsmp.element.Element;
import me.minignomer.elementsmp.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Revive implements Listener {


    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.revive)) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (p.hasCooldown(e.getItem().getType())) {
            p.sendMessage("§c§lYou can't revive someone else yet!");
            return;
        }

        if (Bukkit.getOfflinePlayers().length < 1) {
            p.sendMessage("§c§lNo one has been eliminated yet!");
        }

        createReviveGUI(p, e.getItem());
    }


    // GUI:

    public void createReviveGUI(Player p, ItemStack item) {

        SGMenu gui = ElementSMP.spiGUI.create("§1§lRevive Player", 6);

        for (OfflinePlayer op : Bukkit.getOfflinePlayers()) {
            if (!Element.getConfig().contains("Lives." + op.getUniqueId())) continue;
            if (Element.getConfig().getInt("Lives." + op.getUniqueId()) > 0) continue;

            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);


            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setOwningPlayer(op);
            skullMeta.setDisplayName("§r" + op.getName());

            List<String> lore = new ArrayList<>();
            lore.add("§aClick to unban " + op.getName() + "!");
            skullMeta.setLore(lore);

            skull.setItemMeta(skullMeta);

            SGButton button = new SGButton(skull).withListener((InventoryClickEvent e) -> {

                if (Lives.getLives(op) > 0) {
                    e.getWhoClicked().sendMessage("§c§lThat player isn't eliminated!");
                    p.closeInventory();
                    return;
                }
                if (!e.getWhoClicked().getInventory().contains(item)) {
                    e.getWhoClicked().sendMessage("§c§lSomething went wrong!");
                    p.closeInventory();
                    return;
                }

                Bukkit.broadcastMessage("§d" + e.getWhoClicked().getName() + " §r§5§lREVIVED §r§d" + op.getName());
                Lives.setLives(op, 3);
                item.setAmount(item.getAmount() - 1);
                p.closeInventory();
            });

            gui.addButton(button);

        }

        p.openInventory(gui.getInventory());
    }
}
