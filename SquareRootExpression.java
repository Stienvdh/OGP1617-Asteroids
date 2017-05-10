package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class SquareRootExpression extends UnaryDoubleExpression {

	public SquareRootExpression(ProgramExpression<Double> operand) {
		super(operand);
	}
	
	@Override
	public Double getValue() {
		if ((Double)getOperand().getValue()>0)
			throw new IllegalExpressionException(getOperand());
		else 
			return Math.sqrt((Double)getOperand().getValue());
	}

}
