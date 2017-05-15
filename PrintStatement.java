package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.expressions.ProgramExpression;

public class PrintStatement extends ProgramStatement {

	public PrintStatement(ProgramExpression value) {
		setExpression(value);
	}
	
	public ProgramExpression getExpression() {
		return this.value;
	}
	
	public void setExpression(ProgramExpression expression) {
		this.value = expression;
	}
	
	@Override
	public void execute() {
		if (getExpression().getValue()==null) {
			System.out.print("null");
		}
		else
			System.out.print(getExpression().getValue().toString());
		if (getProgram()!=null) {
			getProgram().addToPrintStack(getExpression().getValue());
		}
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getExpression().setProgram(program);
	}
	
	private ProgramExpression value;

}
