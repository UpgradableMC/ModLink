package org.psycho.FunStuff;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpgradeGUI implements Listener, CommandExecutor {
    public Inventory UpgradePage1() {
        // Create an inventory with 3 rows
        Inventory inv = Bukkit.createInventory(null, 27, "Upgrade Your Rod");

        // Create a black stained glass pane
        ItemStack blackPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);

        // Set the name of the black stained glass pane to " "
        ItemMeta meta = blackPane.getItemMeta();
        meta.setDisplayName(" ");
        blackPane.setItemMeta(meta);

        // Fill all the slots in the inventory with the black stained glass pane
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, blackPane);
        }

        ItemStack FishingRod1 = createFishingRod(1, "1000", "The first rod. Take good care of it!");
        ItemStack FishingRod2 = createFishingRod(2, "2500", "The second rod.");
        ItemStack FishingRod3 = createFishingRod(3, "5000", "The third rod.");

        ItemStack NextButtonRods = new ItemStack(Material.ARROW, 1);
        ItemMeta NextButtonRodsMeta = NextButtonRods.getItemMeta();
        NextButtonRodsMeta.setDisplayName("§3§lNext Page");
        NextButtonRods.setItemMeta(NextButtonRodsMeta);

        inv.setItem(11, FishingRod1);
        inv.setItem(13, FishingRod2);
        inv.setItem(15, FishingRod3);
        inv.setItem(17, NextButtonRods);
        return inv;
    }

    public Inventory UpgradePage2(){
        // Create an inventory with 3 rows
        Inventory inv = Bukkit.createInventory(null, 27, "Upgrade Your Rod 2");

        // Create a black stained glass pane
        ItemStack blackPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);

        // Set the name of the black stained glass pane to " "
        ItemMeta meta = blackPane.getItemMeta();
        meta.setDisplayName(" ");
        blackPane.setItemMeta(meta);

        // Fill all the slots in the inventory with the black stained glass pane
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, blackPane);
        }

        ItemStack FishingRod4 = createFishingRod(4, "10000", "The fourth rod.");
        ItemStack FishingRod5 = createFishingRod(5, "20000", "The fifth rod.");
        ItemStack FishingRod6 = createFishingRod(6, "25000", "The sixth rod. Last rod achievable in the beta test.");

        ItemStack NextButtonRods = new ItemStack(Material.ARROW, 1);
        ItemMeta NextButtonRodsMeta = NextButtonRods.getItemMeta();
        NextButtonRodsMeta.setDisplayName("§3§lGo Back");
        NextButtonRods.setItemMeta(NextButtonRodsMeta);

        inv.setItem(11, FishingRod4);
        inv.setItem(13, FishingRod5);
        inv.setItem(15, FishingRod6);
        inv.setItem(9, NextButtonRods);
        return inv;
    }


    private ItemStack createFishingRod(int rodNumber, String cost, String description) {
        ItemStack fishingRod = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta fishingRodMeta = fishingRod.getItemMeta();
        fishingRodMeta.setDisplayName("§3§lBuy Fishing Rod " + rodNumber);
        List<String> fishingRodLore = new ArrayList<>();
        fishingRodLore.add("§7Cost: §a$" + cost);
        fishingRodLore.add("§7Description: §a" + description);
        fishingRodMeta.setLore(fishingRodLore);
        fishingRod.setItemMeta(fishingRodMeta);
        return fishingRod;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Check if the clicked inventory is the one you created
        if (event.getView().getTitle().equals("Upgrade Your Rod")) {
            // Check if the clicked item is a black stained glass pane
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE && event.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) {
                // Cancel the event
                event.setCancelled(true);
            }
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.ARROW && event.getCurrentItem().getItemMeta().getDisplayName().equals("§3§lNext Page")) {
                event.setCancelled(true);
                event.getWhoClicked().openInventory(UpgradePage2());
            } else {
                event.setCancelled(true);
            }
        }
        if (event.getView().getTitle().equals("Upgrade Your Rod 2")) {
            // Check if the clicked item is a black stained glass pane
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE && event.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) {
                // Cancel the event
                event.setCancelled(true);
            }
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.ARROW && event.getCurrentItem().getItemMeta().getDisplayName().equals("§3§lGo Back")) {
                event.setCancelled(true);
                event.getWhoClicked().openInventory(UpgradePage1());
            } else {
                event.setCancelled(true);
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command bozo.");
            return false;
        }

        Player player = (Player) sender;
        player.openInventory(UpgradePage1());
        return false;
    }
}
