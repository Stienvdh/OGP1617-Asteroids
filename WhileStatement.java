package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.exceptions.IllegalStatementException;
import asteroids.model.programs.expressions.BooleanExpression;
import asteroids.model.programs.expressions.ProgramExpression;

public class WhileStatement extends ProgramStatement {
	
	public WhileStatement(ProgramExpression condition, ProgramStatement block) {
		setCondition(condition);
		setBlock(block);
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
		if (! (getBlock() instanceof BlockStatement))
			throw new IllegalStatementException(getBlock());
		((BlockStatement)getBlock()).setWhileStatement(this);
		while ((boolean) getCondition().getValue()) {
			getBlock().execute();
		}
	}
	
	private ProgramExpression condition;
	private ProgramStatement block;
	
}
