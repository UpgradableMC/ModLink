package org.psycho.events.discord;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.psycho.Bot;

import java.util.*;

import static org.bukkit.Bukkit.getServer;


public class DiscordChatWatcher extends ListenerAdapter implements Listener {

    private final Bot plugin = Bot.getInstance();
    public Player getPlayerByUuid(UUID uuid) {
        for(Player p : getServer().getOnlinePlayers())
            if(p.getUniqueId().equals(uuid)){
                return p;
            } throw new IllegalArgumentException();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannelUnion channel = event.getChannel();

        // Check if the message is from the desired channel
        if (channel.getId().equals("1205269407350263889")) {
            Message message = event.getMessage();
            String messageraw = message.getContentRaw();
            User user = message.getAuthor();
            List<Attachment> attachments = message.getAttachments();
            if (user.isBot()) {
                return;
            }
            Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "DISCORD " + ChatColor.AQUA + user.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + messageraw);
        }
    }
}
