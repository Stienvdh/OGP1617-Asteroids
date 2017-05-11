package asteroids.model.programs.expressions;

import asteroids.model.programs.Program;

public abstract class BinaryDoubleExpression implements DoubleExpression, BinaryExpression<Double> {
	
	public BinaryDoubleExpression(ProgramExpression<?> leftOperand, ProgramExpression<?> rightOperand) {
		setOperands(leftOperand, rightOperand);
	}

	public void setOperands(ProgramExpression<?> left, ProgramExpression<?> right) {
		this.leftOperand = (ProgramExpression<?>) left;
		this.rightOperand = (ProgramExpression<?>) right;
	}

	@Override
	public ProgramExpression<?> getLeftOperand() {
		return this.leftOperand;
	}

	@Override
	public ProgramExpression<?> getRightOperand() {
		return this.rightOperand;
	}
	
	@Override 
	public Program getProgram() {
		return this.program;
	}
	
	@Override 
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private ProgramExpression<?> leftOperand;
	private ProgramExpression<?> rightOperand;
	private Program program;

}
