package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class LessThanExpression extends BinaryBooleanExpression {

	public LessThanExpression(ProgramExpression leftOperand, ProgramExpression rightOperand) {
		super(leftOperand, rightOperand);
		setOperands(leftOperand, rightOperand);
	}
	
	@Override
	public void setOperands(ProgramExpression left, ProgramExpression right) {
		super.setOperands(left, right);
	}

	@Override
	public Boolean getValue() {
		if (! (getLeftOperand().getValue() instanceof Double))
			throw new IllegalExpressionException(getLeftOperand());
		if (! (getRightOperand().getValue() instanceof Double))
			throw new IllegalExpressionException(getRightOperand());
		return ((Double)getLeftOperand().getValue() < (Double)getRightOperand().getValue());
	}
	
}
