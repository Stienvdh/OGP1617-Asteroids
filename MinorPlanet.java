package asteroids.model;

import asteroids.model.exceptions.IllegalRadiusException;

public abstract class MinorPlanet extends Entity {
	
	/**
	 * Create a new minor planet with given position, velocity and radius.
	 * 
	 * @param	xpos
	 * 			The position for this minor planet along the x-axis.
	 * @param	ypos 
	 * 			The position for this minor planet along the y-axis.
	 * @param	xvel
	 * 			The velocity for this minor planet along the x-axis.
	 * @param	yvel
	 * 			The velocity for this minor planet along the y-axis.
	 * @param	radius
	 * 			The radius for this minor planet.
	 * @effect	The new minor planet is initialized as an entity with the given x position,
	 * 			y position, x velocity, y velocity and radius.
	 */
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
		if (radius < MIN_RADIUS)
			throw new IllegalRadiusException(radius);
		this.radius = radius;
	}
	
	/**
	 * Return the mass of the minor planet.
	 */
	
	@Override
	public double getMass() {
		return (4.0/3.0)*Math.PI*Math.pow(getRadius(),3)*getMassDensity();
	}
	
	public abstract void terminate();
	abstract public void collideShip(Ship ship);
	
	/**
	 * A variable registering the minimum radius of a minor planet.
	 */
	protected static double MIN_RADIUS = 5;

}
