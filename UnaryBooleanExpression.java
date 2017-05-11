package asteroids.model.programs.expressions;

public abstract class UnaryBooleanExpression extends BooleanExpression implements UnaryExpression {
	
	public UnaryBooleanExpression(ProgramExpression operand) {
		setOperand(operand);
	}
	
	public ProgramExpression getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression operand) {
		this.operand = operand;
	}
	
	public ProgramExpression operand;

}
