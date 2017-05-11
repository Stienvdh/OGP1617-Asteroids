package asteroids.model.programs.expressions;

import asteroids.model.programs.Program;

public class ReadVariableExpression<S> implements UnaryExpression<S> {
	
	public ReadVariableExpression(String variableName) {
		setVariableName(variableName);
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public void setVariableName(String name) {
		this.variableName = name;
	}

	@SuppressWarnings("unchecked")
	public S getValue() {
		if (getProgram() != null) {
			if (getProgram().getVariableStack().containsKey(getVariableName()))
				return (S) getProgram().getVariableStack().get(getVariableName());
		}
		return null;
	}
	
	@Override 
	public Program getProgram() {
		return this.program;
	}
	
	@Override 
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Program program;
	private String variableName;
	@Override
	public ProgramExpression<?> getOperand() {
		return null;
	}

}
