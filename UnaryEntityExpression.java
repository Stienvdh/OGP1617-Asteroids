package asteroids.model.programs.expressions;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;

public abstract class UnaryEntityExpression extends EntityExpression implements UnaryExpression{
	
	public UnaryEntityExpression(EntityExpression operand) {
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
	
	@Override
	public void setFunction(ProgramFunction function) {
		super.setFunction(function);
		getOperand().setFunction(function);
	}
	
	private ProgramExpression operand;

}
