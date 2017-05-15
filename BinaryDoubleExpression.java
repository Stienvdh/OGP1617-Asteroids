package asteroids.model.programs.expressions;

import asteroids.model.Program;

public abstract class BinaryDoubleExpression extends DoubleExpression implements BinaryExpression {
	
	public BinaryDoubleExpression(ProgramExpression leftOperand, ProgramExpression rightOperand) {
		setOperands(leftOperand, rightOperand);
	}

	public void setOperands(ProgramExpression left, ProgramExpression right) {
		this.leftOperand = (ProgramExpression) left;
		this.rightOperand = (ProgramExpression) right;
	}

	@Override
	public ProgramExpression getLeftOperand() {
		return this.leftOperand;
	}

	@Override
	public ProgramExpression getRightOperand() {
		return this.rightOperand;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getLeftOperand().setProgram(program);
		getRightOperand().setProgram(program);
	}
	
	private ProgramExpression leftOperand;
	private ProgramExpression rightOperand;

}
