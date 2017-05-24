package asteroids.model.exceptions;

/** 
 * A class of exceptions signaling an illegal orientation.
 */
public class IllegalOrientationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal orientation exception with given orientation.
	 * 
	 * @param 	orientation
	 * 			The orientation for this new illegal orientation exception.
	 * @post	The orientation of this new illegal orientation exception is the given orientation.
	 * 			| new.getOrientation() == orientation
	 * @effect	This new illegal orientation exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalOrientationException(double orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Return the orientation of this illegal orientation exception.
	 */
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * A variable registering the orientation of this illegal orientation exception.
	 */
	private final double orientation;
}