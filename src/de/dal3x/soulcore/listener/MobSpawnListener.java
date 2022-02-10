package de.dal3x.soulcore.listener;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import de.dal3x.soulcore.region.Region;
import de.dal3x.soulcore.region.TeleportRegionStorage;

public class MobSpawnListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onMobSpawnInRegion(CreatureSpawnEvent event) {
		Location spawnLoc = event.getLocation();
		for (String regname : TeleportRegionStorage.getInstance().getRegions().keySet()) {
			Region reg = TeleportRegionStorage.getInstance().getRegion(regname);
			if (spawnLoc.getWorld().equals(reg.getMin().getWorld())) {
				if (reg.hasMobLimit() && isInRegion(spawnLoc, reg)) {
					int hotzone = reg.getmobHotZone();
					for (Entity ent : event.getEntity().getNearbyEntities(hotzone, hotzone, hotzone)) {
						if (ent instanceof Creature) {
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}

	private boolean isInRegion(Location loc, Region reg) {
		double minX = reg.getMin().getX();
		double minZ = reg.getMin().getZ();
		double maxX = reg.getMax().getX();
		double maxZ = reg.getMax().getZ();
		if (minX < loc.getX() && loc.getX() < maxX) {
			if (minZ < loc.getZ() && loc.getZ() < maxZ) {
				return true;
			}
		}
		return false;
	}
}
