package de.dal3x.soulcore.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import de.dal3x.soulcore.file.RegionFileHandler;
import de.dal3x.soulcore.region.Region;
import de.dal3x.soulcore.region.TeleportRegionStorage;

public class SoulCoreRegionCommandHandler {

	public static boolean handleRegion(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 3 && args[1].equalsIgnoreCase("create")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player p = (Player) sender;
			try {
				double x = p.getLocation().getX();
				double z = p.getLocation().getZ();
				Region reg = new Region(args[2], p.getWorld(), x, z, Integer.parseInt(args[3]), -1, false);
				TeleportRegionStorage.getInstance().addRegion(args[2], reg);
				RegionFileHandler.addRegion(reg);
				// Add a worldguard Region
				RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
				RegionManager regions = container.get(BukkitAdapter.adapt(reg.getMin().getWorld()));
				BlockVector3 min = BlockVector3.at(reg.getMin().getBlockX(), 0, reg.getMin().getBlockZ());
				BlockVector3 max = BlockVector3.at(reg.getMax().getBlockX(), 256, reg.getMax().getBlockZ());
				ProtectedRegion region = new ProtectedCuboidRegion("wbr_" + reg.getName(), min, max);
				regions.addRegion(region);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			return true;
		}
		if (args.length > 2 && args[1].equalsIgnoreCase("delete")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Region reg = TeleportRegionStorage.getInstance().getRegion(args[2]);
			RegionFileHandler.removeRegion(reg);
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
			RegionManager regions = container.get(BukkitAdapter.adapt(reg.getMin().getWorld()));
			regions.removeRegion("wbr_" + reg.getName(), RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
			TeleportRegionStorage.getInstance().removeRegion(reg.getName());
			return true;
		}
		if (args.length > 2 && args[1].equalsIgnoreCase("activate")) {
			Region reg = TeleportRegionStorage.getInstance().getRegion(args[2]);
			Location center = new Location(reg.getMin().getWorld(), reg.getCenterX(), 0, reg.getCenterZ());
			reg.getMin().getWorld().getWorldBorder().setCenter(center);
			reg.getMin().getWorld().getWorldBorder().setSize(reg.getLength());
			return true;
		}
		if (args.length > 2 && args[1].equalsIgnoreCase("deactivate")) {
			Region reg = TeleportRegionStorage.getInstance().getRegion(args[2]);
			reg.getMin().getWorld().getWorldBorder().setCenter(new Location(reg.getMin().getWorld(), 0, 0, 0));
			reg.getMin().getWorld().getWorldBorder().setSize(29999984);
			return true;
		}
		if (args.length > 2 && args[1].equalsIgnoreCase("hotzone")) {
			Region reg = TeleportRegionStorage.getInstance().getRegion(args[2]);
			reg.setMobHotZone(Integer.parseInt(args[3]));
			if (Integer.parseInt(args[3]) >= 0) {
				reg.setHasMobLimit(true);
			} else {
				reg.setHasMobLimit(false);
			}
			RegionFileHandler.addRegion(reg);
			return true;
		}
		return true;
	}
}
