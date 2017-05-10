package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public class GetXExpression extends UnaryDoubleExpression {

	public GetXExpression(ProgramExpression<? extends Entity> operand) {
		super(operand);
	}

	@Override
	public Double getValue() {
		return ((Entity)getOperand().getValue()).getXPosition();
	}
	

}
