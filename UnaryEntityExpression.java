package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public abstract class UnaryEntityExpression implements UnaryExpression<Entity>, EntityExpression {
	
	public UnaryEntityExpression(ProgramExpression<? extends Entity> operand) {
		setOperand(operand);
	}

	@Override
	public ProgramExpression<? extends Entity> getOperand() {
		return this.operand;
	}
	
	public void setOperand(ProgramExpression<? extends Entity> operand) {
		this.operand = operand;
	}
	
	private ProgramExpression<? extends Entity> operand;

}
