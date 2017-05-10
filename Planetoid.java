package asteroids.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import asteroids.model.exceptions.IllegalRadiusException;

public class Planetoid extends MinorPlanet {
	
	public Planetoid(double xpos, double ypos, double xvel, double yvel, double radius, double distance) {
		super(xpos,ypos,xvel,yvel,radius);
		setMassDensity(DENSITY);
		setTotalDistance(distance);
	}
	
	@Override
	public void setMassDensity(double massDensity) {
		this.massDensity=DENSITY;
	}

	@Override
	public void move(double dt) {
		super.move(dt);
		setTotalDistance(getTotalDistance()+getXVelocity()*dt);
		setRadius(getRadius()-0.000001*getXVelocity()*dt);
	}
	
	@Override
	public void setRadius(double radius) {
		try {
			super.setRadius(radius);
			}
		catch (IllegalRadiusException exc) {
			terminate();
		}
	}
	
	public double getTotalDistance() {
		return this.totalDistance;
	}
	
	public void setTotalDistance(double distance) {
		if (distance >= 0) {
			this.totalDistance = distance;
		}
	}
	
	public static Set<Planetoid> getAllPlanetoids(World world) {
		Set<Planetoid> set = new HashSet<Planetoid>();
		Stream<Entity> stream = world.getAllEntities().stream().filter(entity -> (entity instanceof Planetoid));
		stream.forEach(planetoid -> set.add((Planetoid)planetoid));
		return set;
	}
	
	/**
	 * A variable registering the minimum mass density of this asteroid.
	 */
	private static double DENSITY = 0.917*Math.pow(10, 12);
	
	/**
	 * A variable registering the total distance traveled by this planetoid.
	 */
	private double totalDistance=0;

}
