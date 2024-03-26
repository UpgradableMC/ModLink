package org.psycho.events.minecraft;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.psycho.Bot;
import org.psycho.managers.BossbarManagerDISABLED;

public class OnJoinLeave implements Listener {
    private final Bot plugin = Bot.getInstance();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
        String playername = player.getName();
        channel.sendMessage(ChatColor.stripColor(playername) + " has joined the server.").queue();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        TextChannel channel = plugin.getDiscordManager().getJDA().getTextChannelById(1205269407350263889L);
        String playername = player.getName();
        channel.sendMessage(ChatColor.stripColor(playername) + " has left the server.").queue();
    }
}
