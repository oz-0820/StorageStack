package net.clustlight.spigot.storagestack.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class StorageStackUseEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Block block;
    private final ItemStack item;

    public StorageStackUseEvent(Player player, Block block, ItemStack item) {
        this.player = player;
        this.block = block;
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
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
