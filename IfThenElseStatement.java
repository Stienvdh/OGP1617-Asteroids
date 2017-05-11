package asteroids.model.programs.statements;

import asteroids.model.programs.expressions.BooleanExpression;

public class IfThenElseStatement extends ProgramStatement{

	public IfThenElseStatement(BooleanExpression ifPart, ProgramStatement thenPart, ProgramStatement elsePart) {
	 	setParts(ifPart, thenPart, elsePart);
	 }
	
	public BooleanExpression getIfPart() {
		return this.ifPart;
	}
	
	public ProgramStatement getThenPart() {
		return this.thenPart;
	}
	
	public ProgramStatement getElsePart() {
		return this.elsePart;
	}
	
	public void setParts(BooleanExpression ifPart, ProgramStatement thenPart, ProgramStatement elsePart) {
		this.ifPart = ifPart;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
	
	@Override
	public void execute() {
		if (ifPart.getValue())
			getThenPart().execute();
		else
			if (getElsePart()!=null)
				getElsePart().execute();
	}
	
	private BooleanExpression ifPart;
	private ProgramStatement thenPart;
	private ProgramStatement elsePart;

}
