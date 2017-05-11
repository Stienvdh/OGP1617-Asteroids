package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class AdditionExpression extends BinaryDoubleExpression {
	
	public AdditionExpression(ProgramExpression left, ProgramExpression right) {
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
		return ((DoubleExpression)getLeftOperand()).getValue()+
				((DoubleExpression)getRightOperand()).getValue();
	}

}
