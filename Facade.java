package asteroids.facade;

import asteroids.model.*;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade {
	
	public Facade() {
	}

	@Override
	public Ship createShip() throws ModelException {
		try {
			return new Ship(); }
		catch (IllegalPositionException exc) {
			throw new ModelException("Illegal position");
		}
		catch (IllegalRadiusException exc2) {
			throw new ModelException("Illegal radius");
		}
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, radius, orientation, 300000); }
		catch (IllegalPositionException exc) {
			throw new ModelException("Illegal position");
		}
		catch (IllegalRadiusException exc2) {
			throw new ModelException("Illegal radius");
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return new double[]{ship.getXPosition(), ship.getYPosition()};
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return new double[]{ship.getXVelocity(), ship.getYVelocity()};
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		try {
			ship.move(dt); }
		catch (IllegalDurationException exc) {
			throw new ModelException("Illegal duration"); 
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2); }
		catch (IllegalShipException exc) {
			throw new ModelException("Illegal ship"); 
		}
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2); }
		catch (IllegalShipException exc) {
			throw new ModelException("Illegal ship"); 
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2); }
		catch (IllegalShipException exc) {
			throw new ModelException("Illegal ship"); 
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2); }
		catch (IllegalShipException exc) {
			throw new ModelException("Illegal ship"); 
		}
	}

}
