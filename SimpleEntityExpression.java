package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public abstract class SimpleEntityExpression<S extends Entity> implements EntityExpression {

	public SimpleEntityExpression(S entity) {
		setValue(entity);
	}

	@Override
	public S getValue() {
		return (S) this.value;
	}
	
	public void setValue(S entity) {
		this.value = (S) entity;
	}
	
	public S value;

}
