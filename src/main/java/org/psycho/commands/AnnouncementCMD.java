package org.psycho.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AnnouncementCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission("modlink.broadcast")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return false;
        }

        if (!(args.length > 0)) {
            player.sendMessage(ChatColor.RED + "You must provide a message to announce.");
            return false;
        }

        String message = String.join(" ", args);
        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Announcement " + ChatColor.DARK_GRAY + "» " + message);
        return true;

    }
}
