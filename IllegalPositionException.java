package asteroids.model;

/** 
 * A class of exceptions signaling an illegal position of a ship.
 */
public class IllegalPositionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal position exception with given position.
	 * 
	 * @param 	xpos
	 * 			The position along the x-axis for this new illegal position exception.
	 * @param 	ypos
	 * 			The position along the y-axis for this new illegal position exception.
	 * @post	The x-position of this new illegal position exception is the given x-position.
	 * 			| new.getXPosition() == xpos
	 * @post	The y-position of this new illegal position exception is the given y-position.
	 * 			| new.getYPosition() == ypos
	 * @effect	This new illegal position exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalPositionException(double xpos, double ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	/**
	 * Return the x-position of this illegal position exception.
	 */
	public double getXPosition() {
		return this.xpos;
	}
	
	/**
	 * Return the y-position of this illegal position exception.
	 */
	public double getYPosition() {
		return this.ypos;
	}
	
	/**
	 * A variable registering the x-position of this illegal position exception.
	 */
	private final double xpos;
	
	/**
	 * A variable registering the y-position of this illegal position exception.
	 */
	private final double ypos;
}