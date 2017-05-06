package asteroids.model.programs;

import asteroids.model.exceptions.IllegalExpressionException;

public class IfThenElseStatement {
	
	public ProgramExpression getIfPart() {
		return this.ifPart;
	}
	
	public void setParts(ProgramExpression ifPart, ProgramExpression thenPart, ProgramExpression elsePart) {
		if (! (ifPart.getValue()==true||ifPart.getValue()==false))
			throw new IllegalExpressionException(ifPart);
		else if (! (thenPart.getValue()==true||thenPart.getValue()==false))
			throw new IllegalExpressionException(thenPart);
		else if (! (elsePart.getValue()==true||elsePart.getValue()==false))
			throw new IllegalExpressionException(elsePart);
		this.ifPart = ifPart;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
	
	public ProgramExpression getThenPart() {
		return this.thenPart;
	}
	
	public ProgramExpression getElsePart() {
		return this.elsePart;
	}
	
	private ProgramExpression ifPart;
	private ProgramExpression thenPart;
	private ProgramExpression elsePart;
	
}
