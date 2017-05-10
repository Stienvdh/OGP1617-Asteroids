package asteroids.model.programs.expressions;

public class EqualsExpression extends BinaryBooleanExpression {

	public <S> EqualsExpression(ProgramExpression<S> leftOperand, ProgramExpression<S> rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public Boolean getValue() {
		return (getLeftOperand().getValue()==getRightOperand().getValue());
	}

}
