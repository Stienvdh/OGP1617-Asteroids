package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;
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
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		this.getVariableValue().setProgram(program);
	}
	
	@Override
	public void execute() {
		if (getProgram()!=null) {
			if (isValidAssignment(getVariableName(), getVariableValue())) {
				getProgram().addVariable(getVariableName(), getVariableValue().getValue()); 
				}
			else
				throw new IllegalStatementException(this);
		}	
	}
	
	public boolean isValidAssignment(String name, ProgramExpression value) {
		if (getProgram()!=null) {
			if (getProgram().getFunctionStack().containsKey(getVariableName()))
				throw new IllegalStatementException(this);
			if (getProgram().getVariableStack().containsKey(name)) {
					if (getProgram().getVariableStack().get(name).getClass()
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
	public Object execute(ProgramFunction function) {
		getVariableValue().setFunction(function);
		if (function.getVariableStack().containsKey(getVariableName())) {
			if (! (function.getVariableStack().get(getVariableName()).getClass()
					==value.getValue().getClass())) {
				System.out.println(function.getVariableStack().get(getVariableName()).getClass());
				System.out.println(value.getValue().getClass());
				throw new IllegalStatementException(this);
			}
		}
		else
			function.addVariable(getVariableName(), getVariableValue().getValue());
		return null;
	}
	
	private String variableName;
	private ProgramExpression value;
	
}
