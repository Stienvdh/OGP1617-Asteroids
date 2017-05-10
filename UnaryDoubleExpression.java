package asteroids.model.programs.expressions;

public abstract class UnaryDoubleExpression implements UnaryExpression<Double>, DoubleExpression{

	public UnaryDoubleExpression(ProgramExpression<?> operand) {
		setOperand(operand);
	}
	
	@Override
	public ProgramExpression<?> getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression<?> operand) {
		this.operand = operand;
	}
	
	public ProgramExpression<?> operand;

}
