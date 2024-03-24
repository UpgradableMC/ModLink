package org.psycho.events.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.psycho.Bot;
import org.psycho.managers.StaffChatManager;

import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.bukkit.Bukkit.getServer;

public class OnStaffChatUse implements Listener {


    private final Bot plugin;
    private StaffChatManager staffChatManager;

    public OnStaffChatUse(Bot plugin, StaffChatManager staffChatManager) {
        this.staffChatManager = staffChatManager;
        this.plugin = plugin;
    }



    public void sendStaffChatMessage(Player sender, String message) {
        for (Player staff : getServer().getOnlinePlayers()) {
            if (staff.hasPermission("staffchat.use")) {
                staff.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "STAFF " + ChatColor.AQUA + sender.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + message);
                getLogger().info("STAFF " + sender.getName() + " » " + message);
            }
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (staffChatManager.isStaffChatEnabled(event.getPlayer()) && event.getPlayer().hasPermission("staffchat.use")) {
            event.setCancelled(true);
            sendStaffChatMessage(event.getPlayer(), message);
        }
        if (event.getPlayer().hasPermission("staffchat.use") && message.startsWith("#")) {
            event.setCancelled(true);
            message = message.substring(1);
            if (message.startsWith(" ")) {
                message = message.substring(1);
            }
            sendStaffChatMessage(event.getPlayer(), message);
        }
    }
}
