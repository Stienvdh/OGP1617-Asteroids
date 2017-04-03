package asteroids.model;

public class Bullet {
	
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
