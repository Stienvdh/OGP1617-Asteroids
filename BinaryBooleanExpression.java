package asteroids.model.programs.expressions;

import asteroids.model.programs.Program;

public abstract class BinaryBooleanExpression implements BooleanExpression, BinaryExpression<Boolean> {
	
	public BinaryBooleanExpression(ProgramExpression<?> leftOperand, ProgramExpression<?> rightOperand) {
		setOperands(leftOperand, rightOperand);
	}

	public void setOperands(ProgramExpression<?> left, ProgramExpression<?> right) {
		this.leftOperand = (ProgramExpression<?>) left;
		this.rightOperand = (ProgramExpression<?>) right;
	}
	
	@Override 
	public Program getProgram() {
		return this.program;
	}
	
	@Override 
	public void setProgram(Program program) {
		this.program = program;
	}

	@Override
	public ProgramExpression<?> getLeftOperand() {
		return leftOperand;
	}

	@Override
	public ProgramExpression<?> getRightOperand() {
		return rightOperand;
	}

	private ProgramExpression<?> leftOperand;
	private	ProgramExpression<?> rightOperand;
	
	private Program program = null;
	
}
