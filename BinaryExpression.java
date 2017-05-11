package asteroids.model.programs.expressions;

public interface BinaryExpression {
	
	public ProgramExpression getLeftOperand();
	public ProgramExpression getRightOperand();
	public void setOperands(ProgramExpression left, ProgramExpression right);
	
}
