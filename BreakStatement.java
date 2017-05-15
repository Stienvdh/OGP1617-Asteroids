package asteroids.model.programs.statements;

import asteroids.model.programs.exceptions.IllegalStatementException;

public class BreakStatement extends ProgramStatement {
	
	public BreakStatement() {
	}
	
	public WhileStatement getWhileStatement() {
		return this.whileStatement;
	}
	
	public void setWhileStatement(WhileStatement whileStatement) {
		this.whileStatement = whileStatement;
	}

	@Override
	public void execute() {
		if (getWhileStatement()==null)
			throw new IllegalStatementException(this);
	}
	
	private WhileStatement whileStatement;

}
