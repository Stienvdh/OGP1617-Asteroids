package asteroids.model.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.programs.Program;

public class SimpleEntityExpression<S extends Entity> implements EntityExpression {

	public SimpleEntityExpression(S entity) {
		setValue(entity);
	}

	@Override
	public S getValue() {
		return (S) this.value;
	}
	
	public void setValue(S entity) {
		this.value = (S) entity;
	}
	
	@Override 
	public Program getProgram() {
		return this.program;
	}
	
	@Override 
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public S value;
	private Program program;

}
