package asteroids.model.programs.expressions;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;

public abstract class BinaryBooleanExpression extends BooleanExpression implements BinaryExpression {
	
	public BinaryBooleanExpression(ProgramExpression leftOperand, ProgramExpression rightOperand) {
		setOperands(leftOperand, rightOperand);
	}

	@Override
	public void setOperands(ProgramExpression left, ProgramExpression right) {
		this.leftOperand = (ProgramExpression) left;
		this.rightOperand = (ProgramExpression) right;
	}

	@Override
	public ProgramExpression getLeftOperand() {
		return leftOperand;
	}

	@Override
	public ProgramExpression getRightOperand() {
		return rightOperand;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getLeftOperand().setProgram(program);
		getRightOperand().setProgram(program);
	}
	
	@Override
	public void setFunction(ProgramFunction function) {
		super.setFunction(function);
		getLeftOperand().setFunction(function);
		getRightOperand().setFunction(function);
	}
	
	private ProgramExpression leftOperand;
	private	ProgramExpression rightOperand;
	
}
