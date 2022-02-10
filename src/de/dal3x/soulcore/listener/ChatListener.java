package de.dal3x.soulcore.listener;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.dal3x.soulcore.main.CorePlugin;

public class ChatListener implements Listener {

    public static boolean DEV_MESSAGES = false;

    /**
     * Load Configuration-File and returns Volume
     * 
     * @return float
     **/
    public static float load_Volume() {
        if (CorePlugin.check_ConfigFile()) {
            File configFile = new File("plugins/SoulCore/ChatPing/" + "config.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            return (float) config.getDouble("volume");
        } else {
            return 20.0f; // default value
        }
    }

    /**
     * Load Configuration-File and returns Pitch
     * 
     * @return float
     **/
    public static float load_Pitch() {
        if (CorePlugin.check_ConfigFile()) {
            File configFile = new File("plugins/SoulCore/ChatPing/" + "config.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            return (float) config.getDouble("pitch");
        } else {
            return 1.0f; // default value
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // Checks if a Player wrote @
        if (event.getMessage().contains("@")) {

            String message = event.getMessage();

            int posAt = message.indexOf("@");

            int indexLast = message.length();
            String substring = message.substring(posAt + 1);
            String name = "test";

            if (substring.contains(" ")) {
                indexLast = substring.indexOf(" ");
                name = substring.substring(0, indexLast);
            } else {
                indexLast = substring.length();
                name = substring.substring(0, indexLast);
            }

            @SuppressWarnings("deprecation")
            OfflinePlayer player_ = Bukkit.getOfflinePlayer(name);
            if (player_.isOnline()) {
                ((Player) player_).playSound(((Entity) player_).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, load_Volume(),
                        load_Pitch());
            }

            if (DEV_MESSAGES) {
                System.out.println(message);
                System.out.println("PosAt: " + posAt);
                System.out.println("Substring " + substring);
                System.out.println("Name " + name);
            }

        }
    }

}
