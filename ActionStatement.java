package asteroids.model.programs.statements;

import asteroids.model.Ship;

public abstract class ActionStatement extends ProgramStatement {

	public abstract void execute();
	
	public Ship getShip() {
		if (getProgram()!=null)
			return getProgram().getShip();
		else
			return null;
	}
	
}
