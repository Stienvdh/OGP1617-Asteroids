package asteroids.model;

public class Asteroid extends MinorPlanet {
	
	/**
	 * Create a new asteroid with given position, velocity and radius.
	 * 
	 * @param	xpos
	 * 			The position for this asteroid along the x-axis.
	 * @param	ypos 
	 * 			The position for this asteroid along the y-axis.
	 * @param	xvel
	 * 			The velocity for this asteroid along the x-axis.
	 * @param	yvel
	 * 			The velocity for this asteroid along the y-axis.
	 * @param	radius
	 * 			The radius for this asteroid.
	 * @post	The mass density of this new asteroid is the standard density.
	 * 			|new.getMassDensity() == DENSITY
	 * @effect	The new asteroid is initialized as an minor planet with the given x position,
	 * 			y position, x velocity, y velocity and radius.
	 */
	public Asteroid(double xpos, double ypos, double xvel, double yvel, double radius) {
		super(xpos,ypos,xvel,yvel,radius);
		setMassDensity(DENSITY);
	}
	
	/**
	 * Set the mass density of this asteroid to the given mass density.
	 */
	@Override
	public void setMassDensity(double massDensity) {
		this.massDensity=DENSITY;
	}
	
	/**
	 * Terminate the given ship on collision with this asteroid.
	 * 
	 * @effect	Terminate the given ship.
	 * 			|ship.isTerminated() == True
	 */
	public void collideShip(Ship ship) {
		ship.terminate();
	}
	
	/**
	 * Terminate this asteroid.
	 * 
	 * @post	The new state of this asteroid is terminated, it is removed from it's world and
	 * 			its world is set to null.
	 * 			|new.isTerminated() == true
	 * 			|old.getWorld().contains(this) == false
	 * 			|new.getWorld() == null
	 */
	public void terminate() {
		this.isTerminated=true;
		this.getWorld().removeEntity(this);
		this.setWorld(null);
	}

	/**
	 * A variable registering the minimum mass density of this asteroid.
	 */
	protected static double DENSITY = 2.65*Math.pow(10, 12);
	
}
