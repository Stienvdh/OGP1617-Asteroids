package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class ReadParameterExpression extends ProgramExpression {
	
	public ReadParameterExpression(String parameterName) {
		setName(parameterName);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private String name;

	@Override
	public Object getValue() {
		if (getFunction()==null) {
			throw new IllegalExpressionException(this);
		}
		if (getFunction().getCurrentArgs().containsKey(getName()))
			return getFunction().getCurrentArgs().get(getName());
		else {
			throw new IllegalExpressionException(this);
		}
	}

}
