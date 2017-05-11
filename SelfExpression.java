package asteroids.model.programs.expressions;

import asteroids.model.Ship;

public class SelfExpression extends SimpleEntityExpression {

	public SelfExpression() {
		super(null);
	}
	
	@Override
	public Ship getValue() {
		return getShip();
	}

}
