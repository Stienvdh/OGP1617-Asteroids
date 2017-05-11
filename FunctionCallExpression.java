package asteroids.model.programs.expressions;

import java.util.List;

import asteroids.model.programs.exceptions.IllegalExpressionException;

public class FunctionCallExpression extends ProgramExpression {
	
	public FunctionCallExpression(String functionName, List<ProgramExpression> args) {
		setFunctionName(functionName);
		setArguments(args);
	}
	
	public String getFunctionName() {
		return this.functionName;
	}
	
	public void setFunctionName(String name) {
		this.functionName = name;
	}
	
	public List<ProgramExpression> getArguments() {
		return this.args;
	}
	
	public void setArguments(List<ProgramExpression> args) {
		this.args = args;
	}
	
	@Override
	public Object getValue() {
		if (getProgram()!=null) {
			if (getProgram().getFunctionStack().containsKey(getFunctionName())) {
				return getProgram().getFunctionStack().get(getFunctionName()).call(getArguments());
			}
			else
				throw new IllegalExpressionException(this);
		}
		return null;
		}
	
	private String functionName;
	private List<ProgramExpression> args;

}
