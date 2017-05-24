package asteroids.model.programs;

import java.util.List;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.ClosestEntity;
import asteroids.model.Entity;
import asteroids.model.EntitySet;
import asteroids.model.MinorPlanet;
import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.model.programs.exceptions.IllegalExpressionException;
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
		return new ProgramFunction(functionName, body);
	}

	@Override
	public ProgramStatement createAssignmentStatement(String variableName, ProgramExpression value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value);
	}

	@Override
	public ProgramStatement createWhileStatement(ProgramExpression condition, ProgramStatement body, SourceLocation sourceLocation) {
		return new WhileStatement(condition, body);
	}

	@Override
	public ProgramStatement createBreakStatement(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public ProgramStatement createReturnStatement(ProgramExpression value, SourceLocation sourceLocation) {
		return new ReturnStatement(value);
	}

	@Override
	public ProgramStatement createIfStatement(ProgramExpression condition, ProgramStatement ifBody, ProgramStatement elseBody, SourceLocation sourceLocation) {
		return new IfThenElseStatement(condition, ifBody, elseBody);
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
		return new ReadParameterExpression(parameterName);
	}

	@Override
	public ProgramExpression createFunctionCallExpression(String functionName, List<ProgramExpression> actualArgs, SourceLocation sourceLocation) {
		return new FunctionCallExpression(functionName, (List<ProgramExpression>) actualArgs);
	}

	@Override
	public ProgramExpression createChangeSignExpression(ProgramExpression expression, SourceLocation sourceLocation) {
		return new ChangeSignExpression(expression);
	}

	@Override
	public ProgramExpression createNotExpression(ProgramExpression expression, SourceLocation sourceLocation) {
		return new LogicalNegationExpression(expression);
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
		return new SimpleEntityExpression(null) {
			@Override
			public Entity getValue() {
				return (new ClosestEntity<Ship>(getShip(), Ship.class)).getClosestEntity();
			}
		};
	}

	@Override
	public ProgramExpression createAsteroidExpression(SourceLocation location) {
		return new SimpleEntityExpression(null) {
			@Override
			public Entity getValue() {
				return (new ClosestEntity<Asteroid>(getShip(), Asteroid.class)).getClosestEntity();
			}
		};
	}

	@Override
	public ProgramExpression createPlanetoidExpression(SourceLocation location) {
		return new SimpleEntityExpression(null) {
			@Override
			public Entity getValue() {
				return (new ClosestEntity<Planetoid>(getShip(), Planetoid.class)).getClosestEntity();
			}
		};
	}

	@Override
	public ProgramExpression createBulletExpression(SourceLocation location) {
		return new SimpleEntityExpression(null) {
			@Override
			public Entity getValue() {
				if (getShip()!=null) {
					Object[] stream = (new EntitySet<Bullet>(getShip().getWorld(),Bullet.class))
							.getSet().stream().filter(entity -> ((Bullet)entity).getSource()==getShip()).toArray();
					if (stream.length>0) {
						return (Entity) stream[0];
					}
					else
						return null;
				}
				return null;
			}
		};
	}

	@Override
	public ProgramExpression createPlanetExpression(SourceLocation location) {
		return new SimpleEntityExpression(null) {
			@Override
			public Entity getValue() {
				return (new ClosestEntity<MinorPlanet>(getShip(), MinorPlanet.class)).getClosestEntity();
			}
		};
	}

	@Override
	public ProgramExpression createAnyExpression(SourceLocation location) {
		return new SimpleEntityExpression(null) {
			@Override
			public Entity getValue() {
				if (getShip()!=null) {
					return (Entity) getShip().getWorld().getAllEntities().toArray()[0];
				}
				return null;
			}
		};
	}

	@Override
	public ProgramExpression createGetXExpression(ProgramExpression e, SourceLocation location) {
		return new GetXExpression(e);
	}

	@Override
	public ProgramExpression createGetYExpression(ProgramExpression e, SourceLocation location) {
		return new GetYExpression(e);
	}

	@Override
	public ProgramExpression createGetVXExpression(ProgramExpression e, SourceLocation location) {
		return new GetXVelocityExpression(e);
	}

	@Override
	public ProgramExpression createGetVYExpression(ProgramExpression e, SourceLocation location) {
		return new GetYVelocityExpression(e);
	}

	@Override
	public ProgramExpression createGetRadiusExpression(ProgramExpression e, SourceLocation location) {
		return new GetRadiusExpression(e);

	}

	@Override
	public ProgramExpression createLessThanExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new LessThanExpression(e1, e2);
	}

	@Override
	public ProgramExpression createEqualityExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new EqualsExpression(e1, e2);
	}

	@Override
	public ProgramExpression createAdditionExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new AdditionExpression(e1, e2);
	}

	@Override
	public ProgramExpression createMultiplicationExpression(ProgramExpression e1, ProgramExpression e2, SourceLocation location) {
		return new MultiplicationExpression(e1, e2);
	}

	@Override
	public ProgramExpression createSqrtExpression(ProgramExpression e, SourceLocation location) {
			return new SquareRootExpression(e);
	}

	@Override
	public ProgramExpression createGetDirectionExpression(SourceLocation location) {
		return new GetDirectionExpression();
	}

	@Override
	public ProgramStatement createThrustOnStatement(SourceLocation location) {
		return new ActionStatement() {
			@Override
			public void execute() {
				super.execute();
				if (getShip()!=null) {
					getShip().thrustOn();
				}
			}
		};
	}

	@Override
	public ProgramStatement createThrustOffStatement(SourceLocation location) {
		return new ActionStatement() {
			@Override
			public void execute() {
				super.execute();
				if (getShip()!=null) {
					getShip().thrustOff();
				}
			}
		};
	}

	@Override
	public ProgramStatement createFireStatement(SourceLocation location) {
		return new ActionStatement() {
			@Override
			public void execute() {
				super.execute();
				if (getShip()!=null) {
					getShip().fireBullet();
				}
			}
		};
	}

	@Override
	public ProgramStatement createTurnStatement(ProgramExpression angle, SourceLocation location) {
		return new ActionStatement() {
			@Override
			public void execute() {
				super.execute();
				if (getShip()!=null) {
					if (! (angle instanceof DoubleExpression))
						throw new IllegalExpressionException(angle);
					getShip().turn(((DoubleExpression)angle).getValue());
				}
			}
		};
	}

	@Override
	public ProgramStatement createSkipStatement(SourceLocation location) {
		return new ActionStatement() {
		};
	}
}
