package asteroids.model.programs.expressions;

public abstract class UnaryBooleanExpression implements UnaryExpression<Boolean>, BooleanExpression {
	
	public UnaryBooleanExpression(ProgramExpression<?> operand) {
		setOperand(operand);
	}
	
	public ProgramExpression<?> getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression<?> operand) {
		this.operand = operand;
	}
	
	public ProgramExpression<?> operand;

}
