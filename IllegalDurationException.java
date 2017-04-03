package asteroids.model;

/** 
 * A class of exceptions signaling an illegal duration of a movement.
 */
public class IllegalDurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal duration exception with given duration.
	 * 
	 * @param 	dt
	 * 			The duration for this new illegal duration exception.
	 * @post	The duration of this new illegal duration exception is the given duration.
	 * 			| new.getDuration() == dt
	 * @effect	This new illegal duration exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalDurationException(double dt) {
		this.duration = dt;
	}
	
	/**
	 * Return the duration of this illegal duration exception.
	 */
	public double getDuration() {
		return this.duration;
	}

	/**
	 * A variable registering the duration of this illegal duration exception.
	 */
	private final double duration;
}