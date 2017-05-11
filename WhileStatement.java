package asteroids.model.programs.statements;

import asteroids.model.programs.expressions.BooleanExpression;

public class WhileStatement extends ProgramStatement {
	
	public WhileStatement(BooleanExpression condition, BlockStatement block) {
		setCondition(condition);
		setBlock(block);
	}
	
	public BooleanExpression getCondition() {
		return this.condition;
	}
	
	public void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}
	
	public ProgramStatement getBlock() {
		return this.block;
	}
	
	public void setBlock(BlockStatement block) {
		this.block = block;
	}

	@Override
	public void execute() {
		while (getCondition().getValue()) {
			getBlock().execute();
		}
	}
	
	private BooleanExpression condition;
	private BlockStatement block;
	
}
