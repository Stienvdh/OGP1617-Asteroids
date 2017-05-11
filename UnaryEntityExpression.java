package asteroids.model.programs.expressions;

public abstract class UnaryEntityExpression extends EntityExpression implements UnaryExpression{
	
	public UnaryEntityExpression(EntityExpression operand) {
		setOperand(operand);
	}

	@Override
	public ProgramExpression getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression operand) {
		this.operand = operand;
	}
	
	private ProgramExpression operand;

}
