package asteroids.model.programs.statements;

import java.util.List;

public class SequenceStatement extends ProgramStatement{

	public SequenceStatement(List<ProgramStatement> statements) {
		setStatements(statements);
	}
	
	public void setStatements(List<ProgramStatement> statements) {
		this.statements = statements;
	}
	
	public void execute() {
		for (ProgramStatement statement: statements) {
			statement.execute();
		}
	}
	
	private List<ProgramStatement> statements;
}