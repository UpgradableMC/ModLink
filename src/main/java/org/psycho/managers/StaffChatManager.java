package org.psycho.managers;

import org.bukkit.entity.Player;
import org.psycho.Bot;

import java.util.HashMap;
import java.util.Map;

public class StaffChatManager {

    private final Bot plugin;

    public StaffChatManager(Bot plugin) {
        this.plugin = plugin;
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
