package net.clustlight.spigot.storagestack;

import net.clustlight.spigot.storagestack.items.StorageBox;
import net.clustlight.spigot.storagestack.listeners.PlayerActionListener;
import net.clustlight.spigot.storagestack.listeners.StackListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class StorageStack extends JavaPlugin {

    private static StorageStack plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().addRecipe(StorageBox.getRecipe());
        getServer().getPluginManager().registerEvents(new PlayerActionListener(), this);
        getServer().getPluginManager().registerEvents(new StackListener(), this);
    }

    public static StorageStack getPlugin() {
        return plugin;
    }
}
