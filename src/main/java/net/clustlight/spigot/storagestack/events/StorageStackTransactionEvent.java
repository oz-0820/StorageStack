package net.clustlight.spigot.storagestack.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class StorageStackTransactionEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final ItemStack item;

    public StorageStackTransactionEvent(Player player, ItemStack item) {
        this.player = player;
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
