package de.dal3x.soulcore.region;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;

public class Region  {

	private Location min;
	private Location max;
	private String name;
	private double centerX;
	private double centerZ;
	private int length;
	private int mobHotZoneLength;
	private boolean hasMobLimit;

	public Region(String name, World w, double centerX, double centerZ, int length, int mobHotZoneLength, boolean hasMobLimit) {
			setName(name);
			setLength(length);
			setCenterX(centerX);
			setCenterZ(centerZ);
			setMobHotZone(mobHotZoneLength);
			setHasMobLimit(hasMobLimit);
			setMin(new Location(w, centerX - 0.5 * length, 0, centerZ - 0.5 * length));
			setMax(max = new Location(w, centerX + 0.5 * length, 0, centerZ + 0.5 * length));
	}

	public Location getRandomLocationInRegion() {
		Random ran = new Random();
		double xRange = Math.max(min.getX(), max.getX()) - Math.min(min.getX(), max.getX());
		double yRange = Math.max(min.getY(), max.getY()) - Math.min(min.getY(), max.getY());
		double zRange = Math.max(min.getZ(), max.getZ()) - Math.min(min.getZ(), max.getZ());
		double xCoord = ran.nextDouble() * xRange + Math.min(min.getX(), max.getX());
		double yCoord = ran.nextDouble() * yRange + Math.min(min.getY(), max.getY());
		double zCoord = ran.nextDouble() * zRange + Math.min(min.getZ(), max.getZ());
		return new Location(min.getWorld(), xCoord, yCoord, zCoord);
	}

	public Location getMin() {
		return min;
	}

	public void setMin(Location min) {
		this.min = min;
	}

	public Location getMax() {
		return max;
	}

	public void setMax(Location max) {
		this.max = max;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterZ() {
		return centerZ;
	}

	public void setCenterZ(double centerZ) {
		this.centerZ = centerZ;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getmobHotZone() {
		return mobHotZoneLength;
	}

	public void setMobHotZone(int hotZone) {
		this.mobHotZoneLength = hotZone;
	}

	public boolean hasMobLimit() {
		return hasMobLimit;
	}

	public void setHasMobLimit(boolean hasMobLimit) {
		this.hasMobLimit = hasMobLimit;
	}

}