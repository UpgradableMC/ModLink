package org.psycho.FunStuff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
public class BossBarBar {
    private double progress = 0.0;
    private int currentIndex = 0;
    private BossBar bossBar;

    public BossBar getBossBar() {
        return bossBar;
    }

    public void enableBossBar() {
        List<String> messages = Arrays.asList(
                ChatColor.DARK_AQUA + "Check out our Discord! /discord",
                ChatColor.DARK_AQUA + "Support the server by buying a rank! /buy"
        );


        BossBar bossBar = Bukkit.createBossBar(
                messages.get(0),
                BarColor.BLUE,
                BarStyle.SOLID);

        // Add all online players to the BossBar
        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                bossBar.setTitle(messages.get(currentIndex));
                bossBar.setProgress(progress);
                progress += 0.005; // increment by 1% each tick
                if (progress >= 1.0) {
                    progress = 0.0; // reset progress
                    currentIndex++; // move to next message
                    if (currentIndex >= messages.size()) {
                        currentIndex = 0;
                    }
                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("ModLink"), 0, 1); // run every tick (20 ticks = 1 second)

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    bossBar.addPlayer(player);
                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("ModLink"), 0, 1200); // run every tick (20 ticks = 1 second)
        }

}


