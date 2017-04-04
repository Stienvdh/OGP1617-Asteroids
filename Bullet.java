package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class Bullet extends Entity{
	
	/**
	 * Return the position of this bullet along the x-axis.
	 */
	@Basic @Raw
	public double getXPosition() {
		return this.xPosition;
	}
	
	/**
	 * Return the position of this bullet along the y-axis.
	 */
	@Basic @Raw
	public double getYPosition() {
		return this.yPosition;
	}
	
	/**
	 * Return the mass of this bullet.
	 */
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Return the ship, in which this bullet is loaded. Null if none.
	 */
	public Ship getShip() {
		return this.ship;
	}
	
	/**
	 * Load this bullet in a given ship.
	 * 
	 * @param 	ship
	 * 			The ship in which this bullet has to be loaded.
	 * @post	This bullet is loaded in the given ship.
	 * 			| new.ship == ship
	 * @throws	IllegalShipException
	 * 			The given ship did not load this bullet.
	 * 			| ! ship.getBullets().contains(this)
	 */
	public void setShip(Ship ship) {
		if (! ship.getBullets().contains(this))
			throw new IllegalShipException(ship);
		this.ship = ship;
	}
	
	/**
	 * Return the world, in which this bullet is located. Null if none.
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Locate this bullet in the given world.
	 * 
	 *@param 	world
	 * 			The world, in which this bullet has to be located.
	 * @post	If the bullet does not belong to a world/ship yet and if the given world already associates 
	 * 			the bullet with itself, the new world of this bullet is the given world.
	 * 			| if (!old.hasPosition())&&(world.getEntities().contains(this))
	 *			|	new.world == world;		
	 */
	public void setWorld(World world) {
		if (!this.hasPosition()&&(world.getEntities().containsValue(this)))
			this.world = world;
	}
	
	/**
	 * Return whether this bullet is located in a world/ship.
	 */
	public boolean hasPosition() {
		return ((this.getWorld()!=null)||(this.getShip()!=null));
	}
	
	/**
	 * A variable registering the position of this bullet along the x-axis.
	 */
	private double xPosition;
	
	/**
	 * A variable registering the position of this bullet along the y-axis. 
	 */
	private double yPosition;
	
	/** 
	 * A variable registering the mass of this bullet.
	 */
	public double mass;
	
	/**
	 * A variable registering in which ship this bullet is loaded. Null if none.
	 */
	public Ship ship;
	
	/**
	 * A variable registering in which world this bullet is loaded. Null if none.
	 */
	public World world;
}
