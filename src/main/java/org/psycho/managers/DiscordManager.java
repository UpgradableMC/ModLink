package org.psycho.managers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.entity.Player;
import org.psycho.Bot;
import org.psycho.events.discord.DiscordChatWatcher;
import org.psycho.events.discord.DiscordSlashCommands;
import org.psycho.events.discord.DiscordStaffChatWatcher;


import java.util.EnumSet;
import java.util.Map;


public class DiscordManager {
    private final Bot Instance = Bot.getInstance();
             private JDA jda;
    private final Map<Player, Boolean> staffChatStatus;
    public DiscordManager(Map<Player, Boolean> staffChatStatus) {
        this.staffChatStatus = staffChatStatus;
        try {
            jda = JDABuilder.createLight("MTIwNjgzNzMxMjQwMzY3MzA4OA.GfOsQ5.iZ5aiUxj78nPO65_4qDUDyBJB3Nn9D8F4ewmwE")
                    .enableIntents(EnumSet.allOf(GatewayIntent.class))
                    .addEventListeners(new DiscordChatWatcher())
                    .addEventListeners(new DiscordStaffChatWatcher())
                    .addEventListeners(new DiscordSlashCommands())
                    .build()
                    .awaitReady();

            Instance.getLogger().info("Successfully connected to Discord JDA!");
        } catch (Exception e) {
            e.printStackTrace();
            Instance.getLogger().severe("Unable to connect to Discord JDA!");
        }
    }

    public JDA getJDA() {
        return jda;
    }
}