package org.psycho;

import java.net.http.WebSocket.Listener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.psycho.FunStuff.*;
import org.psycho.TextHandlers.*;


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
        getCommand("staffchat").setExecutor(new StaffChatCmd(this));
        getServer().getPluginManager().registerEvents(new StaffChat(this), this);
        getServer().getPluginManager().registerEvents(new UpgradeGUI(), this);
        BossBarBar bossBarBar = new BossBarBar();
        bossBarBar.enableBossBar();
        getCommand("upgrade").setExecutor(new UpgradeGUI());
        getCommand("broadcast").setExecutor(new Annoucments());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new PAPIHooker(this), this);
        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            getLogger().severe("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
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
    private final JoinCounter joinCounter = new JoinCounter();

    public JoinCounter getJoinCounter() {
        return joinCounter;
    }

}