package org.psycho.TextHandlers;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class StaffChatInterface extends ListenerAdapter implements Listener {
    private final Set<UUID> usersStaffChat = StaffChat.getStaffChatUsers();

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
            for (UUID uuid : usersStaffChat) {
                Player player = getPlayerByUuid(uuid);
                player.sendMessage(user.getName() + " » " + messageraw);
            }
        }
    }
}
