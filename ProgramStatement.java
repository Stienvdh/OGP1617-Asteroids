package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.ProgramLine;

public abstract class ProgramStatement implements ProgramLine {
	
	public Program getProgram() {
		return this.program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public abstract void execute();
	
	private Program program;
	
}
