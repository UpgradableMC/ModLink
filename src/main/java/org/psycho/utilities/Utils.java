package org.psycho.utilities;

import org.bukkit.entity.Player;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class Utils {
    public static Player getPlayerByUuid(UUID uuid) {
        for(Player p : getServer().getOnlinePlayers())
            if(p.getUniqueId().equals(uuid)){
                return p;
            } throw new IllegalArgumentException();
    }
}
