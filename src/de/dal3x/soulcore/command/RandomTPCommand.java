package de.dal3x.soulcore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.dal3x.soulcore.region.Region;
import de.dal3x.soulcore.region.TeleportRegionStorage;

public class RandomTPCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			return false;
		}
		if (args.length != 2) {
			return true;
		}
		@SuppressWarnings("deprecation")
		Player p = Bukkit.getPlayer(args[0]);
		String regionName = args[1];
		Region region = TeleportRegionStorage.getInstance().getRegion(regionName);
		if (region != null) {
			p.teleport(region.getMin().getWorld().getHighestBlockAt(region.getRandomLocationInRegion()).getLocation().add(0, 1, 0));
		}
		return true;
	}
}
