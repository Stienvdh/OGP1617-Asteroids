package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public class GetXVelocityExpression extends UnaryDoubleExpression {

	public GetXVelocityExpression(ProgramExpression<? extends Entity> operand) {
		super(operand);
	}

	@Override
	public Double getValue() {
		return ((Entity)getOperand().getValue()).getXVelocity();
	}

}
