package asteroids.model.programs.statements;

import java.util.List;

import asteroids.model.programs.Program;
import asteroids.model.programs.exceptions.IllegalStatementException;

public class BlockStatement extends ProgramStatement {
	
	public BlockStatement(List<ProgramStatement> statements) {
		setStatements(statements);
	}
	
	public List<ProgramStatement> getStatements() {
		return this.statements;
	}
	
	public void setStatements(List<ProgramStatement> statements) {
		this.statements = statements;
	}
	
	@Override 
	public void setProgram(Program program) {
		for (ProgramStatement statement: getStatements()) {
			statement.setProgram(program);
		}
	}

	@Override
	public void execute() {
		for (ProgramStatement statement: getStatements()) {
			if (statement instanceof BreakStatement) {
				if (((BreakStatement) statement).getWhileStatement()==null)
					throw new IllegalStatementException(statement);
				break;
			}
			statement.execute();
		}
	}
	
	private List<ProgramStatement> statements;
}