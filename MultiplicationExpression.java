package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class MultiplicationExpression extends BinaryDoubleExpression {
	
	public MultiplicationExpression(ProgramExpression left, ProgramExpression right) {
		super(left, right);
		setOperands(left,right);
	}
	
	@Override
	public void setOperands(ProgramExpression left, ProgramExpression right) {
		super.setOperands(left, right);
	}
	
	@Override
	public Double getValue() {
		if (! (getLeftOperand() instanceof DoubleExpression))
			throw new IllegalExpressionException(getLeftOperand());
		if (! (getRightOperand() instanceof DoubleExpression))
			throw new IllegalExpressionException(getRightOperand());
		return (Double)getLeftOperand().getValue()*(Double)getRightOperand().getValue();
	}

}
