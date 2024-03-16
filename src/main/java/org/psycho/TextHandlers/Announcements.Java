package org.psycho.TextHandlers;

import org.jetbrains.annotations.NotNull;
import org.psycho.Bot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Announcements implements CommandExecutor {
    private final Bot plugin;
    public Announcements(Bot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender player, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("broadcast")) {
            if (!player.hasPermission("modlink.broadcast")) {
            if (args.length > 0) {
                String message = String.join(" ", args);
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Announcement " + ChatColor.DARK_GRAY + "» " + message);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "You must provide a message to announce.");
                return false;
            }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            }
        }
        return false;
    }
}