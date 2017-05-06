package asteroids.model.programs;

import asteroids.model.exceptions.IllegalAssignmentException;

public class AssignmentStatement extends ProgramStatement {
	
	public AssignmentStatement(ProgramExpression leftSide, ProgramExpression rightSide) {
		setSides(leftSide, rightSide);
	}
	
	public void setSides(ProgramExpression left, ProgramExpression right) {
		this.leftSide = left;
		if (left.isValidAssignment(right)) {
			this.rightSide = right;
		}
		else
			throw new IllegalAssignmentException(left, right);
	}
	
	public ProgramExpression getLeftSide() {
		return this.leftSide;
	}
	
	public ProgramExpression getRightSide() {
		return this.rightSide;
	}
	
	public void execute() {
		getLeftSide().setValue(getRightSide().getValue());
	}
	
	private ProgramExpression leftSide;
	private ProgramExpression rightSide;
	
}
