package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public class GetYVelocityExpression extends UnaryDoubleExpression {

	public GetYVelocityExpression(ProgramExpression<? extends Entity> operand) {
		super(operand);
	}

	@Override
	public Double getValue() {
		return ((Entity)getOperand().getValue()).getYVelocity();
	}

}
