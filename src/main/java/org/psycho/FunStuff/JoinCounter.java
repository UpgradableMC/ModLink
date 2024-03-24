package org.psycho.FunStuff;

import org.bukkit.entity.Player;

import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class JoinCounter {
    private final Set<UUID> uniqueJoins = new HashSet<>();

    public void addUniqueJoin(Player player) {
        UUID uuid = player.getUniqueId();
        uniqueJoins.add(uuid);
    }

    public int getUniqueJoinCount() {

        return uniqueJoins.size();
    }
}