package asteroids.model;

import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;

/**
 * A class of asteroids, involving a position, velocity and radius.
 */
public class Asteroid extends MinorPlanet {
	
	/**
	 * Initialize this asteroid with given position, velocity and radius.
	 * 
	 * @post	The density of this asteroid is set to DENSITY.
	 * 			| new.getMassDensity() == DENSITY
	 */
	public Asteroid(double xpos, double ypos, double xvel, double yvel, double radius) 
		throws IllegalPositionException, IllegalRadiusException {
		super(xpos,ypos,xvel,yvel,radius);
		setMassDensity(DENSITY);
	}
	
	/**
	 * Return whether the given density is valid for this asteroid.
	 * 
	 * @result	True if and only if density equals DENSITY.
	 * 			| result == (density == DENSITY)
	 */
	@Override
	public boolean isValidDensity(double density) {
		return (density == DENSITY);
	}

	/**
	 * Resolve a collision of this asteroid with a given ship.
	 * 
	 * @effect	The given ship is terminated.
	 * 			| ship.terminate()
	 */
	@Override
	public void collideShip(Ship ship) {
		ship.terminate();
	}
	
	
	/**
	 * Terminate this asteroid.
	 * 
	 * @post	This asteroid is no longer associated with a world.
	 * 			| @see implementation
	 */
	@Override
	public void terminate() {
		super.terminate();
		this.getWorld().removeEntity(this);
		this.setWorld(null);
	}

	/**
	 * A variable registering the minimum mass density of this asteroid.
	 */
	private static final double DENSITY = 2.65*Math.pow(10, 12);
	
}
