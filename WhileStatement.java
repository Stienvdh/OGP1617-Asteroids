package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.expressions.BooleanExpression;
import asteroids.model.programs.expressions.ProgramExpression;

public class WhileStatement extends ProgramStatement {
	
	public WhileStatement(ProgramExpression condition, ProgramStatement block) {
		setCondition(condition);
		setBlock(block);
		block.setParent(this);
	}
	
	public ProgramExpression getCondition() {
		return this.condition;
	}
	
	public void setCondition(ProgramExpression condition) {
		this.condition = condition;
	}
	
	public ProgramStatement getBlock() {
		return this.block;
	}
	
	public void setBlock(ProgramStatement block) {
		this.block = block;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getCondition().setProgram(program);
		getBlock().setProgram(program);
	}

	@Override
	public void execute() {
		if (! (getCondition() instanceof BooleanExpression))
			throw new IllegalExpressionException(getCondition());
		else if ((boolean) getCondition().getValue()) {
			getProgram().setCurrentStatement(getBlock());
			getProgram().getCurrentStatement().execute();
		}
	}
	
	@Override
	public Object execute(ProgramFunction function) {
		getCondition().setFunction(function);
		while ((boolean) getCondition().getValue()) {
			Object result = getBlock().execute(function);
			if (result!=null) {
				return result;
			}
		}
		return null;
	}
	
	private ProgramExpression condition;
	private ProgramStatement block;
	
}
