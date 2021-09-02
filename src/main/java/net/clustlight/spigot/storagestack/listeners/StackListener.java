package net.clustlight.spigot.storagestack.listeners;

import net.clustlight.spigot.storagestack.events.StorageStackTransactionEvent;
import net.clustlight.spigot.storagestack.events.StorageStackUseEvent;
import net.clustlight.spigot.storagestack.items.StorageBox;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Collections;


public class StackListener implements Listener {


    @EventHandler
    public void onStorageUse(StorageStackUseEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Block block = event.getBlock();
        Material material = block.getType();
        ItemStack item = event.getItem();

        ItemMeta meta = item.getItemMeta();
        int count = meta.getCustomModelData();
        Location location = block.getLocation();

        if (count > 1) {
            int next = count - 1;
            meta.setCustomModelData(next);
            meta.setLore(Collections.singletonList(String.valueOf(next)));
            item.setItemMeta(meta);
        } else {
            player.getInventory().remove(item);
            player.getInventory().addItem(StorageBox.getItem());
        }
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(net.clustlight.spigot.storagestack.StorageStack.getPlugin(), () -> world.getBlockAt(location).setType(material), 0);
    }

    @EventHandler
    public void onStorageTransaction(StorageStackTransactionEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack i = event.getItem();
        ItemStack[] items = inventory.getContents();
        int has = 0;
        for (ItemStack item : items)
        {
            if ((item != null) && (event.getItem().getType() == item.getType()) && (item.getAmount() > 0))
            {
                has += item.getAmount();
                inventory.remove(item);
            }
        }
        ItemMeta meta = i.getItemMeta();
        int count = meta.getCustomModelData();
        int next = count + has - 1;
        meta.setCustomModelData(next);
        meta.setLore(Collections.singletonList(String.valueOf(next)));
        i.setItemMeta(meta);
        if (event.isMainHand()) {
            player.getInventory().setItemInMainHand(i);
        } else {
            player.getInventory().setItemInOffHand(i);
        }
    }

}
