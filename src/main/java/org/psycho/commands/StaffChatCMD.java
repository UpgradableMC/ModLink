package org.psycho.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.psycho.Bot;
import org.psycho.managers.StaffChatManager;

import java.util.HashMap;
import java.util.Map;

public class StaffChatCMD implements CommandExecutor {

    private final Bot plugin;
    private StaffChatManager staffChatManager;

    public StaffChatCMD(Bot plugin, StaffChatManager staffChatManager) {
        this.staffChatManager =staffChatManager;
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

        boolean staffChatEnabled = staffChatManager.toggleStaffChat(player);
        player.sendMessage("Staff chat " + (staffChatEnabled ? "enabled" : "disabled"));
        return true;
    }


}