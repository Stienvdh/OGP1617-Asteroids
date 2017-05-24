package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;
import asteroids.model.programs.exceptions.IllegalStatementException;
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
	
	public Object execute(ProgramFunction function) {
		getResult().setFunction(function);
		return getResult().getValue();
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
		getResult().setProgram(program);
	}

	private ProgramExpression result;

	@Override
	public void execute() {
		throw new IllegalStatementException(this);
	}
	
}
