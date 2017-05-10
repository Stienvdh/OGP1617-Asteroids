package asteroids.model.programs.expressions;

import asteroids.model.Ship;

public class GetDirectionExpression extends UnaryDoubleExpression {

	public GetDirectionExpression(ProgramExpression<Ship> operand) {
		super(operand);
	}

	@Override
	public Double getValue() {
		return ((Ship)getOperand().getValue()).getOrientation();
	}

}
