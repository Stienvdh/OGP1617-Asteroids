package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class LessThanExpression extends BinaryBooleanExpression {

	public LessThanExpression(ProgramExpression leftOperand, ProgramExpression rightOperand) {
		super(leftOperand, rightOperand);
		setOperands(leftOperand, rightOperand);
	}
	
	@Override
	public void setOperands(ProgramExpression left, ProgramExpression right) {
		if (! (left instanceof DoubleExpression))
			throw new IllegalExpressionException(left);
		if (! (right instanceof DoubleExpression))
			throw new IllegalExpressionException(right);
		super.setOperands(left, right);
	}

	@Override
	public Boolean getValue() {
		return ((Double)getLeftOperand().getValue() < (Double)getRightOperand().getValue());
	}
	
}
