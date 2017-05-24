package asteroids.model.programs.exceptions;

import asteroids.model.programs.statements.ProgramStatement;

/** 
 * A class of exceptions signaling an illegal statement.
 */
public class IllegalStatementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal statement exception with given statement.
	 * 
	 * @param 	statement
	 * 			The statement for this new illegal statement exception.
	 * @post	The statement of this new illegal statement exception is the given statement.
	 * 			| new.getStatement() == statement
	 * @effect	This new illegal statement exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalStatementException(ProgramStatement statement) {
		this.statement=statement;
	}
	
	/**
	 * Return the statement of this illegal statement exception.
	 */
	public ProgramStatement getStatement() {
		return statement;
	}

	/**
	 * A variable registering the statement of this illegal statement exception.
	 */
	private final ProgramStatement statement;
}