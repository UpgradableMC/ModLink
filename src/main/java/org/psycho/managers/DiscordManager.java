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
import java.util.*;


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
            Path path = Paths.get("/plugins/ModLink/token.yaml");
            if (!Files.exists(path)) {
                Map<String, String> defaultConfig = new HashMap<>();
                defaultConfig.put("token", "YOUR_BOT_TOKEN");
                Map<String, Object> botConfig = new HashMap<>();
                botConfig.put("bot", defaultConfig);
                yaml.dump(botConfig, Files.newBufferedWriter(path));
                Instance.getLogger().severe("token.yaml created in /plugins/ModLink/. Please fill in YOUR_BOT_TOKEN!");
                return;
            }
            InputStream in = Files.newInputStream(path);
            Map<String, Object> yamlProps = yaml.load(in);
            in.close();

            Map<String, String> botProps = (Map<String, String>) yamlProps.get("bot");
            String botToken = botProps.get("token");
            if ("YOUR_BOT_TOKEN".equals(botToken)) {
                Instance.getLogger().severe("Please fill in YOUR_BOT_TOKEN in config.yaml!");
                return;
            }

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