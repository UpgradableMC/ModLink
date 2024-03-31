package org.psycho.managers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.entity.Player;
import org.psycho.Bot;
import org.psycho.events.discord.DiscordChatWatcher;
import org.psycho.events.discord.DiscordSlashCommands;
import org.psycho.events.discord.DiscordStaffChatWatcher;
import org.yaml.snakeyaml.Yaml;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.EnumSet;
import java.util.Map;
import java.util.Properties;


public class DiscordManager {
    private final Bot Instance = Bot.getInstance();
             private JDA jda;
    private final Map<Player, Boolean> staffChatStatus;

    public String decode(String encodedToken) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedToken);
        return new String(decodedBytes);
    }

    public DiscordManager(Map<Player, Boolean> staffChatStatus) {
        this.staffChatStatus = staffChatStatus;
        try {
            Yaml yaml = new Yaml();
            Path path = Paths.get("/plugins/ModLink/config.yaml");
            InputStream in = Files.newInputStream(path);
            Map<String, Object> yamlProps = yaml.load(in);
            in.close();

            Map<String, String> botProps = (Map<String, String>) yamlProps.get("bot");
            String botToken = botProps.get("token");

            jda = JDABuilder.createLight(decode(botToken))
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