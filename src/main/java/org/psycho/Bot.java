package org.psycho;

import java.net.http.WebSocket.Listener;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.psycho.FunStuff.*;
import org.psycho.commands.AnnouncementCMD;
import org.psycho.commands.StaffChatCMD;
import org.psycho.events.minecraft.OnPlayerChat;
import org.psycho.events.minecraft.OnStaffChatUse;
import org.psycho.managers.BossbarManager;
import org.psycho.managers.DiscordManager;


public final class Bot extends JavaPlugin implements Listener{

    public static final String Prefix = (ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " » ");

    private DiscordManager discordManager;
    private static Bot plugin;

    private final Map<Player, Boolean> staffChatStatus = new HashMap<>();

    public Map<Player, Boolean> getStaffChatStatus() {
        return staffChatStatus;
    }

    public void onEnable() {

        plugin = this;
        this.discordManager = new DiscordManager(staffChatStatus);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getCommand("staffchat").setExecutor(new StaffChatCMD(this));
        getServer().getPluginManager().registerEvents(new OnStaffChatUse(this), this);
        getServer().getPluginManager().registerEvents(new UpgradeGUI(), this);
        BossbarManager bossBarBar = new BossbarManager();
        bossBarBar.enableBossBar();
        getCommand("upgrade").setExecutor(new UpgradeGUI());
        getCommand("broadcast").setExecutor(new AnnouncementCMD());

    }
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down the bot.");
        if (discordManager != null && discordManager.getJDA() != null) {
            discordManager.getJDA().shutdown();
        }
    }
    public static Bot getInstance() {
        return plugin;
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
            if (staff.hasPermission("staffchat.use")) {
                staff.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "STAFF " + ChatColor.AQUA + sender.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + message);
                getLogger().info("STAFF " + sender.getName() + " » " + message);
            }
        }
    }

}