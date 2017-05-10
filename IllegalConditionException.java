package asteroids.model.programs.exceptions;

import asteroids.model.programs.expressions.ProgramExpression;

/** 
 * A class of exceptions signaling an illegal executionTime of a movement.
 */
public class IllegalConditionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal executionTime exception with given executionTime.
	 * 
	 * @param 	dt
	 * 			The executionTime for this new illegal executionTime exception.
	 * @post	The executionTime of this new illegal executionTime exception is the given executionTime.
	 * 			| new.getCondition() == dt
	 * @effect	This new illegal executionTime exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalConditionException(ProgramExpression condition) {
		this.condition=condition;
	}
	
	/**
	 * Return the executionTime of this illegal executionTime exception.
	 */
	public ProgramExpression getCondition() {
		return condition;
	}

	/**
	 * A variable registering the executionTime of this illegal executionTime exception.
	 */
	private final ProgramExpression condition;
}