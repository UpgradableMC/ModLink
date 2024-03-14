package org.psycho.TextHandlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.attribute.IGuildChannelContainer;
import org.apache.logging.log4j.LoggingException;
import java.util.List;


public class ChatInterface extends ListenerAdapter implements Listener {

    private final JDA jda;

    public ChatInterface(JDA jda) {
        this.jda = jda;
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
            // Check if the message has media
            if (!attachments.isEmpty()) {
                // The message has media
                for (Attachment attachment : attachments) {
                    if (attachment.isImage()) {
                        // The attachment is an image
                        Bukkit.broadcastMessage(user + " » " + messageraw + ChatColor.BOLD + "THIS MESSAGE CONTAINS AN IMAGE");
                    } else if (attachment.isVideo()) {
                        // The attachment is a video
                    }
                }
            } else {
                // The message does not have media
                Bukkit.broadcastMessage(user + " » " + messageraw);
            }
        }

    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        TextChannel channel = jda.getTextChannelById(1205269407350263889L);
        channel.sendMessage(player + " » " + message).queue();
    }

}