package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class Bullet extends Entity{
	
	/**
	 * 
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius) {
		
	}
	
	/**
	 * Return the position of this bullet along the x-axis.
	 */
	@Basic @Raw
	public double getXPosition() {
		return this.xPosition;
	}
	
	/**
	 * Set the position of this bullet to a given position.
	 * 
	 * @param	xpos
	 * 			The new position of this bullet along the x-axis.
	 * @param	ypos
	 * 			The new position of this bullet along the y-axis.
	 * @post	The new position for this new bullet is equal to the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| ! isValidPosition(xpos, ypos)
	 */
	@Raw
	public void setPosition(double xpos, double ypos) {
		if (! isValidPosition(xpos,ypos))
			throw new IllegalPositionException(xpos, ypos);
		this.xPosition = xpos;
		this.yPosition = ypos;
	}
	
	/**
	 * Checks whether the given position is a valid position
	 * 
	 * @param 	xpos
	 * 			The position along the x-axis to check.
	 * @param 	ypos
	 * 			The position along the y-axis to check.
	 * @return	If this bullet is not associated with a world, nor with a ship, it is positioned
	 * 			in an unbounded two-dimensional space.
	 * 			| if (! hasPosition())
	 * 			| 	result == true
	 * @return	If this bullet is associated with a world, this method returns true if and only
	 * 			if the bullet is located within bounds of its world and does not overlap with another
	 * 			entity in its world.
	 * 			| if this.getWorld() != null
	 * 			| 	result == (this.getRadius()<xpos<this.getWorld().getWidth()-getRadius())&&
	 * 			| 		(this.getRadius()<ypos<this.getWorld().getHeight()-getRadius())&&
	 * 			| 		(for each entity in this.getWorld().getEntities(): !this.overlap(entity))
	 * @return	If this bullet is associated with a ship, this method returns true if and only if 
	 * 			this bullet is located within bounds of its mother world and does not overlap with 
	 * 			another entity in its mother world. If this bullet is loaded in a ship, the method
	 * 			returns true.
	 * 			| if this.getShip() != null
	 * 			| 	if this.getShip().getBullets().contains(this)
	 * 			| 		result == true
	 * 			| 	else 
	 * 			| 		result == (this.getRadius()<xpos<this.getMotherWorld().getWidth()-getRadius())&&
	 * 			| 		(this.getRadius()<ypos<this.getMotherWorld().getHeight()-getRadius())&&
	 * 			| 		(for each entity in this.getMotherWorld().getEntities(): !this.overlap(entity)
	 */
	public boolean isValidPosition(double xpos, double ypos) {
		if (! hasPosition())
			return true;
		else if (this.getWorld() != null)
			world = this.getWorld();
		else if (this.getShip() != null) {
			if (this.getShip().getBullets().contains(this))
				return true;
			world = this.getMotherWorld();
		}
		if ((this.getRadius()<=xpos)&&(xpos<=world.getWidth()-this.getRadius())&&
				(this.getRadius()<=ypos)&&(ypos<=world.getHeight()-this.getRadius())) {
			for (Entity entity: world.getEntities().values()) {
				if (this.overlap(entity))
				return false;
			return true;
			}
		}
		return false;
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
		return (4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getMassDensity();
	}
	
	/**
	 * Return the mass density of this bullet.
	 */
	public double getMassDensity() {
		return this.massDensity;
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
	 * Return the radius of this bullet.
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Set the radius of this bullet to a given radius.
	 * 
	 * @param	radius
	 * 			The new radius of this bullet.
	 * @post	The new radius of this bullet is the given radius.
	 * 			| new.getRadius() == radius
	 * @throws	IllegalRadiusException
	 * 			The given radius does not exceed its minimum value.
	 * 			| radius < MIN_RADIUS
	 */
	public void setRadius(double radius) {
		if (radius < MIN_RADIUS)
			throw new IllegalRadiusException(radius);
		this.radius = radius;
	}
	
	/**
	 * Return the mother world of this bullet.
	 * 
	 * @return	If this bullet is loaded in a ship, the world associated with this ship is returned.
	 * 			Otherwise, null is returned.
	 * 			| if this.getShip() != null
	 * 			| 	return this.getShip().getWorld()
	 * 			| else
	 * 			| 	return null
	 */
	public World getMotherWorld() {
		if (this.getShip().getWorld() != null)
			return this.getShip().getWorld();
		else
			return null;
	}
	
	/**
	 * Terminate this bullet.
	 * 
	 * @post	If this bullet was located in a world, it is not anymore.
	 * 			| if old.getWorld()!=null
	 * 			| 	pos == [old.getXPosition(),old.getYPosition()]
	 * 			| 	! old.getWorld().getEntities().contains(pos)
	 * 			| 	! old.getWorld().getBullets().contains(old)
	 * 			| 	new.getWorld() == null
	 * @post	If this bullet was associated with a ship, it is not anymore.
	 * 			| if old.getShip != null
	 * 			| 	if old.getShip().getBullets().contains(old)
	 * 			| 		! old.getShip().getBullets().contains(old)
	 * 			| 	new.getShip() == null
	 */
	public void terminate() {
		this.isTerminated = true;
		if (this.getWorld()!=null) {
			double[] pos = {this.getXPosition(),this.getYPosition()};
			this.getWorld().getEntities().remove(pos);
			this.getWorld().getBullets().remove(this);
			this.setWorld(null);
		}
		if (this.getShip()!=null) {
			if (this.getShip().getBullets().contains(this))
				this.getShip().getBullets().remove(this);
			this.setShip(null);
		}
	}
	
	/**
	 * Return whether this bullet is terminated.
	 */
	public boolean isTerminated() {
		return this.isTerminated;
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
	private double massDensity = DENSITY;
	
	/**
	 * A variable registering in which ship this bullet is loaded. Null if none.
	 */
	private Ship ship;
	
	/**
	 * A variable registering in which world this bullet is loaded. Null if none.
	 */
	private World world;
	
	/**
	 * A variable registering the radius of this bullet.
	 */
	private double radius;
	
	/**
	 * A variable registering whether this bullet is terminated.
	 */
	private boolean isTerminated;
	
	/**
	 * A variable registering the minimum radius of a bullet.
	 */
	private static double MIN_RADIUS = 1;
	
	/**
	 * A variable registering the mass density of a bullet.
	 */
	private static double DENSITY = 7.8*Math.pow(10,12);

}
