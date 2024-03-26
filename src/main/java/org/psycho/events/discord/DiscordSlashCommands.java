package org.psycho.events.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import net.dv8tion.jda.api.entities.channel.*;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.awt.*;


public class DiscordSlashCommands extends ListenerAdapter {

    public void onSlashCommand(SlashCommandInteractionEvent event) {
        if (event.getName().equals("punish")) {
            String arg1 = event.getOption("name").getAsString();


            MessageEmbed embed = createMenuEmbed(event.getName());

            event.getChannel().sendMessageEmbeds(embed).setActionRow(
                    StringSelectMenu.create("MuteMenu")
                            .addOption("15 minutes", "15m")
                            .addOption("30 minutes", "30m")
                            .addOption("1 hour", "1h")
                            .addOption("1 day", "1d")
                            .addOption("1 week", "1w")
                            .addOption("1 month", "1mo")
                            .addOption("Permanent", "perm")
                            .build())
                    .setActionRow(
                    StringSelectMenu.create("KickMenu")
                            .addOption("15 minutes", "15m")
                            .addOption("30 minutes", "30m")
                            .addOption("1 hour", "1h")
                            .addOption("1 day", "1d")
                            .addOption("1 week", "1w")
                            .addOption("1 month", "1mo")
                            .addOption("Permanent", "perm")
                            .build())
                    .setActionRow(
                            StringSelectMenu.create("BanMenu")
                                    .addOption("15 minutes", "15m")
                                    .addOption("30 minutes", "30m")
                                    .addOption("1 hour", "1h")
                                    .addOption("1 day", "1d")
                                    .addOption("1 week", "1w")
                                    .addOption("1 month", "1mo")
                                    .addOption("Permanent", "perm")
                                    .build()).queue();

        }
    }

    public MessageEmbed createMenuEmbed(String name) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Punish " + name);
        embedBuilder.setDescription("Please select a punishment type.");
        embedBuilder.setColor(Color.RED);

        MessageEmbed embed = embedBuilder.build();

        return embed;
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("MuteMenu")) {
            event.reply("You chose " + event.getValues().get(0)).queue();
        }
    }

}


