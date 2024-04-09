package org.psycho.events.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import net.dv8tion.jda.api.entities.channel.*;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.internal.handle.ReadyHandler;
import org.psycho.Bot;
import org.psycho.managers.DiscordManager;

import java.awt.*;


public class DiscordSlashCommands extends ListenerAdapter {

    private static final JDA jda = DiscordManager.getJDA();

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
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

    public static void updateCommands() {
        jda.updateCommands().addCommands(Commands.slash("punish", "Punish a player")
                .addOption(OptionType.STRING, "name", "The name of the player", true)
                ).queue();
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