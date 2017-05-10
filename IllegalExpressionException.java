package asteroids.model.programs.exceptions;

import asteroids.model.programs.expressions.ProgramExpression;

/** 
 * A class of exceptions signaling an illegal expression of a movement.
 */
public class IllegalExpressionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal expression exception with given expression.
	 * 
	 * @param 	dt
	 * 			The expression for this new illegal expression exception.
	 * @post	The expression of this new illegal expression exception is the given expression.
	 * 			| new.getExpression() == dt
	 * @effect	This new illegal expression exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalExpressionException(ProgramExpression dt) {
		this.expression = dt;
	}
	
	/**
	 * Return the expression of this illegal expression exception.
	 */
	public ProgramExpression getExpression() {
		return this.expression;
	}

	/**
	 * A variable registering the expression of this illegal expression exception.
	 */
	private final ProgramExpression expression;
}