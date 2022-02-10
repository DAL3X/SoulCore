package de.dal3x.soulcore.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.dal3x.soulcore.command.BankCommand;
import de.dal3x.soulcore.command.RandomTPCommand;
import de.dal3x.soulcore.command.SoulCoreCommand;
import de.dal3x.soulcore.docking.VaultBankDock;
import de.dal3x.soulcore.file.RegionFileHandler;
import de.dal3x.soulcore.listener.ArmorStandPlaceListener;
import de.dal3x.soulcore.listener.ChatListener;
import de.dal3x.soulcore.listener.MobSpawnListener;

public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;

    public static CorePlugin getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        getServer().getScheduler().runTaskLater(this, new Runnable() {
            public void run() {
                RegionFileHandler.initRegionStorage();
            }
        }, 200);
        getCommand("randomRegionTp").setExecutor(new RandomTPCommand());
        getCommand("soulcore").setExecutor(new SoulCoreCommand());
        getCommand("bank").setExecutor(new BankCommand());
        VaultBankDock.registerVault();
        getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandPlaceListener(), this);
        setUpChatPing();
    }

    public void onDisable() {
        instance = null;
    }

    public static boolean check_ConfigFile() {
        File f = new File("plugins/SoulCore/ChatPing/" + "config.yml");
        if (f.exists()) {
            return true;
        } else
            return false;
    }

    private void setUpChatPing() {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        if (!check_ConfigFile()) {
            File configFile = new File("plugins/SoulCore/ChatPing/" + "config.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            config.set("volume", (double) 20.0);
            config.set("pitch", (double) 1.0);
            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
