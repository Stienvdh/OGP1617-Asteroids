package asteroids.model.programs.expressions;

import asteroids.model.programs.exceptions.IllegalExpressionException;

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
		if (getFunction()!=null)
			if (getFunction().getVariableStack().containsKey(getVariableName())) {
				return getFunction().getVariableStack().get(getVariableName());
			}
		if (getProgram()!=null) {
			if (getProgram().getVariableStack().containsKey(getVariableName()))
				return getProgram().getVariableStack().get(getVariableName());
			else
				throw new IllegalExpressionException(this);
		}
		return null;
	}
	 
	private String variableName;

}
