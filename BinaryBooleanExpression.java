package asteroids.model.programs.expressions;

public abstract class BinaryBooleanExpression implements BooleanExpression, BinaryExpression<Boolean> {
	
	public BinaryBooleanExpression(ProgramExpression<?> leftOperand, ProgramExpression<?> rightOperand) {
		setOperands(leftOperand, rightOperand);
	}

	public void setOperands(ProgramExpression<?> left, ProgramExpression<?> right) {
		this.leftOperand = (ProgramExpression<?>) left;
		this.rightOperand = (ProgramExpression<?>) right;
	}

	@Override
	public ProgramExpression<?> getLeftOperand() {
		return leftOperand;
	}

	@Override
	public ProgramExpression<?> getRightOperand() {
		return rightOperand;
	}

	private ProgramExpression<?> leftOperand;
	private	ProgramExpression<?> rightOperand;
	
}
