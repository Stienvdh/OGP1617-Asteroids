package asteroids.model.programs.expressions;

public class ReadVariableExpression extends ProgramExpression {
	
	public ReadVariableExpression(String variableName) {
		setVariableName(variableName);
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public void setVariableName(String name) {
		this.variableName = name;
	}

	public Object getValue() {
		if (getProgram() != null) {
			if (getProgram().getVariableStack().containsKey(getVariableName()))
				return getProgram().getVariableStack().get(getVariableName()).getValue();;
		}
		return null;
	}
	 
	private String variableName;

}
