package asteroids.model.programs.expressions;

import asteroids.model.programs.Program;

public abstract class UnaryDoubleExpression implements UnaryExpression<Double>, DoubleExpression{
	
	public UnaryDoubleExpression(ProgramExpression<?> operand) {
		setOperand(operand);
	}
	
	@Override
	public ProgramExpression<?> getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression<?> operand) {
		this.operand = operand;
	}
	
	@Override 
	public Program getProgram() {
		return this.program;
	}
	
	@Override 
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public ProgramExpression<?> operand;
	private Program program;

}
