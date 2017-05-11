package asteroids.model.programs.expressions;

public class EqualsExpression extends BinaryBooleanExpression {

	public EqualsExpression(ProgramExpression leftOperand, ProgramExpression rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public Boolean getValue() {
		return (getLeftOperand().getValue()==getRightOperand().getValue());
	}

}
