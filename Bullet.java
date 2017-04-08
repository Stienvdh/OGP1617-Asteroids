package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;

public class Bullet extends Entity{
	
	/**
	 * Create a new bullet with given position, velocity and radius.
	 * 
	 * @param	xpos
	 * 			The position of this bullet along the x-axis.
	 * @param	ypos 
	 * 			The position of this bullet along the y-axis.
	 * @post	The position of this bullet is the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| ! isValidPosition(xpos,ypos)
	 */
	public Bullet(double xpos, double ypos, double xvel, double yvel, double radius) 
			throws IllegalPositionException {
		setWorld(null);
		setShip(null);
		setSource(null);
		setPosition(xpos, ypos);
		setMaxSpeed(MAX_SPEED);
		setVelocity(xvel, yvel);
		setRadius(radius);
	}
	
	/**
	 * Checks whether the given position is a valid position
	 * 
	 * @param 	xpos
	 * 			The position along the x-axis to check.
	 * @param 	ypos
	 * 			The position along the y-axis to check.
	 * @return 	If xpos or ypos is infinity or not a number, false is returned.
	 * 			| if ((((xpos == Double.POSITIVE_INFINITY)||(xpos == Double.NEGATIVE_INFINITY)||(Double.isNaN(xpos))))
				|	||(((ypos == Double.POSITIVE_INFINITY)||(ypos == Double.NEGATIVE_INFINITY)||(Double.isNaN(ypos)))))
				|	then result == false
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
		if ((((xpos == Double.POSITIVE_INFINITY)||(xpos == Double.NEGATIVE_INFINITY)||(Double.isNaN(xpos))))
				||(((ypos == Double.POSITIVE_INFINITY)||(ypos == Double.NEGATIVE_INFINITY)||(Double.isNaN(ypos)))))
			return false;
		if (! this.hasPosition())
			return true;
		else if (this.getWorld() != null)
			world = this.getWorld();
		else if (this.getShip() != null)
			return true;
		else 
			world = this.getSource().getWorld();
		if ((this.getRadius()<=xpos)&&(xpos<=world.getWidth()-this.getRadius())&&
				(this.getRadius()<=ypos)&&(ypos<=world.getHeight()-this.getRadius())) {
			for (Entity entity: world.getEntities().values()) {
				if ((this.overlap(entity))&&(entity!=this))
				return false;
			return true;
			}
		}
		return false;
	}
	
	/**
	 * Set the maximum speed of this bullet to a given speed.
	 * 
	 * @param 	maxSpeed	
	 * 			The new maximum speed of this bullet.
	 * @post	If the given speed does not exceed the maximum speed of a bullet, the new
	 * 			maximum speed of this bullet is the given speed. Otherwise, the new maximum speed of
	 * 			this bullet is MAX_SPEED.
	 * 			| if maxSpeed <= MAX_SPEED
	 * 			| 	new.getMaxSpeed == maxSpeed
	 * 			| else
	 * 			| 	new.getMaxSpeed == MAX_SPEED
	 * @post	If the current velocity of this bullet exceeds its maximum value, the velocity of
	 * 			this bullet is set to its maximum value. 
	 * 			| if (old.getSpeed() > new.getMaxSpeed())
	 *			| 	new.getXVelocity == new.getMaxSpeed()*Math.cos(old.getOrientation()), 
	 *			| 	new.getYVelocity == new.getMaxSpeed()*Math.sin(old.getOrientation());
	 */
	@Raw
	public void setMaxSpeed(double maxSpeed) {
		if (Math.abs(maxSpeed) > MAX_SPEED)
			this.maxSpeed = MAX_SPEED;
		else
			this.maxSpeed = maxSpeed;
		this.setVelocity(this.getXVelocity(), this.getYVelocity());
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
		return DENSITY;
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
	 * @throws	IllegalBulletException
	 * 			This bullet is already located in a world/loaded by as ship/fired by a ship.
	 * 			| this.hasPosition()
	 */
	public void setShip(Ship ship) throws IllegalBulletException{
		if (ship==null)
			this.ship=null;
		else if (this.hasPosition())
			throw new IllegalBulletException(this);
		else
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
	 * @param 	world
	 * 			The world, in which this bullet has to be located.
	 * @post	If the bullet does not belong to a world/ship yet and if the given world already associates 
	 * 			the bullet with itself, the new world of this bullet is the given world.
	 * 			| if (!old.hasPosition())&&(world.getEntities().contains(this))
	 *			|	new.world == world;		
	 * @throws	IllegalBulletException
	 * 			This bullet is already located in a world/loaded by as ship/fired by a ship.
	 * 			| this.hasPosition()
	 */
	public void setWorld(World world) throws IllegalBulletException{
		if (world==null)
			this.world=null;
		if (this.hasPosition())
			throw new IllegalBulletException(this);
		this.world = world;
	}
	
	/**
	 * Return whether this bullet is located in a world/ship.
	 */
	public boolean hasPosition() {
		return ((this.getWorld()!=null)||(this.getShip()!=null)||(this.getSource()!=null));
	}
	
	/**
	 * Return the ship that fired this bullet.
	 */
	public Ship getSource() {
		return this.source;
	}
	
	/**
	 * Set the source of this bullet to a given ship.
	 * 
	 * @param	source
	 * 			The ship that fired this bullet.
	 * @post	The source of this bullet is the given ship.
	 * 			| new.getSource() == source
	 * @throws 	IllegalBulletException
	 * 			This bullet is already located in a world/loaded by as ship/fired by a ship.
	 * 			| this.hasPosition()
	 */
	public void setSource(Ship source) throws IllegalBulletException {
		if (source==null)
			this.source=null;
		else if (this.hasPosition())
			throw new IllegalBulletException(this);
		else
			this.source = source;
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
		if (radius <= MIN_RADIUS)
			throw new IllegalRadiusException(radius);
		this.radius = radius;
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
	 * A variable registering in which ship this bullet is loaded. Null if none.
	 */
	private Ship ship = null;
	
	/**
	 * A variable registering in which world this bullet is loaded. Null if none.
	 */
	private World world = null;
	
	/**
	 * A variable registering the ship that fired this bullet. Null if none.
	 */
	private Ship source = null;
	
	/**
	 * A variable registering the maximum speed of a bullet.
	 */
	private static double MAX_SPEED = 300000;
	
	/**
	 * A variable registering the minimum radius of a bullet.
	 */
	private static double MIN_RADIUS = 1;
	
	/**
	 * A variable registering the mass density of a bullet.
	 */
	private static double DENSITY = 7.8*Math.pow(10,12);

}
