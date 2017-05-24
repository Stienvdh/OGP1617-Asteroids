package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.ProgramFunction;
import asteroids.model.programs.ProgramLine;

public abstract class ProgramStatement implements ProgramLine {
	
	public Program getProgram() {
		return this.program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public ProgramStatement getNext() {
		if (this instanceof BreakStatement) {
			return ((BreakStatement)this).getWhileParent().getNext();
		}
		else if (getParent()!=null) {
			if (getParent() instanceof BlockStatement) {
				if (getBlockIndex() <
						((BlockStatement) getParent()).getStatements().size()-1) {
					return ((BlockStatement) getParent()).getStatements().get(
							getBlockIndex()+1);
				}
				else
					return getParent().getNext();
			}
			else if (getParent() instanceof WhileStatement) {
				if ((boolean) ((WhileStatement) getParent()).getCondition().getValue()) {
					return this;
				}
				else
					return getParent().getNext();
			}
			else
				return getParent().getNext();
		}
		return null;
	}
	
	public int getBlockIndex() {
		return this.blockIndex;
	}
	
	public void setBlockIndex(int index) {
		this.blockIndex = index;
	}
	
	public ProgramStatement getParent() {
		return this.parent;
	}
	
	public void setParent(ProgramStatement parent) {
		this.parent = parent;
	}
	
	public ProgramStatement getWhileParent() {
		ProgramStatement parentWhile = getParent();
		while (! (parentWhile instanceof WhileStatement)) {
			if (parentWhile==null) {
				return null;
			}
			parentWhile = parentWhile.getParent();
		}
		return parentWhile;
	}
	
	public abstract void execute();
	
	public abstract Object execute(ProgramFunction function);
	
	private ProgramStatement parent;
	private int blockIndex = 0;
	private Program program;
	
}
