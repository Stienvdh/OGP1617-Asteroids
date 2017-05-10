package asteroids.model.programs.expressions;

public class MultiplicationExpression extends BinaryDoubleExpression {
	
	public MultiplicationExpression(ProgramExpression<Double> left, ProgramExpression<Double> right) {
		super(left, right);
	}
	
	@Override
	public Double getValue() {
		return (Double)getLeftOperand().getValue()*(Double)getRightOperand().getValue();
	}

}
