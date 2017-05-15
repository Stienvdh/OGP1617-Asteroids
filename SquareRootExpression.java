package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class SquareRootExpression extends UnaryDoubleExpression {

	public SquareRootExpression(ProgramExpression operand) {
		super(operand);
		setOperand(operand);
	}
	
	@Override
	public void setOperand(ProgramExpression operand) {
		super.setOperand(operand);
	}
	
	@Override
	public Double getValue() {
		if (! (getOperand() instanceof DoubleExpression)) 
			throw new IllegalExpressionException(getOperand());
		if (! (((DoubleExpression)getOperand()).getValue()>0))
			throw new IllegalExpressionException(getOperand());
		else 
			return Math.sqrt(((DoubleExpression)getOperand()).getValue());
	}
	
}
