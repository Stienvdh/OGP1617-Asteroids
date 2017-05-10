package asteroids.model.programs.expressions;

public class DoubleLiteralExpression implements DoubleExpression {

	public DoubleLiteralExpression(Double value) {
		setValue(value);
	}

	@Override
	public Double getValue() {
		return this.value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	private Double value;
	
}
