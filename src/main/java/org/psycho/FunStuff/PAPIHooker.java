package org.psycho.FunStuff;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.psycho.Bot;

public class PAPIHooker extends PlaceholderExpansion implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        joinCounter.addUniqueJoin(event.getPlayer());
    }
    private final JoinCounter joinCounter = new JoinCounter();
    private final Bot plugin;

    public PAPIHooker(Bot plugin) {
        this.plugin = plugin;
    }
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if ("unique_joins".equals(identifier)) {
            return String.valueOf(plugin.getJoinCounter().getUniqueJoinCount());
        }

        return null;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ModLink";
    }

    @Override
    public @NotNull String getAuthor() {
        return "UpgradableMC";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
}
