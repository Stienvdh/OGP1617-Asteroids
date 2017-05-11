package asteroids.model.programs;

import java.util.List;

import asteroids.model.programs.expressions.ProgramExpression;

public abstract class ProgramFunction {
	
	public abstract Object call(List<ProgramExpression> args);

}
