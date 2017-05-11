package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class MultiplicationExpression extends BinaryDoubleExpression {
	
	public MultiplicationExpression(ProgramExpression left, ProgramExpression right) {
		super(left, right);
		setOperands(left,right);
	}
	
	@Override
	public void setOperands(ProgramExpression left, ProgramExpression right) {
		if (! (left instanceof DoubleExpression))
			throw new IllegalExpressionException(left);
		if (! (right instanceof DoubleExpression))
			throw new IllegalExpressionException(left);
		super.setOperands(left, right);
	}
	
	@Override
	public Double getValue() {
		return (Double)getLeftOperand().getValue()*(Double)getRightOperand().getValue();
	}

}
