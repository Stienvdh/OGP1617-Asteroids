package asteroids.model.programs.statements;

import asteroids.model.programs.ProgramFunction;
import asteroids.model.programs.exceptions.IllegalStatementException;

public class BreakStatement extends ProgramStatement {

	@Override
	public void execute() {
		if (getWhileParent()==null) {
			throw new IllegalStatementException(this);
		}
	}

	@Override
	public Object execute(ProgramFunction function) {
		if (getWhileParent()==null) {
			if (getProgram().getCurrentStatement().getWhileParent()==null)
				throw new IllegalStatementException(this);
			else {
				
				getProgram().setCurrentStatement(
						getProgram().getCurrentStatement().getNext());
				getProgram().getCurrentStatement().execute();
			}
		}
		else {
			
		}
		return null;
	}
	
}
