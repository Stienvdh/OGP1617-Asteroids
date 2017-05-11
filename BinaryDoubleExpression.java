package asteroids.model.programs.expressions;

public abstract class BinaryDoubleExpression extends DoubleExpression implements BinaryExpression {
	
	public BinaryDoubleExpression(ProgramExpression leftOperand, ProgramExpression rightOperand) {
		setOperands(leftOperand, rightOperand);
	}

	public void setOperands(ProgramExpression left, ProgramExpression right) {
		this.leftOperand = (ProgramExpression) left;
		this.rightOperand = (ProgramExpression) right;
	}

	@Override
	public ProgramExpression getLeftOperand() {
		return this.leftOperand;
	}

	@Override
	public ProgramExpression getRightOperand() {
		return this.rightOperand;
	}
	
	private ProgramExpression leftOperand;
	private ProgramExpression rightOperand;

}
