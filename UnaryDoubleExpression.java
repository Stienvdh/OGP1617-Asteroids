package asteroids.model.programs.expressions;

import asteroids.model.Program;

public abstract class UnaryDoubleExpression extends DoubleExpression implements UnaryExpression {
	
	public UnaryDoubleExpression(ProgramExpression operand) {
		setOperand(operand);
	}
	
	@Override
	public ProgramExpression getOperand() {
		return this.operand;
	}
	
	@Override
	public void setOperand(ProgramExpression operand) {
		this.operand = operand;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getOperand().setProgram(program);
	}
	
	public ProgramExpression operand;

}
