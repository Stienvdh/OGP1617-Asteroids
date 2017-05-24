package asteroids.model.programs.expressions;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.model.programs.ProgramFunction;
import asteroids.model.programs.ProgramLine;

public abstract class ProgramExpression implements ProgramLine {
	
	@Override
	public Program getProgram() {
		return this.program;
	}
	
	@Override
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public Ship getShip() {
		if (getProgram()!=null)
			return getProgram().getShip();
		else
			return null;
	}
	
	public ProgramFunction getFunction() {
		return this.function;
	}
	
	public void setFunction(ProgramFunction function) {
		this.function = function;
	}
	
	public abstract Object getValue();
	
	private Program program;
	private ProgramFunction function;
	
}
