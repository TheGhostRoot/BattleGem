package me.thegoldenmine.gemofwar;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack glassForGUI;
    public static ItemStack closeGUI;
    public static ItemStack Gem;
    public static ItemStack SkeletonGemGUI;
    public static ItemStack ZombieGemGUI;
    public static ItemStack PigGemGUI;
    public static ItemStack WolfGenGUI;
    public static ItemStack SpiderGemGUI;

    public static void init() {
        setGlassForGUI();
        setCloseGUI();
        setGem();

        setPigGemGUI();
        setSkeletonGemGUI();
        setZombieGemGUI();
        setWolfGenGUI();
        setSpiderGemGUI();
    }

    private static void setSkeletonGemGUI() {
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GRAY+ "" + ChatColor.ITALIC + "Skeleton");
        List<String> lore = new ArrayList<>();
        // §
        lore.add("§6§lPower >§7§o Shoots strong arrows.");
        lore.add("§6§lClick§7§o to spawn Skeleton.");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        SkeletonGemGUI = item;
    }

    private static void setZombieGemGUI() {
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED+ "" + ChatColor.ITALIC + "Zombie");
        List<String> lore = new ArrayList<>();
        // §
        lore.add("§6§lPower >§7§o Stronger then normal.");
        lore.add("§6§lClick§7§o to spawn "+ChatColor.RED+""+ChatColor.ITALIC+"Zombie"+ChatColor.GRAY+""+ChatColor.ITALIC+".");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        ZombieGemGUI = item;
    }

    private static void setSpiderGemGUI() {
        ItemStack item = new ItemStack(Material.SPIDER_EYE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.DARK_PURPLE+ "" + ChatColor.ITALIC + "Spider");
        List<String> lore = new ArrayList<>();
        // §
        lore.add("§6§lPower >§7§o Strong and fast.");
        lore.add("§6§lClick§7§o to spawn "+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Spider"+ChatColor.GRAY+""+ChatColor.ITALIC+".");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        SpiderGemGUI = item;
    }

    private static void setPigGemGUI() {
        ItemStack item = new ItemStack(Material.POTATO, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD+ "" + ChatColor.ITALIC + "Pig");
        List<String> lore = new ArrayList<>();
        // §
        lore.add("§6§lPower >§7§o Heals your army and you. Deals no damage.");
        lore.add("§6§lClick§7§o to spawn "+ChatColor.GOLD+""+ChatColor.ITALIC+"Pig"+ChatColor.GRAY+""+ChatColor.ITALIC+".");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        PigGemGUI = item;
    }

    private static void setWolfGenGUI() {
        ItemStack item = new ItemStack(Material.BONE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GRAY+ "" + ChatColor.ITALIC + "Wolf");
        List<String> lore = new ArrayList<>();
        // §
        lore.add("§6§lPower >§7§o Jumps to catch the enemy.");
        lore.add("§6§lClick§7§o to spawn Wolf.");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        WolfGenGUI = item;
    }

    private static void setGlassForGUI() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GRAY + "");
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        glassForGUI = item;
    }

    private static void setCloseGUI() {
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close");
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        closeGUI = item;
    }

    private static void setGem() {
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Gem");
        List<String> lore = new ArrayList<>();
        // §
        lore.add("§6§lRight Click§7§o the gem to open your army GUI.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.KNOCKBACK, 2, false);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        Gem = item;
    }
}
