package asteroids.model.programs.expressions;

public abstract class UnaryDoubleExpression extends DoubleExpression implements UnaryExpression {
	
	public UnaryDoubleExpression(ProgramExpression operand) {
		setOperand(operand);
	}
	
	@Override
	public ProgramExpression getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression operand) {
		this.operand = operand;
	}
	
	public ProgramExpression operand;

}
