package org.psycho;

import java.net.http.WebSocket.Listener;
import java.util.EnumSet;

import org.apache.logging.log4j.LoggingException;
import org.bukkit.plugin.java.JavaPlugin;
import org.psycho.TextHandlers.Announcements;
import org.psycho.TextHandlers.ChatInterface;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public final class Bot extends JavaPlugin implements Listener{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void onEnable() throws LoggingException {

        JDA jda = JDABuilder.createDefault("MTIwNjgzOTQzNDQ2NTc3NTY0Ng.GiLhS_.f-S6q3SSF6xGRMmjuYd8Mp2LlUq51nHoouEOfs")
            .enableIntents(GatewayIntent.GUILD_MEMBERS,GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MESSAGE_REACTIONS,GatewayIntent.DIRECT_MESSAGES,GatewayIntent.DIRECT_MESSAGE_REACTIONS)
            .setActivity(Activity.playing("UpgradableMC"))
            .setStatus(OnlineStatus.ONLINE)
            .build();
            jda.addEventListener(new ChatInterface(jda));
        try {
            jda.awaitReady();
            getLogger().info("Starting up the bot!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new ChatInterface(jda), this);
        getCommand("broadcast").setExecutor(new Announcements(this));
        
    }

    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down the bot.");
    }
}