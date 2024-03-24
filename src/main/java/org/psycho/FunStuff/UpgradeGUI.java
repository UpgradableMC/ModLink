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

        ItemStack FishingRod1 = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta FishingRod1Meta = FishingRod1.getItemMeta();
        FishingRod1Meta.setDisplayName("§3§lBuy Fishing Rod 1");
        List<String> FishingRod1Lore = new ArrayList<>();
        FishingRod1Lore.add("§7Cost: §a$0");
        FishingRod1Lore.add("§7Description: §aThe first rod. Take good care of it!");
        FishingRod1Meta.setLore(FishingRod1Lore);
        FishingRod1.setItemMeta(FishingRod1Meta);

        ItemStack FishingRod2 = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta FishingRod2Meta = FishingRod2.getItemMeta();
        FishingRod1Meta.setDisplayName("§3§lBuy Fishing Rod 2");
        List<String> FishingRod2Lore = new ArrayList<>();
        FishingRod2Lore.add("§7Cost: §a$0");
        FishingRod2Lore.add("§7Description: §aThe second rod.");
        FishingRod2Meta.setLore(FishingRod2Lore);
        FishingRod2.setItemMeta(FishingRod2Meta);

        ItemStack FishingRod3 = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta FishingRod3Meta = FishingRod3.getItemMeta();
        FishingRod1Meta.setDisplayName("§3§lBuy Fishing Rod 3");
        List<String> FishingRod3Lore = new ArrayList<>();
        FishingRod3Lore.add("§7Cost: §a$0");
        FishingRod3Lore.add("§7Description: §aThe third rod.");
        FishingRod3Meta.setLore(FishingRod3Lore);
        FishingRod3.setItemMeta(FishingRod3Meta);

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

        ItemStack FishingRod4 = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta FishingRod4Meta = FishingRod4.getItemMeta();
        FishingRod4Meta.setDisplayName("§3§lBuy Fishing Rod 4");
        List<String> FishingRod4Lore = new ArrayList<>();
        FishingRod4Lore.add("§7Cost: §a$10000");
        FishingRod4Lore.add("§7Description: §aThe fourth rod.");
        FishingRod4Meta.setLore(FishingRod4Lore);
        FishingRod4.setItemMeta(FishingRod4Meta);

        ItemStack FishingRod5 = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta FishingRod5Meta = FishingRod5.getItemMeta();
        FishingRod5Meta.setDisplayName("§3§lBuy Fishing Rod 5");
        List<String> FishingRod5Lore = new ArrayList<>();
        FishingRod5Lore.add("§7Cost: §a$20000");
        FishingRod5Lore.add("§7Description: §aThe fifth rod.");
        FishingRod5Meta.setLore(FishingRod5Lore);
        FishingRod5.setItemMeta(FishingRod5Meta);

        ItemStack FishingRod6 = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta FishingRod6Meta = FishingRod6.getItemMeta();
        FishingRod6Meta.setDisplayName("§3§lBuy Fishing Rod 6");
        List<String> FishingRod6Lore = new ArrayList<>();
        FishingRod6Lore.add("§7Cost: §a$25000");
        FishingRod6Lore.add("§7Description: §aThe sixth rod. Last rod achievable in the beta test.");
        FishingRod6Meta.setLore(FishingRod6Lore);
        FishingRod6.setItemMeta(FishingRod6Meta);

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
