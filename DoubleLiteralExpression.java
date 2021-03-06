package asteroids.model.programs.expressions;

import asteroids.model.Program;

public class DoubleLiteralExpression extends DoubleExpression {

	public DoubleLiteralExpression(Double value) {
		setValue(value);
	}

	@Override
	public Double getValue() {
		return this.value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override 
	public Program getProgram() {
		return this.program;
	}
	
	@Override 
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Double value;
	private Program program;
	
}
