package de.dal3x.soulcore.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.dal3x.soulcore.region.Region;
import de.dal3x.soulcore.region.TeleportRegionStorage;

public class RegionFileHandler {

	public static void initRegionStorage() {
		File regionFile = getFile();
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(regionFile);
		for (String name : cfg.getKeys(false)) {
			World w = Bukkit.getWorld(cfg.getString(name + ".world"));
			double centerX = cfg.getDouble(name + ".centerX");
			double centerZ = cfg.getDouble(name + ".centerZ");
			int length = cfg.getInt(name + ".length");
			int hotzone = cfg.getInt(name + ".hotzone");
			boolean hasLimit = false;
			if(hotzone > 0) {
				hasLimit = true;
			}
			TeleportRegionStorage.getInstance().addRegion(name, new Region(name, w, centerX, centerZ, length, hotzone, hasLimit));
		}
	}

	public static void addRegion(Region reg) {
		File regionFile = getFile();
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(regionFile);
		cfg.set(reg.getName() + ".world", reg.getMin().getWorld().getName());
		cfg.set(reg.getName() + ".centerX", reg.getCenterX());
		cfg.set(reg.getName() + ".centerZ", reg.getCenterZ());
		cfg.set(reg.getName() + ".length", reg.getLength());
		cfg.set(reg.getName() + ".hotzone", reg.getmobHotZone());
		try {
			cfg.save(regionFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeRegion(Region reg) {
		File regionFile = getFile();
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(regionFile);
		cfg.set(reg.getName(), null);
		try {
			cfg.save(regionFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static File getFile() {
		File regionFile = new File("plugins/SoulCore/TeleportRegions.yml");
		if (!regionFile.exists()) {
			// File doesn't exist
			try {
				File path = new File("plugins/SoulCore");
				if (!path.exists()) {
					path.mkdir();
				}
				regionFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return regionFile;
	}

}
