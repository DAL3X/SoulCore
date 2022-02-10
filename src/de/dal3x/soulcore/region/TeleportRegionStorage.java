package de.dal3x.soulcore.region;

import java.util.HashMap;

public class TeleportRegionStorage {

	private static TeleportRegionStorage instance;
	private HashMap<String, Region> regions;

	private TeleportRegionStorage() {
		instance = this;
		regions = new HashMap<String, Region>();
	}

	public static TeleportRegionStorage getInstance() {
		if(instance == null) {
			return new TeleportRegionStorage();
		}
		return instance;
	}
	
	public static void clearInstance() {
		instance = null;
	}
	
	public void addRegion(String name, Region region) {
		this.regions.put(name, region);
	}
	
	public void removeRegion(String name) {
		this.regions.remove(name);
	}
	
	public Region getRegion(String name) {
		return this.regions.get(name);
	}
	
	public HashMap<String, Region> getRegions() {
		return this.regions;
	}
	
	
}
