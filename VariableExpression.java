package asteroids.model.programs.expressions;

public class VariableExpression<S> implements UnaryExpression<S> {
	
	public VariableExpression(String variableName) {
		setVariableName(variableName);
	}

	public void setValue(ProgramExpression<S> value) {
		this.value = value;
	}

	public ProgramExpression<S> getVariableValue() {
		return this.value;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public void setVariableName(String name) {
		this.variableName = name;
	}

	@Override
	public S getValue() {
		return getVariableValue().getValue();
	}

	@Override
	public ProgramExpression<S> getOperand() {
		return getVariableValue();
	}
	
	private ProgramExpression<S> value;
	private String variableName;

}
