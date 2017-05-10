package asteroids.model.programs.expressions;

public class ChangeSignExpression extends UnaryDoubleExpression {
	
	public ChangeSignExpression(ProgramExpression<Double> operand) {
		super(operand);
	}

	@Override
	public Double getValue() {
		return (-1)*(Double)getOperand().getValue();
	}

}
