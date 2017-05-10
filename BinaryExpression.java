package asteroids.model.programs.expressions;

public interface BinaryExpression<S> extends ProgramExpression<S> {
	
	public ProgramExpression<?> getLeftOperand();
	public ProgramExpression<?> getRightOperand();
	
}
