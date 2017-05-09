package asteroids.model.programs;

import asteroids.model.exceptions.IllegalExpressionException;

public class IfThenElseStatement extends ProgramStatement{
	
	public IfThenElseStatement(ProgramExpression ifPart, ProgramStatement thenPart, ProgramStatement elsePart) {
		setParts(ifPart, thenPart, elsePart);
	}
	
	public ProgramExpression getIfPart() {
		return this.ifPart;
	}
	
	public void setParts(ProgramExpression ifPart, ProgramStatement thenPart, ProgramStatement elsePart) {
		if (! (ifPart.getValue().getClass() == boolean.class))
			throw new IllegalExpressionException(ifPart);
//		else if (! (thenPart.getValue()==true||thenPart.getValue()==false))
//			throw new IllegalExpressionException(thenPart);
//		else if (! (elsePart.getValue()==true||elsePart.getValue()==false))
//			throw new IllegalExpressionException(elsePart);
		this.ifPart = ifPart;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
	
	public ProgramStatement getThenPart() {
		return this.thenPart;
	}
	
	public ProgramStatement getElsePart() {
		return this.elsePart;
	}
	
	public void execute() {
		if (ifPart.getValue() == true)
			getThenPart().execute();
		else
			getElsePart().execute();
	}
	
	private ProgramExpression ifPart;
	private ProgramStatement thenPart;
	private ProgramStatement elsePart;
	
}
