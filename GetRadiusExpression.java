package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public class GetRadiusExpression extends UnaryDoubleExpression {

	public GetRadiusExpression(ProgramExpression<? extends Entity> operand) {
		super(operand);
	}

	@Override
	public Double getValue() {
		return ((Entity)getOperand().getValue()).getRadius();
	}

}
