package net.clustlight.spigot.storagestack.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class StorageScreen implements InventoryHolder {

    private final Inventory inv;

    public StorageScreen() {
        inv = Bukkit.createInventory(this, 9, ChatColor.GOLD + "ストレージスタック");
        init();
    }

    private void init() {
        ItemStack item;
        for(int i = 0; i < 4; i++) {
            item = createItem(">>>>>", Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            inv.setItem(i, item);
        }

        for(int i = 5; i < 9; i++) {
            item = createItem("<<<<<", Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            inv.setItem(i, item);
        }
    }

    private ItemStack createItem(String name, Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
