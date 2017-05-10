package asteroids.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Asteroid extends MinorPlanet {
	
	public Asteroid(double xpos, double ypos, double xvel, double yvel, double radius) {
		super(xpos,ypos,xvel,yvel,radius);
		setMassDensity(DENSITY);
	}
	
	@Override
	public void setMassDensity(double massDensity) {
		this.massDensity=DENSITY;
	}

	/**
	 * A variable registering the minimum mass density of this asteroid.
	 */
	protected static double DENSITY = 2.65*Math.pow(10, 12);
	
	public static Set<Asteroid> getAllAsteroids(World world) {
		Set<Asteroid> set = new HashSet<Asteroid>();
		Stream<Entity> stream = world.getAllEntities().stream().filter(entity -> (entity instanceof Asteroid));
		stream.forEach(asteroid -> set.add((Asteroid)asteroid));
		return set;
	}
	
}
