package asteroids.model.programs.expressions;

import asteroids.model.Ship;

public class GetDirectionExpression extends UnaryDoubleExpression {

	public GetDirectionExpression() {
		super(new SelfExpression());
		getOperand().setProgram(getProgram());
	}

	@Override
	public Double getValue() {
		return ((Ship)getOperand().getValue()).getOrientation();
	}

}
