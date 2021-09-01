package net.clustlight.spigot.storagestack.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StorageBox {

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "ストレージスタック");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "単一のアイテムを無限に収納");
        lore.add(ChatColor.LIGHT_PURPLE + "右クリックで展開");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("storage_stack"), getItem());
        recipe.shape(
                "CCC",
                "C C",
                "CCC"
        );
        recipe.setIngredient('C', Material.CHEST);

        return recipe;
    }

}
