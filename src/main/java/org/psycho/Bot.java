package org.psycho;

import java.net.http.WebSocket.Listener;
import java.util.Set;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.psycho.TextHandlers.*;
import org.bukkit.entity.Player;


public final class Bot extends JavaPlugin implements Listener{

    public static final String Prefix = (ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Upgradable " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " » ");

    @Getter
    private DiscordManager discordManager;
    private static Bot plugin;

    public void onEnable() {

        plugin = this;
        this.discordManager = new DiscordManager();

        Set<Player> staffChatUsers = StaffChat.getStaffChatUsers();

        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getCommand("broadcast").setExecutor(new Announcements(this));
        getCommand("staffchat").setExecutor(new StaffChat(this));

    }
    public static Bot getInstance() {
        return plugin;
}

    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down the bot.");
    }
}