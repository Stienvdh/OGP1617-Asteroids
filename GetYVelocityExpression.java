package asteroids.model.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.programs.exceptions.IllegalExpressionException;

public class GetYVelocityExpression extends UnaryDoubleExpression {

	public GetYVelocityExpression(ProgramExpression operand) {
		super(operand);
		setOperand(operand);
	}
	
	@Override
	public void setOperand(ProgramExpression operand) {
		if (! (operand instanceof EntityExpression))
			throw new IllegalExpressionException(operand);
		super.setOperand(operand);
	}

	@Override
	public Double getValue() {
		return ((Entity)getOperand().getValue()).getYVelocity();
	}

}
