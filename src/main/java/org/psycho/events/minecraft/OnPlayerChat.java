package org.psycho.events.minecraft;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.psycho.Bot;
import org.psycho.managers.StaffChatManager;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class OnPlayerChat implements Listener {

    private final Bot plugin;
    private StaffChatManager staffChatManager;

    public OnPlayerChat(Bot plugin, StaffChatManager staffChatManager) {
        this.plugin = plugin;
        this.staffChatManager = staffChatManager;
    }


        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent event){
            Bot bot = Bot.getInstance();
            Map<Player, Boolean> staffChatStatus = staffChatManager.getStaffChatStatus();
            Player player = event.getPlayer();
            String message = event.getMessage();
            if (staffChatManager.isStaffChatEnabled(event.getPlayer()) && event.getPlayer().hasPermission("staffchat.use")) {
                TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1168051592780587021L);
                String playername = player.getName();
                channel.sendMessage(ChatColor.stripColor(playername) + " » " + message).queue();
            } else if (!staffChatManager.isStaffChatEnabled(event.getPlayer()) && !event.getPlayer().hasPermission("staffchat.use")) {
                TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
                String playername = player.getName();
                channel.sendMessage(ChatColor.stripColor(playername) + " » " + message).queue();
            } else if (event.getPlayer().hasPermission("staffchat.use") && message.startsWith("#")) {
                message = message.substring(1);
                if (message.startsWith(" ")) {
                    message = message.substring(1);
                }
                TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1168051592780587021L);
                String playername = player.getName();
                channel.sendMessage(ChatColor.stripColor(playername) + " » " + message).queue();
            } else {
                TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
                String playername = player.getName();
                channel.sendMessage(ChatColor.stripColor(playername) + " » " + message).queue();
            }

        }
    }


