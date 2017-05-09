package asteroids.model.programs;

import asteroids.model.exceptions.IllegalConditionException;

public class WhileStatement extends ProgramStatement {
	
	public WhileStatement(ProgramExpression condition, ProgramStatement block) {
		setCondition(condition);
		setBlock(block);
	}
	
	public ProgramExpression getCondition() {
		return this.condition;
	}
	
	public void setCondition(ProgramExpression condition) {
		if (condition.getValue()==true||condition.getValue()==false) {
			this.condition = condition;
		}
		else
			throw new IllegalConditionException(condition);
	}
	
	public ProgramStatement getBlock() {
		return this.block;
	}
	
	public void setBlock(ProgramStatement block) {
		this.block = block;
	}

	@Override
	public void execute() {
		while (getCondition().getValue()) {
			getBlock().execute();
		}
	}
	
	private ProgramExpression condition;
	private ProgramStatement block;
	
}
