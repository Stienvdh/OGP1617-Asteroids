package asteroids.model.programs.expressions;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;

public abstract class UnaryBooleanExpression extends BooleanExpression implements UnaryExpression {
	
	public UnaryBooleanExpression(ProgramExpression operand) {
		setOperand(operand);
	}
	
	public ProgramExpression getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression operand) {
		this.operand = operand;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getOperand().setProgram(program);
	}
	
	@Override
	public void setFunction(ProgramFunction function) {
		super.setFunction(function);
		getOperand().setFunction(function);
	}
	
	public ProgramExpression operand;

}
