package asteroids.model.programs.statements;

import asteroids.model.Ship;
import asteroids.model.programs.ProgramFunction;
import asteroids.model.programs.exceptions.IllegalStatementException;

public abstract class ActionStatement extends ProgramStatement {

	@Override
	public void execute() {
		if (this.getProgram()!=null) {
			this.getProgram().setTimeLeftToExecute(
					this.getProgram().getTimeLeftToExecute()-0.2);
		}
	}
	
	@Override
	public Object execute(ProgramFunction function) {
		throw new IllegalStatementException(this);
	}
	
	public Ship getShip() {
		if (getProgram()!=null)
			return getProgram().getShip();
		else
			return null;
	}
	
}
