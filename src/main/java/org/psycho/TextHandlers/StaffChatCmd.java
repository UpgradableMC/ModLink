package org.psycho.TextHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.psycho.Bot;

public class StaffChatCmd implements CommandExecutor {

    private final Bot plugin;

    public StaffChatCmd(Bot plugin) {
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
}