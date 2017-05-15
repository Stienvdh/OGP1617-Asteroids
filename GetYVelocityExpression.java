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
		super.setOperand(operand);
	}

	@Override
	public Double getValue() {
		if ((! (getOperand() instanceof EntityExpression))||(getOperand().getValue()==null))
			throw new IllegalExpressionException(getOperand());
		return ((Entity)getOperand().getValue()).getYVelocity();
	}

}
