package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.*;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalEntityException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.exceptions.IllegalShipException;
import asteroids.model.exceptions.IllegalWorldException;
import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalOrientationException;
import asteroids.model.programs.*;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.exceptions.IllegalStatementException;
import asteroids.part2.CollisionListener;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;

public class Facade implements asteroids.part3.facade.IFacade {
	
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
			return new Ship(x, y, xVelocity, yVelocity, radius, orientation, 100); }
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
		catch (IllegalEntityException exc2) {
			throw new ModelException("Illegal entity"); 
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
		catch (IllegalOrientationException exc3) {
			throw new ModelException("Illegal orientation");
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
		return new double[]{bullet.getXVelocity(),bullet.getYVelocity()};
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
		return (new EntitySet<Ship>(world, Ship.class)).getSet();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return (new EntitySet<Bullet>(world, Bullet.class)).getSet();
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
		try {
			world.removeEntity(ship);
		}
		catch (IllegalEntityException exc) {
			throw new ModelException("Illegal entity");
		}
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.addEntity(bullet);
		}
		catch (IllegalEntityException exc) {
			throw new ModelException("Illegal entity");
		}
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
		try {
			ship.loadBullet(bullet);
		}
		catch (IllegalBulletException exc) {
			throw new ModelException("Illegal Bullet");
		}
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.loadBullet(bullets);
		}
		catch (IllegalBulletException exc) {
			throw new ModelException("Illegal Bullet");
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBullet(bullet);
		}
		catch (IllegalBulletException exc) {
			throw new ModelException("Illegal Bullet");
		}
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
//		catch (IllegalPositionException exc2) {
//			throw new ModelException("Illegal position");
//		}
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

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return (new EntitySet<Asteroid>(world, Asteroid.class)).getSet();
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		world.addEntity(asteroid);
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		world.removeEntity(asteroid);
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return (new EntitySet<Planetoid>(world, Planetoid.class)).getSet();
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		world.addEntity(planetoid);
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		world.removeEntity(planetoid);
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		return new Asteroid(x,y,xVelocity,yVelocity,radius);
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return new double[]{asteroid.getXPosition(),asteroid.getYPosition()};
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return new double[]{asteroid.getXVelocity(),asteroid.getYVelocity()};
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		return new Planetoid(x,y,xVelocity,yVelocity,radius,totalTraveledDistance);
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.terminate();
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isTerminated();
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		return new double[]{planetoid.getXPosition(),planetoid.getYPosition()};
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		return new double[]{planetoid.getXVelocity(),planetoid.getYVelocity()};
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getTotalDistance();
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getWorld();
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		ship.setProgram(program);
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		try {
		if (ship.getProgram()!=null)
			return ship.getProgram().execute(dt);
		}
		catch (IllegalExpressionException exc) {
			throw new ModelException("Illegal expression");
		}
		catch (IllegalStatementException exc) {
			throw new ModelException("Illegal statement");
		}
		return null;
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		return new ProgramFactory();
	}

}
