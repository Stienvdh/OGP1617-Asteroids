package asteroids.model.programs.exceptions;

/** 
 * A class of exceptions signaling an illegal executionTime of a movement.
 */
public class IllegalExecutionTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal executionTime exception with given executionTime.
	 * 
	 * @param 	dt
	 * 			The executionTime for this new illegal executionTime exception.
	 * @post	The executionTime of this new illegal executionTime exception is the given executionTime.
	 * 			| new.getExecutionTime() == dt
	 * @effect	This new illegal executionTime exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalExecutionTimeException(double time) {
		this.executionTime = time;
	}
	
	/**
	 * Return the executionTime of this illegal executionTime exception.
	 */
	public double getExecutionTime() {
		return this.executionTime;
	}

	/**
	 * A variable registering the executionTime of this illegal executionTime exception.
	 */
	private final double executionTime;
}