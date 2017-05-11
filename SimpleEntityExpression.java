package asteroids.model.programs.expressions;

import asteroids.model.Entity;

public class SimpleEntityExpression extends EntityExpression {

	public SimpleEntityExpression(Entity entity) {
		setValue(entity);
	}

	@Override
	public Entity getValue() {
		return this.value;
	}
	
	public void setValue(Entity entity) {
		this.value = entity;
	}
	
	public Entity value;

}
