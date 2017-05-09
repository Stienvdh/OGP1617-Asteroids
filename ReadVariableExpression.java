package asteroids.model.programs;

public class ReadVariableExpression extends ProgramExpression {
	
	public ReadVariableExpression(ProgramExpression variable) {
		setVariable(variable);
	}

	public void setVariable(ProgramExpression variable) {
		this.variable = variable;
	}
	
	public ProgramExpression getVariable() {
		return this.variable;
	}
	
	private ProgramExpression variable;
}
