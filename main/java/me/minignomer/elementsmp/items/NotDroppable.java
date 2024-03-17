package me.minignomer.elementsmp.items;

import me.minignomer.elementsmp.element.Element;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class NotDroppable implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        List<ItemStack> drops = e.getDrops();
        for (ItemStack item : Element.getElementItems()) {
            drops.remove(item);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (Element.getElement(p) == null) return;
        switch (Element.ElementType.valueOf(Element.getElement(p))) {
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
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory clickedInventory = e.getClickedInventory();

        // Check if player shift clicks the item
        if (e.getClick().isShiftClick()) {

            // Make sure the player is shift clicking from their own inventory
            if (clickedInventory != e.getWhoClicked().getInventory()) return;

            ItemStack clickedItem = e.getCurrentItem();

            // Make sure the item is an element
            if (clickedItem == null || !Element.getElementItems().contains(clickedItem)) return;

            e.setCancelled(true);

        }

        // Check if player is hotkeying the item
        else if (e.getClick().isKeyboardClick()) {


            // Check if the cursor is above another inventory
            if (clickedInventory != e.getWhoClicked().getInventory()) {

                // Check if they're doing the offhand item
                if (e.getClick() == ClickType.SWAP_OFFHAND) {

                    ItemStack offHandItem = e.getWhoClicked().getInventory().getItemInOffHand();

                    if (Element.getElementItems().contains(offHandItem)) {
                        e.setCancelled(true);
                    }
                    return;
                }

                // Get hotkeyed item
                if (e.getHotbarButton() < 0) return;
                ItemStack hotkeyedItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());

                if (hotkeyedItem != null && Element.getElementItems().contains(hotkeyedItem)) {
                    e.setCancelled(true);
                }
            }
        }


        else {
            if (clickedInventory == e.getWhoClicked().getInventory()) return;

            ItemStack onCursor = e.getCursor();

            if (onCursor == null || !Element.getElementItems().contains(onCursor)) return;

            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {

        ItemStack draggedItem = e.getOldCursor();

        if (Element.getElementItems().contains(draggedItem)) {
            int inventorySize = e.getInventory().getSize();

            for (int i : e.getRawSlots()) {
                if (i < inventorySize) {
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        if (Element.getElementItems().contains(e.getItemDrop().getItemStack())) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                p.getInventory().setItemInOffHand(e.getItemDrop().getItemStack());
            }
        }
    }

    @EventHandler
    public void onItemMoveInventories(InventoryMoveItemEvent e) {
        if (Element.getElementItems().contains(e.getItem())) {
            e.setCancelled(true);
        }
    }
}
