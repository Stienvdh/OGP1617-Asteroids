package asteroids.model.programs.expressions;

import asteroids.model.Program;

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
	
	private ProgramExpression operand;

}
