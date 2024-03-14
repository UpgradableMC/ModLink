package org.psycho;

import java.net.http.WebSocket.Listener;
import org.apache.logging.log4j.LoggingException;
import org.bukkit.plugin.java.JavaPlugin;
import org.psycho.TextHandlers.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;

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
    }

    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down the bot.");
    }
}