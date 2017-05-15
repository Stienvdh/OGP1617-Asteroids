package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class LogicalNegationExpression extends UnaryBooleanExpression {
	
	public LogicalNegationExpression(ProgramExpression operand ) {
		super(operand);
	}
	
	@Override
	public void setOperand(ProgramExpression operand) {
		super.setOperand(operand);
	}

	@Override
	public Boolean getValue() {
		if (! (getOperand() instanceof BooleanExpression)) 
			throw new IllegalExpressionException(getOperand());
		return (! (Boolean)getOperand().getValue());
	}
}
