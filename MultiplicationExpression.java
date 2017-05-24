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
		if (! (getLeftOperand().getValue() instanceof Double))
			throw new IllegalExpressionException(getLeftOperand());
		if (! (getRightOperand().getValue() instanceof Double))
			throw new IllegalExpressionException(getRightOperand());
		return (Double)getLeftOperand().getValue()*(Double)getRightOperand().getValue();
	}

}
