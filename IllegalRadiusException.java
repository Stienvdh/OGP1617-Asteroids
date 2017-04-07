package asteroids.model;

/** 
 * A class of exceptions signaling an illegal radius of a ship.
 */
public class IllegalRadiusException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal radius exception with given radius.
	 * 
	 * @param 	radius
	 * 			The radius for this new illegal radius exception.
	 * @post	The radius of this new illegal radius exception is the given radius.
	 * 			| new.getRadius() == radius
	 * @effect	This new illegal radius exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalRadiusException(double radius) {
		this.radius = radius;
	}
	
	/**
	 * Return the radius of this illegal radius exception.
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * A variable registering the radius of this illegal radius exception.
	 */
	private final double radius;
}
