package asteroids.model.programs.expressions;

public class AdditionExpression extends BinaryDoubleExpression {
	
	public AdditionExpression(ProgramExpression<Double> left, ProgramExpression<Double> right) {
		super(left, right);
	}
	
	@Override
	public Double getValue() {
		return ((Double)getLeftOperand().getValue() + (Double)getRightOperand().getValue());
	}

}
