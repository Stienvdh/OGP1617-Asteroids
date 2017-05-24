package asteroids.model.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.model.Program;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.expressions.ProgramExpression;
import asteroids.model.programs.statements.ProgramStatement;

public class ProgramFunction {
	
	public ProgramFunction(String functionName, ProgramStatement body) {
		setName(functionName);
		setBody(body);
	}
	
	public Object call(List<ProgramExpression> args) {
		if (getProgram().getVariableStack().containsKey(getName()))
			throw new IllegalExpressionException(null);
		for (ProgramExpression arg: args) {
			arg.setFunction(this);
		}
		setCurrentArgs(args);
		getArgsStack().add(args);
		Object result = getBody().execute(this);
		getArgsStack().remove(args);
		if (getArgsStack().size()>0) {
			setCurrentArgs(getArgsStack().get(getArgsStack().size()-1));
		}
		return result;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ProgramStatement getBody() {
		return this.body;
	}
	
	public void setBody(ProgramStatement body) {
		this.body = body;
	}
	
	public Map<String, Object> getCurrentArgs() {
		return this.currentArgs;
	}
	
	public void setCurrentArgs(List<ProgramExpression> args) {
		List<Object> values = new ArrayList<Object>();
		for (ProgramExpression arg: args) {
			values.add(arg.getValue());
		}
		this.currentArgs = new HashMap<String, Object>();
		for (int j=0; j<values.size(); j++) {
			this.currentArgs.put("$"+(j+1), values.get(j));
		}
	}
	
	public Map<String, Object> getVariableStack() {
		return this.variableStack;
	}
	
	public void addVariable(String name, Object value) {
		this.getVariableStack().put(name, value);
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
		getBody().setProgram(program);
	}
	
	public List<List<ProgramExpression>> getArgsStack() {
		return this.argsStack;
	}
	
	private Map<String, Object> currentArgs = new HashMap<String, Object>();
	private String name;
	private ProgramStatement body;
	private Map<String, Object> variableStack = new HashMap<String, Object>();
	private Program program;
	private List<List<ProgramExpression>> argsStack = new ArrayList<List<ProgramExpression>>();
	
}
