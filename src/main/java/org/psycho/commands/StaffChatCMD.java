package org.psycho.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.psycho.Bot;

import java.util.HashMap;
import java.util.Map;

public class StaffChatCMD implements CommandExecutor {

    private final Bot plugin;

    public StaffChatCMD(Bot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("staffchat.use")) {
            player.sendMessage("You don't have permission to use staff chat.");
            return false;
        }

        boolean staffChatEnabled = plugin.toggleStaffChat(player);
        player.sendMessage("Staff chat " + (staffChatEnabled ? "enabled" : "disabled"));
        return true;
    }
    private final Map<Player, Boolean> staffChatStatus = new HashMap<>();

    public Map<Player, Boolean> getStaffChatStatus() {
        return staffChatStatus;
    }
    public boolean toggleStaffChat(Player player) {
        boolean newStatus = !isStaffChatEnabled(player);
        staffChatStatus.put(player, newStatus);
        return newStatus;
    }

    public boolean isStaffChatEnabled(Player player) {
        return staffChatStatus.getOrDefault(player, false);
    }

}