package org.psycho;

import java.net.http.WebSocket.Listener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.psycho.FunStuff.*;
import org.psycho.TextHandlers.*;


public final class Bot extends JavaPlugin implements Listener{

    public static final String Prefix = (ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " » ");

    private DiscordManager discordManager;
    private static Bot plugin;

    private final Map<Player, Boolean> staffChatStatus = new HashMap<>();

    public void onEnable() {

        plugin = this;
        this.discordManager = new DiscordManager(staffChatStatus);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);

        getCommand("staffchat").setExecutor(new StaffChatCmd(this));
        getServer().getPluginManager().registerEvents(new StaffChat(this), this);
        getServer().getPluginManager().registerEvents(new UpgradeGUI(), this);
        getCommand("upgrade").setExecutor(new UpgradeGUI());
        getCommand("broadcast").setExecutor(new Annoucments());

    }
    public static Bot getInstance() {
        return plugin;
}

    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down the bot.");
    }

    public DiscordManager getDiscordManager() {
        return discordManager;
    }


    public boolean toggleStaffChat(Player player) {
        boolean newStatus = !isStaffChatEnabled(player);
        staffChatStatus.put(player, newStatus);
        return newStatus;
    }

    public boolean isStaffChatEnabled(Player player) {
        return staffChatStatus.getOrDefault(player, false);
    }

    public void sendStaffChatMessage(Player sender, String message) {
        for (Player staff : getServer().getOnlinePlayers()) {
            if (isStaffChatEnabled(staff)) {
                staff.sendMessage("[Staff] " + sender.getName() + " » " + message);
            }
        }
        TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1168051592780587021L);
        String playerName = sender.getName();
        channel.sendMessage(ChatColor.stripColor(playerName) + " » " + message).queue();
    }


}