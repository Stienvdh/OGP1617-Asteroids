package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.exceptions.IllegalStatementException;
import asteroids.model.programs.expressions.ProgramExpression;

public class AssignmentStatement extends ProgramStatement {
	
	public AssignmentStatement(String variableName, ProgramExpression value) {
		setVariable(variableName, value);
	}
	
	public void setVariable(String name, ProgramExpression value) {
		this.variableName = name;
		this.value = value;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public ProgramExpression getVariableValue() {
		return this.value;
	}
	
	public void execute() {
		if (getProgram()!=null) {
			if (isValidAssignment(getVariableName(), getVariableValue())) {
				System.out.print("ok" + getVariableValue().getValue());
				getProgram().getVariableStack().put(getVariableName(), getVariableValue()); }
			else
				throw new IllegalStatementException(this);
		}	
	}
	
	public boolean isValidAssignment(String name, ProgramExpression value) {
		if (getProgram()!=null) {
			value.setProgram(getProgram());
			if (getProgram().getVariableStack().containsKey(name)) {
					if (getProgram().getVariableStack().get(name).getValue().getClass()
							==value.getValue().getClass()) {
						return true; 
					}
					else
						return false;	
			}
		}
		return true;
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
	private ProgramExpression value;
	
}
