package asteroids.model.programs.expressions;

public class LessThanExpression extends BinaryBooleanExpression {

	public LessThanExpression(ProgramExpression<Double> leftOperand, ProgramExpression<Double> rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public Boolean getValue() {
		return ((Double)getLeftOperand().getValue() < (Double)getRightOperand().getValue());
	}
	
}
