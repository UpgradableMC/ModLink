package org.psycho.events.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.psycho.Bot;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.bukkit.Bukkit.getServer;

public class DiscordStaffChatWatcher extends ListenerAdapter implements Listener {

    private final Bot plugin = Bot.getInstance();
    private final Map<Player, Boolean> staffChatStatus;

    public DiscordStaffChatWatcher(Map<Player, Boolean> staffChatStatus) {
        this.staffChatStatus = staffChatStatus;
    }
    public boolean isStaffChatEnabled(Player player) {
        return staffChatStatus.getOrDefault(player, false);
    }
    public Player getPlayerByUuid(UUID uuid) {
        for(Player p : getServer().getOnlinePlayers())
            if(p.getUniqueId().equals(uuid)){
                return p;
            } throw new IllegalArgumentException();
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannelUnion channel = event.getChannel();


        if (channel.getId().equals("1168051592780587021")) {
            Message message = event.getMessage();
            String messageraw = message.getContentRaw();
            User user = message.getAuthor();
            List<Message.Attachment> attachments = message.getAttachments();
            if (user.isBot()) {
                return;
            }
            for (Player staff : getServer().getOnlinePlayers()) {
                if (staff.hasPermission("staffchat.use")) {
                    staff.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "DISCORD SC " + ChatColor.AQUA + user.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + messageraw);
                    getLogger().info("DISCORD SC " + user.getName() + " » " + messageraw);
                }
            }
        }
    }
}
