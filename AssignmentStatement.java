package asteroids.model.programs.statements;

import asteroids.model.programs.Program;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.expressions.ProgramExpression;

public class AssignmentStatement extends ProgramStatement {
	
	public AssignmentStatement(String variableName, ProgramExpression value) {
		setVariable(variableName, value);
	}
	
	public void setVariable(String name, ProgramExpression value) {
		this.variableName = name;
		if (isValidAssignment(name, value)) {
			this.value = value;
		}
		else
			throw new IllegalExpressionException(value);
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public ProgramExpression getVariableValue() {
		return this.value;
	}
	
	public void execute() {
		if (getProgram()!=null) {
			getProgram().getVariableStack().put(getVariableName(), getVariableValue());
		}
	}
	
	public boolean isValidAssignment(String name, ProgramExpression value) {
		if (getProgram()!=null) {
			if (getProgram().getVariableStack().containsKey(name)) {
					if (getProgram().getVariableStack().get(name).getClass()
							==value.getValue().getClass())
						return true; 
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
