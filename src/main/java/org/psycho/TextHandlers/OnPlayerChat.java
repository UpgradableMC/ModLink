package org.psycho.TextHandlers;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.psycho.Bot;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class OnPlayerChat implements Listener {

    private final Bot plugin = Bot.getInstance();

    public Player getPlayerByUuid(UUID uuid) {
        for(Player p : getServer().getOnlinePlayers())
            if(p.getUniqueId().equals(uuid)){
                return p;
            } throw new IllegalArgumentException();
    }

    private final Map<Player, Boolean> staffChatStatus = new HashMap<>();
    public boolean isStaffChatEnabled(Player player) {
        return staffChatStatus.getOrDefault(player, false);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (plugin.isStaffChatEnabled(event.getPlayer()) && event.getPlayer().hasPermission("staffchat.use")) {
            TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1168051592780587021L);
            String playername = player.getName();
            channel.sendMessage(ChatColor.stripColor(playername) + " » " + message).queue();
        }
        if (!plugin.isStaffChatEnabled(event.getPlayer()) && event.getPlayer().hasPermission("staffchat.use")) {
            TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
            String playername = player.getName();
            channel.sendMessage(ChatColor.stripColor(playername) + " » " + message).queue();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
        String playername = player.getName();
        channel.sendMessage(ChatColor.stripColor(playername) + " has joined the server.").queue();
    }

    @EventHandler
    public void onPlayerLeave(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
        String playername = player.getName();
        channel.sendMessage(ChatColor.stripColor(playername) + " has left the server.").queue();
    }
}

