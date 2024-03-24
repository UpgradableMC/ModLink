package org.psycho.TextHandlers;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (plugin.isStaffChatEnabled(event.getPlayer()) && event.getPlayer().hasPermission("staffchat.use")) {
            event.setCancelled(true);
            plugin.sendStaffChatMessage(event.getPlayer(), message);
        }
        if (event.getPlayer().hasPermission("staffchat.use") && message.startsWith("#")) {
            event.setCancelled(true);
            message = message.substring(1);
            if (message.startsWith(" ")) {
                message = message.substring(1);
            }
            plugin.sendStaffChatMessage(event.getPlayer(), message);
        }
    }
}
