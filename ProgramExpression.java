package asteroids.model.programs.expressions;

import asteroids.model.programs.ProgramLine;

public interface ProgramExpression<S> extends ProgramLine {
	
	public S getValue();
	
}
