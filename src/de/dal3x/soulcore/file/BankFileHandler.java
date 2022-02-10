package de.dal3x.soulcore.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BankFileHandler {

	public static double getBankAccount(UUID id) {
		File bankFile = new File("plugins/SoulCore/bank", id.toString() + ".yml");
		if (!bankFile.exists()) {
			return 0;
		}
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(bankFile);
		return cfg.getDouble("account");
	}

	public static void setBankAccount(UUID id, double account) {
		File bankFile = new File("plugins/SoulCore/bank", id.toString() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(bankFile);
		cfg.set("account", account);
		try {
			cfg.save(bankFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
