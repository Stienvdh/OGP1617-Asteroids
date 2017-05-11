package asteroids.model.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.model.Ship;
import asteroids.model.programs.expressions.ProgramExpression;
import asteroids.model.programs.statements.ProgramStatement;

public class Program {
	
	public Program(List<ProgramFunction> functions, ProgramStatement main) {
		main.setProgram(this);
		setMain(main);
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Map<String, ProgramExpression> getVariableStack() {
		return this.variableStack;
	}
	
	public void addVariable(String variableName, ProgramExpression value) {
		this.getVariableStack().put(variableName, value);
	}
	
	public void removeVariable(String variableName) {
		this.getVariableStack().remove(variableName);
	}
	
	public Map<String, ProgramFunction> getFunctionStack() {
		return this.functionStack;
	}
	
	public void addFunction(String functionName, ProgramFunction function) {
		this.getFunctionStack().put(functionName, function);
	}
	
	public void removeFunction(String functionName) {
		this.getVariableStack().remove(functionName);
	}
	
	public List<Object> getPrintStack() {
		return this.printStack;
	}
	
	public void addToPrintStack(Object object) {
		this.printStack.add(object);
	}
	
	public ProgramStatement getMain() {
		return this.main;
	}
	
	public void setMain(ProgramStatement main) {
		this.main = main;
	}
	
	public List<Object> execute() {
		main.execute();
		return getPrintStack();
	}
	
	private Ship ship;

	private Map<String, ProgramExpression> variableStack = new HashMap<String, ProgramExpression>();
	private Map<String, ProgramFunction> functionStack = new HashMap<String, ProgramFunction>();
	private List<Object> printStack = new ArrayList<Object>();
	private ProgramStatement main;
	
}
