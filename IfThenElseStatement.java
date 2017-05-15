package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.expressions.BooleanExpression;
import asteroids.model.programs.expressions.ProgramExpression;

public class IfThenElseStatement extends ProgramStatement{

	public IfThenElseStatement(ProgramExpression condition, ProgramStatement thenPart, ProgramStatement elsePart) {
	 	setParts(condition, thenPart, elsePart);
	 }
	
	public ProgramExpression getIfPart() {
		return this.ifPart;
	}
	
	public ProgramStatement getThenPart() {
		return this.thenPart;
	}
	
	public ProgramStatement getElsePart() {
		return this.elsePart;
	}
	
	public void setParts(ProgramExpression ifPart, ProgramStatement thenPart, ProgramStatement elsePart) {
		this.ifPart = ifPart;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
	
	@Override
	public void execute() {
		if (! (getIfPart() instanceof BooleanExpression))
			throw new IllegalExpressionException(ifPart);
		if ((boolean) ifPart.getValue())
			getThenPart().execute();
		else
			if (getElsePart()!=null)
				getElsePart().execute();
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getIfPart().setProgram(program);
		getThenPart().setProgram(program);
		if (getElsePart()!=null) {
			getElsePart().setProgram(program);
		}
	}
	
	private ProgramExpression ifPart;
	private ProgramStatement thenPart;
	private ProgramStatement elsePart;

}
