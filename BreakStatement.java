package asteroids.model.programs.statements;

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
	}
	
	private WhileStatement whileStatement;

}
