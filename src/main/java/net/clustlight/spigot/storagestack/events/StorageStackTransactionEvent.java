package net.clustlight.spigot.storagestack.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class StorageStackTransactionEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final ItemStack item;
    private final EquipmentSlot hand;

    public StorageStackTransactionEvent(Player player, ItemStack item, EquipmentSlot hand) {
        this.player = player;
        this.item = item;
        this.hand = hand;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public EquipmentSlot getHand() {
        return hand;
    }

    public boolean isMainHand() {
        return hand.equals(EquipmentSlot.HAND);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
