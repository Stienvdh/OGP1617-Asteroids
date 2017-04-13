package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.model.*;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements asteroids.part2.facade.IFacade {
	
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

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		try {
			return new Ship(x,y,xVelocity,yVelocity,radius,direction,mass); }
		catch (IllegalPositionException exc) {
			throw new ModelException("Illegal position"); 
		}
		catch (IllegalRadiusException exc2) {
			throw new ModelException("Illegal radius");
		}
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.thrusterEnabled();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active==true)
			ship.thrustOn();
		else
			ship.thrustOff();
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		return new Bullet(x,y,xVelocity,yVelocity,radius);
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return new double[]{bullet.getXPosition(),bullet.getYPosition()};
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return new double[]{bullet.getXPosition(),bullet.getYPosition()};
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getSource();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		return new World(width, height);
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return new double[]{world.getWidth(),world.getHeight()};
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getBullets();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addEntity(ship);
		}
		catch (IllegalEntityException exc) {
			throw new ModelException("Illegal entity");
		}
		catch (IllegalPositionException exc2) {
			throw new ModelException("Illegal entity");
		}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeEntity(ship);
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addEntity(bullet);
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeEntity(bullet);
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		ship.loadBullet(bullet);
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		ship.loadBullet(bullets);
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBullet(bullet);
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return ((Entity)object).getTimeToBoundary();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((Entity)object).getBoundaryPosition();
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return ((Entity)entity1).getTimeToCollision((Entity)entity2);
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return ((Entity)entity1).getCollisionPosition((Entity)entity2);
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeFirstCollision();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getFirstCollisionPosition();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt);
		}
		catch (IllegalEntityException exc) {
			throw new ModelException("Illegal entity");
		}
		catch (IllegalPositionException exc2) {
			throw new ModelException("Illegal position");
		}
		catch (IllegalWorldException exc3) {
			throw new ModelException("Illegal world");
		}
		catch (IllegalDurationException exc4) {
			throw new ModelException("Illegal duration");
		}
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x, y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getAllEntities();
	}

}
