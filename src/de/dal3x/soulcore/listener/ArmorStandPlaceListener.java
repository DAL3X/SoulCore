package de.dal3x.soulcore.listener;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class ArmorStandPlaceListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onMobSpawnInRegion(EntitySpawnEvent event) {
		Entity ent = event.getEntity();
		if (ent instanceof ArmorStand) {
			Player result = null;
			double lastDistance = Double.MAX_VALUE;
			for (Entity p : ent.getNearbyEntities(10, 10, 10)) {
				double distance = ent.getLocation().distance(p.getLocation());
				if (distance < lastDistance && p instanceof Player && ((Player)p).hasPermission("soulcore.armorstand")) {
					lastDistance = distance;
					result = (Player)p;
				}
			}
			if (result != null) {
				((ArmorStand) ent).setArms(true);
			} else {
				return;
			}
		}
	}
}
