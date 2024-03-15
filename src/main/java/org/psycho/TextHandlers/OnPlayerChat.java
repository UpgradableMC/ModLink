package org.psycho.TextHandlers;

import lombok.Getter;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.psycho.Bot;
import org.psycho.TextHandlers.StaffChat;

import java.net.http.WebSocket;
import java.util.Set;

import static org.psycho.TextHandlers.StaffChat.staffChatUsers;

public class OnPlayerChat implements Listener {

    private final Bot plugin = Bot.getInstance();

    private final Set<Player> usersStaffChat = StaffChat.getStaffChatUsers();
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
        String playername = player.getName();
        channel.sendMessage(ChatColor.stripColor(playername) + " » " + message).queue();
    }
}

