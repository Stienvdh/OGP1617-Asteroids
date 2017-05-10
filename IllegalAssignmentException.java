package asteroids.model.programs.exceptions;

import asteroids.model.programs.expressions.ProgramExpression;

/** 
 * A class of exceptions signaling an illegal executionTime of a movement.
 */
public class IllegalAssignmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal executionTime exception with given executionTime.
	 * 
	 * @param 	dt
	 * 			The executionTime for this new illegal executionTime exception.
	 * @post	The executionTime of this new illegal executionTime exception is the given executionTime.
	 * 			| new.getAssignment() == dt
	 * @effect	This new illegal executionTime exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalAssignmentException(ProgramExpression left, ProgramExpression right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Return the executionTime of this illegal executionTime exception.
	 */
	public ProgramExpression[] getAssignment() {
		return new ProgramExpression[]{left,right};
	}

	/**
	 * A variable registering the executionTime of this illegal executionTime exception.
	 */
	private final ProgramExpression right;
	
	private final ProgramExpression left;
}