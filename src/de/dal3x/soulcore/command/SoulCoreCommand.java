package de.dal3x.soulcore.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SoulCoreCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("soulcraft.core")) {
			return true;
		}
		if (args.length > 0 && args[0].equalsIgnoreCase("region")) {
			return SoulCoreRegionCommandHandler.handleRegion(sender, cmd, label, args);
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
			if(sender.hasPermission("soulcore.help")) {
				sender.sendMessage("§b/soulcore region create [RegionName] [RegionSeitenBreite]");
				sender.sendMessage("§b/soulcore region activate [RegionName]");
				sender.sendMessage("§b/soulcore region deactivate [RegionName]");
				sender.sendMessage("§b/soulcore region delete [RegionName]");
				sender.sendMessage("§b/soulcore region hotzone [RegionName] [Breite]");
				sender.sendMessage("§b/randomRegionTp [Spielername] [RegionName] §c(Nur für Konsole)");
				sender.sendMessage("§b/legion givexp [Spielername] [Menge]");
				sender.sendMessage("§b/legion givelegionxp [Legionsname] [Menge]");
			}
		}
		return true;
	}
}
