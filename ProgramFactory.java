package asteroids.model.programs;

import java.util.List;

import asteroids.model.Ship;
import asteroids.model.programs.expressions.*;
import asteroids.model.programs.statements.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<ProgramExpression, 
		ProgramStatement, ProgramFunction, Program> {

	@Override
	public Program createProgram(List<ProgramFunction> functions, ProgramStatement main) {
		return new Program(functions, main);
	}

	@Override
	public ProgramFunction createFunctionDefinition(String functionName, ProgramStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramStatement createAssignmentStatement(String variableName, ProgramExpression value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, (ProgramExpression) value);
	}

	@Override
	public ProgramStatement createWhileStatement(ProgramExpression condition, ProgramStatement body, SourceLocation sourceLocation) {
		return new WhileStatement((BooleanExpression) condition, (BlockStatement) body);
	}

	@Override
	public ProgramStatement createBreakStatement(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public ProgramStatement createReturnStatement(ProgramExpression value, SourceLocation sourceLocation) {
		return new ReturnStatement((ProgramExpression) value);
	}

	@Override
	public ProgramStatement createIfStatement(ProgramExpression condition, ProgramStatement ifBody, ProgramStatement elseBody, SourceLocation sourceLocation) {
		return new IfThenElseStatement((BooleanExpression) condition, ifBody, elseBody);
	}

	@Override
	public ProgramStatement createPrintStatement(ProgramExpression value, SourceLocation sourceLocation) {
		return new PrintStatement(value);
	}

	@Override
	public ProgramStatement createSequenceStatement(List<ProgramStatement> statements, SourceLocation sourceLocation) {
		return new BlockStatement(statements);
	}

	@Override
	public ProgramExpression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new ReadVariableExpression(variableName);
	}

	@Override
	public ProgramExpression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramExpression createFunctionCallExpression(String functionName, List<ProgramExpression> actualArgs, SourceLocation sourceLocation) {
		return new FunctionCallExpression(functionName, (List<ProgramExpression>) actualArgs);
	}

	@Override
	public ProgramExpression createChangeSignExpression(ProgramExpression expression, SourceLocation sourceLocation) {
		return new ChangeSignExpression((DoubleExpression)expression);
	}

	@Override
	public ProgramExpression createNotExpression(ProgramExpression expression, SourceLocation sourceLocation) {
		return new LogicalNegationExpression((BooleanExpression) expression);
	}

	@Override
	public ProgramExpression createDoubleLiteralExpression(double value, SourceLocation location) {
		return new DoubleLiteralExpression(value);
	}

	@Override
	public ProgramExpression createNullExpression(SourceLocation location) {
		return new SimpleEntityExpression(null);
	}

	@Override
	public ProgramExpression createSelfExpression(SourceLocation location) {
		return new SelfExpression();
	}

	@Override
	public ProgramExpression createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramExpression createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramExpression createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramExpression createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramExpression createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramExpression createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramExpression createGetXExpression(ProgramExpression e, SourceLocation location) {
		return new GetXExpression((EntityExpression) e);
	}

	@Override
	public ProgramExpression createGetYExpression(ProgramExpression e, SourceLocation location) {
		return new GetYExpression((EntityExpression)e);
	}

	@Override
	public ProgramExpression createGetVXExpression(ProgramExpression e, SourceLocation location) {
		return new GetXVelocityExpression((EntityExpression) e);
	}

	@Override
	public ProgramExpression createGetVYExpression(ProgramExpression e, SourceLocation location) {
		return new GetYVelocityExpression((EntityExpression) e);
	}

	@Override
	public ProgramExpression createGetRadiusExpression(ProgramExpression e, SourceLocation location) {
		return new GetRadiusExpression((EntityExpression) e);

	}

	@Override
	public ProgramExpression createLessThanExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new LessThanExpression((DoubleExpression) e1, (DoubleExpression) e2);
	}

	@Override
	public ProgramExpression createEqualityExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new EqualsExpression(e1, e2);
	}

	@Override
	public ProgramExpression createAdditionExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new AdditionExpression((DoubleExpression) e1, (DoubleExpression) e2);
	}

	@Override
	public ProgramExpression createMultiplicationExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new MultiplicationExpression((DoubleExpression) e1, (DoubleExpression) e2);
	}

	@Override
	public ProgramExpression createSqrtExpression(ProgramExpression e, SourceLocation location) {
		return new SquareRootExpression((DoubleExpression) e);
	}

	@Override
	public ProgramExpression createGetDirectionExpression(SourceLocation location) {
		return new GetDirectionExpression();
	}

	@Override
	public ProgramStatement createThrustOnStatement(SourceLocation location) {
		return new ActionStatement() {
			public void execute() {
				if ((new SelfExpression()).getValue()!=null) {
					((Ship)(new SelfExpression()).getValue()).thrustOn();
				}
			}
		};
	}

	@Override
	public ProgramStatement createThrustOffStatement(SourceLocation location) {
		return new ActionStatement() {
			public void execute() {
				if ((new SelfExpression()).getValue()!=null) {
					((Ship)(new SelfExpression()).getValue()).thrustOff();
				}
			}
		};
	}

	@Override
	public ProgramStatement createFireStatement(SourceLocation location) {
		return new ActionStatement() {
			public void execute() {
				if ((new SelfExpression()).getValue()!=null) {
					((Ship)(new SelfExpression()).getValue()).fireBullet();
				}
			}
		};
	}

	@Override
	public ProgramStatement createTurnStatement(ProgramExpression angle, SourceLocation location) {
		return new ActionStatement() {
			public void execute() {
				if ((new SelfExpression()).getValue()!=null) {
					((Ship)(new SelfExpression()).getValue()).turn(((DoubleExpression)angle).getValue());
				}
			}
		};
	}

	@Override
	public ProgramStatement createSkipStatement(SourceLocation location) {
		return new ActionStatement() {
			public void execute() {}
		};
	}
}
