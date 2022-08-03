package me.thegoldenmine.gemofwar;

import me.thegoldenmine.gemofwar.Cmds.GiveGem;
import me.thegoldenmine.gemofwar.Cmds.HelpMenu;
import me.thegoldenmine.gemofwar.Cmds.SetMaxMobSpawn;
import me.thegoldenmine.gemofwar.CoolDowns.Checker;
import me.thegoldenmine.gemofwar.CoolDowns.PigHeal;
import me.thegoldenmine.gemofwar.Listeners.AntiGlich;
import me.thegoldenmine.gemofwar.Listeners.Attacks;
import me.thegoldenmine.gemofwar.Listeners.UseGem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GemOfWar extends JavaPlugin {
    private GemOfWar gemOfWar;
    public Checker checker;
    public UseGem useGem;
    public Attacks attacks;
    public AntiGlich antiGlich;
    public Config config;
    public PigHeal pigHeal;

    public HashMap<UUID, List<LivingEntity>> spawnedMobs = new HashMap<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        String version = "N/A";

        try {

            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            getLogger().severe("Failed to setup BattleGem!");
            getLogger().severe("Your server version is not compatible with this plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        if (!version.equals("N/A")) {
            getLogger().info("Your server is running version " + version);
            switch (version) {
                case "v1_18_R1":
                case "v1_18_R2":
                case "v1_19_R1":
                case "v1_16_R3":
                case "v1_16_R2":
                case "v1_16_R1":
                case "v1_15_R1":
                case "v1_14_R1":
                case "v1_17_R1":
                case "v1_8_R3":
                case "v1_9_R1":
                case "v1_9_R2":
                case "v1_10_R1":
                case "v1_11_R1":
                case "v1_12_R1":
                case "v1_13_R1":
                case "v1_13_R2":
                    gemOfWar = this;
                    break;
            }
        }
        // This will return true if the server version was compatible with one of our NMS classes
        // because if it is, our actionbar would not be null
        if (gemOfWar != null) {
            try {
                System.out.println("<---{ Loading Config Files }--->");
                System.out.println(" ");
                System.out.println("  <> Registering Configs <>");
                System.out.println(" ");
                config = new Config(this);
            } catch (IOException e) {
                System.out.println("<---{ Couldn't load config files }--->");
                System.out.println(" ");
                throw new RuntimeException("- BattleGem ERROR - Cannot prepare config files", e);
            }
            ItemManager.init();
            checker = new Checker(this);
            attacks = new Attacks(this);
            useGem = new UseGem(this);
            antiGlich = new AntiGlich(this);
            pigHeal = new PigHeal(this);
            getCommand("battlegem").setExecutor(new GiveGem());
            getCommand("battlegem-help").setExecutor(new HelpMenu());
            getCommand("battlegem-set").setExecutor(new SetMaxMobSpawn(this));
            System.out.println("  <> Registered Commands <>");
            getServer().getPluginManager().registerEvents(new UseGem(this), this);
            getServer().getPluginManager().registerEvents(new Attacks(this), this);
            getServer().getPluginManager().registerEvents(new AntiGlich(this), this);
            System.out.println("  <> Registered Events <>");
        } else {
            getLogger().severe("Failed to setup BattleGem!");
            getLogger().severe("Your server version is not compatible with this plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (!spawnedMobs.isEmpty()) {
            for (List<LivingEntity> entities : spawnedMobs.values()) {
                for (LivingEntity entity : entities) {
                    entity.setHealth(0);
                }
            }
        }
        spawnedMobs.clear();
    }

    public Inventory getGemGUI() {
        Inventory gemGUI = Bukkit.createInventory(null, 27, ChatColor.RED + "" + ChatColor.BOLD + "Gem Army");
        gemGUI.setItem(0, ItemManager.glassForGUI);
        gemGUI.setItem(1, ItemManager.glassForGUI);
        gemGUI.setItem(2, ItemManager.glassForGUI);
        gemGUI.setItem(3, ItemManager.glassForGUI);
        gemGUI.setItem(4, ItemManager.glassForGUI);
        gemGUI.setItem(5, ItemManager.glassForGUI);
        gemGUI.setItem(6, ItemManager.glassForGUI);
        gemGUI.setItem(7, ItemManager.glassForGUI);
        gemGUI.setItem(8, ItemManager.closeGUI);
        gemGUI.setItem(7, ItemManager.glassForGUI);
        gemGUI.setItem(9, ItemManager.glassForGUI);
        gemGUI.setItem(10, ItemManager.ZombieGemGUI);
        gemGUI.setItem(11, ItemManager.SkeletonGemGUI);
        gemGUI.setItem(12, ItemManager.SpiderGemGUI);
        gemGUI.setItem(13, ItemManager.WolfGenGUI);
        gemGUI.setItem(14, ItemManager.PigGemGUI);
        gemGUI.setItem(15, ItemManager.glassForGUI);
        gemGUI.setItem(16, ItemManager.glassForGUI);
        gemGUI.setItem(17, ItemManager.glassForGUI);
        gemGUI.setItem(18, ItemManager.glassForGUI);  // random choice
        gemGUI.setItem(19, ItemManager.glassForGUI);
        gemGUI.setItem(20, ItemManager.glassForGUI);
        gemGUI.setItem(21, ItemManager.glassForGUI);
        gemGUI.setItem(22, ItemManager.glassForGUI);
        gemGUI.setItem(23, ItemManager.glassForGUI);
        gemGUI.setItem(24, ItemManager.glassForGUI);
        gemGUI.setItem(25, ItemManager.glassForGUI);
        gemGUI.setItem(26, ItemManager.glassForGUI);
        return gemGUI;
    }
}
