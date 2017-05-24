package asteroids.model.programs.exceptions;

import asteroids.model.programs.expressions.ProgramExpression;

/** 
 * A class of exceptions signaling an illegal assignment.
 */
public class IllegalAssignmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal assignment exception with given assignment.
	 * 
	 * @param 	left
	 * 			The left side of this new illegal assignment exception.
	 * @param	right 
	 * 			The right side of this new illegal assignment exception.
	 * @post	The assignment of this new illegal assignment exception is the given assignment.
	 * 			| new.getAssignment() == new ProgramExpression[]{left, right}
	 * @effect	This new illegal assignment exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalAssignmentException(ProgramExpression left, ProgramExpression right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Return the assignment of this illegal assignment exception.
	 */
	public ProgramExpression[] getAssignment() {
		return new ProgramExpression[]{left,right};
	}

	/**
	 * A variable registering the right side of the assignment of this illegal assignment exception.
	 */
	private final ProgramExpression right;
	
	/**
	 * A variable registering the left side of the assignment of this illegal assignment exception.
	 */
	private final ProgramExpression left;
}