package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

/**
 * A two-dimensional, rectangular area containing ships and bullets.
 *
 */
public class World {
	
	public World(double width, double height) {
		World.width = width;
		World.height = height;
		HashSet<Entity> entities = new HashSet<Entity>();
	}
	
	/**
	 * defensief
	 * @param ship
	 */
	public void addShip(Ship ship) throws IllegalShipException {
		if (ship == null)
			throw new IllegalShipException(ship);
		entities.add(ship);
	}
	
	/**
	 * defensief
	 * @param ship
	 */
	public void removeShip(Ship ship) throws IllegalShipException {
		if (ship == null)
			throw new IllegalShipException(ship);
		if (! entities.contains(ship))
			throw new IllegalShipException(ship);
		entities.remove(ship);
	}
	
	/**
	 * defensief
	 * @param ship
	 */
	public void addBullet(Bullet bullet) throws IllegalBulletException {
		if (bullet == null)
			throw new IllegalBulletException(bullet);
		entities.add(bullet);
	}
	
	/**
	 * defensief
	 * @param ship
	 */
	public void removeBullet(Bullet bullet) {
		if (bullet == null)
			throw new IllegalBulletException(bullet);
		if (! entities.contains(bullet))
			throw new IllegalBulletException(bullet);
		entities.remove(bullet);
	}
	
	/**
	 * totaal
	 * @param xpos
	 * @param ypos
	 * @return
	 */
	public Entity locateEntity(double xpos, double ypos) {
		for (Entity entity : entities) {
			if (entity instanceof Ship) {
				Ship currentShip = (Ship) entity;
				if (Math.sqrt(Math.pow(xpos - currentShip.getXPosition(),2) + (Math.pow(ypos - currentShip.getYPosition(),2))) <= currentShip.getRadius())
					return entity;
			}
			if (entity instanceof Bullet) {
				Bullet currentBullet = (Bullet) entity;
				if (Math.sqrt(Math.pow(xpos - currentBullet.getXPosition(),2) + (Math.pow(ypos - currentBullet.getYPosition(),2))) <= currentBullet.getRadius())
					return entity;
			}
		}
		return null;
	}
	
	/**
	 * defensief
	 */
	public void evolve(double dt) {
		double tc = dt;
		ArrayList timeList = new ArrayList();
		for (Entity entity1 : entities) {
			for (Entity entity2 : entities) {
				if ((entity1.getTimeToCollision(entity2) < dt) && (entity1.getTimeToCollision(entity2) < tc))
					tc = entity1.getTimeToCollision(entity2);
			}
		}
		//
		if (tc < dt)
			this.evolve(dt - tc);
		
	}
	
	public Set<Entity> entities;
	
	private static double height;
	
	private static double width;
	
	private static double MAX_VALUE;
}
