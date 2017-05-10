package asteroids.model.programs.expressions;

public interface UnaryExpression<S> extends ProgramExpression<S>{

	public ProgramExpression<?> getOperand();
	
}
