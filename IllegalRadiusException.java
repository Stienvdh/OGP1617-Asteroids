package asteroids.model;

/** 
 * A class of exceptions signaling an illegal radius of a ship.
 */
public class IllegalRadiusException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal radius exception with given radius and ship.
	 * 
	 * @param 	radius
	 * 			The radius for this new illegal radius exception.
	 * @param 	ship
	 * 			The ship for this new illegal radius exception.
	 * @post	The radius of this new illegal radius exception is the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The ship of this new illegal radius exception is the given ship.
	 * 			| new.getShip() == ship
	 * @effect	This new illegal radius exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalRadiusException(double radius, Ship ship) {
		this.radius = radius;
		this.ship = ship;
	}
	
	/**
	 * Return the radius of this illegal radius exception.
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Return the ship of this illegal radius exception.
	 */
	public Ship getShip() {
		return this.ship;
	}
	
	/**
	 * A variable registering the radius of this illegal radius exception.
	 */
	private final double radius;
	
	
	/**
	 * A variable registering the ship of this illegal radius exception.
	 */
	private final Ship ship;
}
