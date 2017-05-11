package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class SquareRootExpression extends UnaryDoubleExpression {

	public SquareRootExpression(DoubleExpression operand) {
		super(operand);
		setOperand(operand);
		
	}
	
	@Override
	public void setOperand(ProgramExpression operand) {
		if (! (operand instanceof DoubleExpression)) 
			throw new IllegalExpressionException(operand);
		super.setOperand(operand);
	}
	
	@Override
	public Double getValue() {
		if (((DoubleExpression)getOperand()).getValue()>0)
			throw new IllegalExpressionException(getOperand());
		else 
			return Math.sqrt(((DoubleExpression)getOperand()).getValue());
	}

}
