package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class ChangeSignExpression extends UnaryDoubleExpression {
	
	public ChangeSignExpression(ProgramExpression operand) {
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
		return (-1)*((DoubleExpression)getOperand()).getValue();
	}

}
