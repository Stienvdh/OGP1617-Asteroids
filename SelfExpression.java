package asteroids.model.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.programs.Program;

public class SelfExpression extends UnaryEntityExpression {

	@SuppressWarnings("all")
	public SelfExpression() {
		super(new SimpleEntityExpression(null));
		Ship ship = getShip();
		super.setOperand((ProgramExpression<? extends Entity>) ship);
	}

	@Override
	public Entity getValue() {
		return getOperand().getValue();
	}
	
	@Override 
	public Program getProgram() {
		return this.program;
	}
	
	@Override 
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Program program;

}
