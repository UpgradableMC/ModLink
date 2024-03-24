package org.psycho.TextHandlers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.entity.Player;
import org.psycho.Bot;
import org.psycho.TextHandlers.*;


import java.util.EnumSet;
import java.util.Map;
import java.util.Set;


public class DiscordManager {
    private final Bot Instance = Bot.getInstance();
             private JDA jda;
    private final Map<Player, Boolean> staffChatStatus;
    public DiscordManager(Map<Player, Boolean> staffChatStatus) {
        this.staffChatStatus = staffChatStatus;
        try {
            jda = JDABuilder.createLight("MTIwNjgzNzMxMjQwMzY3MzA4OA.GfOsQ5.iZ5aiUxj78nPO65_4qDUDyBJB3Nn9D8F4ewmwE")
                    .enableIntents(EnumSet.allOf(GatewayIntent.class))
                    .addEventListeners(new ChatInterface())
                    .addEventListeners(new StaffChatInterface(staffChatStatus))
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