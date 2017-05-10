package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public class GetYExpression extends UnaryDoubleExpression {

	public GetYExpression(ProgramExpression<? extends Entity> operand) {
		super(operand);
	}

	@Override
	public Double getValue() {
		return ((Entity)getOperand().getValue()).getYPosition();
	}

}
