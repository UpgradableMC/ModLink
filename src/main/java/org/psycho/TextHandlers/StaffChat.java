package org.psycho.TextHandlers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import org.psycho.Bot;

import java.util.HashSet;
import java.util.Set;

public class StaffChat implements CommandExecutor {

    @Getter
    public static Set<Player> staffChatUsers = new HashSet<>();

    public static final String Prefix = (ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable " + ChatColor.DARK_GRAY + ChatColor.BOLD + " » ");
    private final Bot plugin;

    public StaffChat(Bot plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("staffchat")) {
            if (sender instanceof Player player) {
                if (player.hasPermission("modlink.StaffChat")) {
                    if (staffChatUsers.contains(player)) {
                        // The player has staffchat on, so turn it off
                        staffChatUsers.remove(player);
                        player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable SC" + ChatColor.DARK_GRAY + ChatColor.BOLD + " » " + ChatColor.AQUA + "StaffChat has been turned OFF");
                    } else {
                        // The player has staffchat off, so turn it on
                        staffChatUsers.add(player);
                        player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable SC" + ChatColor.DARK_GRAY + ChatColor.BOLD + " » " + ChatColor.AQUA + "StaffChat has been turned ON");
                    }
                    return true;
                }
                else {
                    player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable SC" + ChatColor.DARK_GRAY + ChatColor.BOLD + " » " + ChatColor.DARK_RED + "You do not have access to StaffChat!");
                }
            } else {
                sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable SC" + ChatColor.DARK_GRAY + ChatColor.BOLD + " » " + ChatColor.DARK_RED + "This command can only be run by a player.");
                return false;
            }
        }
        return false;
    }

    public boolean hasStaffChatOn(Player player) {
        return staffChatUsers.contains(player);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();
        String message = event.getMessage();
        if (staffChatUsers.contains(sender)) {
            event.setCancelled(true);
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("modlink.StaffChat")) {
                if (staffChatUsers.contains(player)) {
                    player.sendMessage(ChatColor.DARK_AQUA + "" + player + ChatColor.DARK_GRAY + ChatColor.BOLD + " » " + ChatColor.AQUA + message);
                }
            }

        }
    }

}
