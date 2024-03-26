package org.psycho;

import java.net.http.WebSocket.Listener;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.psycho.FunStuff.*;
import org.psycho.commands.AnnouncementCMD;
import org.psycho.commands.StaffChatCMD;
import org.psycho.events.minecraft.OnPlayerChat;
import org.psycho.events.minecraft.OnStaffChatUse;
import org.psycho.managers.DiscordManager;
import org.psycho.managers.StaffChatManager;


public final class Bot extends JavaPlugin implements Listener{

    public static final String Prefix = (ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " » ");

    private DiscordManager discordManager;
    private static Bot plugin;

    private StaffChatManager staffChatManager;

    public void onEnable() {

        plugin = this;
        StaffChatManager staffChatManager = new StaffChatManager(this);
        this.discordManager = new DiscordManager(staffChatManager.getStaffChatStatus());

        getServer().getPluginManager().registerEvents(new OnPlayerChat(this, staffChatManager), this);
        getCommand("staffchat").setExecutor(new StaffChatCMD(this, staffChatManager));
        getServer().getPluginManager().registerEvents(new OnStaffChatUse(this, staffChatManager), this);
        getServer().getPluginManager().registerEvents(new UpgradeGUI(), this);
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

}