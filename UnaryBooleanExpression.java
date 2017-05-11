package asteroids.model.programs.expressions;

import asteroids.model.programs.Program;

public abstract class UnaryBooleanExpression implements UnaryExpression<Boolean>, BooleanExpression {
	
	public UnaryBooleanExpression(ProgramExpression<?> operand) {
		setOperand(operand);
	}
	
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
