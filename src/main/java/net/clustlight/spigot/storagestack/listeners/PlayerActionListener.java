package net.clustlight.spigot.storagestack.listeners;

import net.clustlight.spigot.storagestack.events.StorageStackTransactionEvent;
import net.clustlight.spigot.storagestack.events.StorageStackUseEvent;
import net.clustlight.spigot.storagestack.inventories.StorageScreen;
import net.clustlight.spigot.storagestack.items.StorageBox;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class PlayerActionListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item == null || !(event.getHand() == EquipmentSlot.HAND)) {
            return;
        }

        if (!item.hasItemMeta() || !item.getType().isBlock() || !item.getItemMeta().hasEnchants()) {
            return;
        }

        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (item.equals(StorageBox.getItem())) {
                event.setCancelled(true);
                StorageScreen gui = new StorageScreen();
                player.openInventory(gui.getInventory());
            }

        } else if(action == Action.RIGHT_CLICK_AIR) {

            if(item.getItemMeta().hasEnchant(Enchantment.LUCK) && item.getItemMeta().hasCustomModelData()) {
                Bukkit.getPluginManager().callEvent(new StorageStackTransactionEvent(player, item));
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getClickedInventory() == null) {
            return;
        }
        if(event.getClickedInventory().getHolder() instanceof StorageScreen) {
            if (event.getSlot() != 4){
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 5);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "ストレージスタック")){
            if(event.getView().getTopInventory().getItem(4) == null){
                return;
            }

            ItemStack i = event.getView().getTopInventory().getItem(4);
            if (i == null || i.getType().equals(Material.AIR)){
                return;
            }

            ItemStack item = new ItemStack(i.getType());
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(i.getAmount());
            meta.setLore(Collections.singletonList(String.valueOf(i.getAmount())));
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            event.getPlayer().getInventory().addItem(item);

            player.getInventory().remove(StorageBox.getItem());
        }
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta() || !item.getType().isBlock() || !item.getItemMeta().hasEnchants()) {
            return;
        }

        if (item.getItemMeta().hasEnchant(Enchantment.LUCK)) {
            Bukkit.getPluginManager().callEvent(new StorageStackUseEvent(player, block, item));
            event.setCancelled(true);
        }
    }
}
