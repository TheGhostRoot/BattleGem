package me.thegoldenmine.gemofwar;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private final GemOfWar plugin;
    private final File MobFile;
    private FileConfiguration mobData;

    public Config(GemOfWar main) throws IOException {
        plugin = main;
        File dataFolder = plugin.getDataFolder();
        MobFile = new File(dataFolder, "mob.yml");
        mobData = YamlConfiguration.loadConfiguration(MobFile);
        if (!dataFolder.exists() && !MobFile.exists()) {
            System.out.println("<---{ Creating New Config Files }--->");
            System.out.println(" ");
            if (dataFolder.mkdir()) {
                System.out.println("  <> Folder Created <>");
            } else {
                System.out.println("  <> Couldn't Create The Folder <>");
            }
            System.out.println(" ");
            if (MobFile.createNewFile()) {
                System.out.println("  <> Mob File Created <>");
            } else {
                System.out.println("  <> Couldn't Create Mob File <>");
            }
            System.out.println(" ");
            mobData = YamlConfiguration.loadConfiguration(MobFile);
            System.out.println("  <-> Set Data <->");
            System.out.println(" ");
            setIntMob("max_mob_spawn", 10);
            setIntMob("Zombie_Health", 50);
            setIntMob("Skeleton_Health", 30);
            setIntMob("Spider_Health", 30);
            setIntMob("Pig_Health", 20);
        }
        if (!dataFolder.exists()) {
            if (dataFolder.mkdir()) {
                System.out.println("  <-> Created A Folder <->");
                System.out.println(" ");
            } else {
                System.out.println("  <-> Couldn't Create The Folder <->");
            }
        }

        if (!MobFile.exists()) {
            if (MobFile.createNewFile()) {
                System.out.println("  <> Created mob.yml <>");
            } else {
                System.out.println("  <> Couldn't Create Mob File <>");
            }
            System.out.println(" ");
            try {
                mobData.load(MobFile);
                System.out.println("  <> Loaded mob.yml <>");
                System.out.println(" ");
            } catch (Exception e) {
                System.out.println("  <> Couldn't Load mob.yml <-");
                System.out.println(" ");
                System.out.println("- BattleGem ERROR - trying to load mob.yml");
            }
        }
    }

    public synchronized void saveMob() {
        try {
            mobData.save(MobFile);
        } catch (IOException e) {
            throw new RuntimeException("- BattleGem ERROR - Cannot save mob.yml", e);
        }
    }

    public synchronized void reloadMob() {
        mobData = YamlConfiguration.loadConfiguration(MobFile);
    }

    public int getIntMob(String path) {
        return mobData.getInt(path);
    }

    public void setIntMob(String path, int i) {
        mobData.set(path, i);
        saveMob();
        reloadMob();
    }
}
