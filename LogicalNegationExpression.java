package asteroids.model.programs.expressions;

public class LogicalNegationExpression extends UnaryBooleanExpression {
	
	public LogicalNegationExpression(ProgramExpression<Boolean> operand ) {
		super(operand);
	}

	@Override
	public Boolean getValue() {
		return (! (Boolean)getOperand().getValue());
	}
}
