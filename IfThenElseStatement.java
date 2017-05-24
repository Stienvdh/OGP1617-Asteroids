package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;
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
		thenPart.setParent(this);
		this.elsePart = elsePart;
		if (elsePart!=null)
			elsePart.setParent(this);
	}
	
	@Override
	public void execute() {
		if (! (getIfPart() instanceof BooleanExpression))
			throw new IllegalExpressionException(ifPart);
		if ((boolean) ifPart.getValue()) {
				getProgram().setCurrentStatement(getThenPart());
				getProgram().getCurrentStatement().execute(); 
				}
		else {
			if (getElsePart()!=null) {
				getProgram().setCurrentStatement(getElsePart());
				getElsePart().execute(); }
			}
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
	
	@Override
	public Object execute(ProgramFunction function) {
		getIfPart().setFunction(function);
		if ((boolean) getIfPart().getValue()) {
			return getThenPart().execute(function);
		}
		else
			if (getElsePart()!=null) {
				return getElsePart().execute(function);
			}
		return null;
	}
	
	private ProgramExpression ifPart;
	private ProgramStatement thenPart;
	private ProgramStatement elsePart;

}
