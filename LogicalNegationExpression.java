package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class LogicalNegationExpression extends UnaryBooleanExpression {
	
	public LogicalNegationExpression(ProgramExpression operand ) {
		super(operand);
	}
	
	@Override
	public void setOperand(ProgramExpression operand) {
		if (! (operand instanceof BooleanExpression)) {
			throw new IllegalExpressionException(operand);
		}
	}

	@Override
	public Boolean getValue() {
		return (! (Boolean)getOperand().getValue());
	}
}
