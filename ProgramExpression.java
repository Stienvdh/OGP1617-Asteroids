package asteroids.model.programs.expressions;

import asteroids.model.programs.Program;
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
	
	private Program program;

	public abstract Object getValue();
	
}
