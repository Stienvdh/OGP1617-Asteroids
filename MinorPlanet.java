package asteroids.model;

import asteroids.model.exceptions.IllegalRadiusException;

public abstract class MinorPlanet extends Entity {

	public MinorPlanet(double xpos, double ypos, double xvel, double yvel, double radius) {
		super(xpos, ypos, xvel, yvel, radius);
	}

	/**
	 * Set the radius of this minor planet to a given radius.
	 * 
	 * @param	radius
	 * 			The new radius of this bullet.
	 * @post	The new radius of this bullet is the given radius.
	 * 			| new.getRadius() == radius
	 * @throws	IllegalRadiusException
	 * 			The given radius does not exceed its minimum value.
	 * 			| radius < MIN_RADIUS
	 */
	public void setRadius(double radius) {
		if (radius <= MIN_RADIUS)
			throw new IllegalRadiusException(radius);
		this.radius = radius;
	}

	@Override
	public double getMass() {
		return 4/3*Math.PI*Math.pow(getRadius(),3)*getMassDensity();
	}

	@Override
	public void terminate() {
		this.isTerminated=true;
		this.getWorld().removeEntity(this);
		this.setWorld(null);
	}
	
	/**
	 * A variable registering the minimum radius of a minor planet.
	 */
	protected static double MIN_RADIUS = 5;

}
