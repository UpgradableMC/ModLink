package org.psycho.TextHandlers;

import org.psycho.Bot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommaCommandDataHandler;
import org.bukkit.command.CommandSender;


public class Announcements implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("broadcast")) {
            if (args.length > 0) {
                String message = String.join(" ", args);
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable " + ChatColor.DARK_GRAY + "» " + message);
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You must provide a message to announce.");
                return false;
            }
        }
        return false;
    }
}