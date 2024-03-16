package org.psycho.TextHandlers;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.psycho.Bot;

public class StaffChat implements Listener {


    private final Bot plugin;

    public StaffChat(Bot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (plugin.isStaffChatEnabled(event.getPlayer()) && event.getPlayer().hasPermission("staffchat.use")) {
            event.setCancelled(true);
            String message = event.getMessage();
            plugin.sendStaffChatMessage(event.getPlayer(), message);
        }
    }

}
