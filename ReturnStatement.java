package asteroids.model.programs.statements;

import asteroids.model.programs.ProgramFunction;
import asteroids.model.programs.expressions.ProgramExpression;

public class ReturnStatement extends ProgramStatement {

	public ReturnStatement(ProgramExpression result) {
		setResult(result);
	}
	
	public ProgramExpression getResult() {
		return this.result;
	}

	public void setResult(ProgramExpression result) {
		this.result = result;
	}

	@Override
	public void execute() {
	}
	
	public ProgramFunction getFunction() {
		return this.function;
	}
	
	public void setFunction(ProgramFunction function) {
		this.function = function;
	}

	private ProgramExpression result;
	private ProgramFunction function;
	
}
