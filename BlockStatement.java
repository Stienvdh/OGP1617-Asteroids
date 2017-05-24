package asteroids.model.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;

public class BlockStatement extends ProgramStatement {
	
	public BlockStatement(List<ProgramStatement> statements) {
		setStatements(statements);
		int index = 0;
		for (ProgramStatement statement: statements) {
			statement.setParent(this);
			statement.setBlockIndex(index);
			index += 1;
		}
	}
	
	public List<ProgramStatement> getStatements() {
		return this.statements;
	}
	
	public void setStatements(List<ProgramStatement> statements) {
		this.statements = statements;
	}
	
	@Override 
	public void setProgram(Program program) {
		super.setProgram(program);
		for (ProgramStatement statement: getStatements()) {
			statement.setProgram(program);
		}
	}
	
	@Override
	public void execute() {
		getProgram().setCurrentStatement(getStatements().get(0));
		getProgram().getCurrentStatement().execute();
	}
	
	private List<ProgramStatement> statements;

	@Override
	public Object execute(ProgramFunction function) {
		for (ProgramStatement statement: getStatements()) {
			Object result = statement.execute(function);
			if (result!=null) {
				return result;
			}
		}
		return null;
	}
}
