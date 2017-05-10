package asteroids.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;

/**
 * @invar	Each bullet has a valid position.	
 * 			| isValidPosition(getXPosition() ,getYPosition())
 * @invar	Each bullet cannot be located in a world and a ship at the same time.
 * 			| if (getWorld()!=null)
 * 			| 	getShip() == null
 * 			| else if (getShip()!=null)
 * 			| 	getWorld() == null
 */
public class Bullet extends Entity{
	
	/**
	 * Create a new bullet with given position, velocity and radius.
	 * 
	 * @param	xpos
	 * 			The position for this bullet along the x-axis.
	 * @param	ypos 
	 * 			The position for this bullet along the y-axis.
	 * @param	xvel
	 * 			The velocity for this bullet along the x-axis.
	 * @param	yvel
	 * 			The velocity for this bullet along the y-axis.
	 * @param	radius
	 * 			The radius for this ship.
	 * @post	The new position for this new bullet is equal to the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @post	The new velocity for this new bullet is equal to the given velocity.
	 * 			| new.getXVelocity() == xvel
	 * 			| new.getYVelocity() == yvel
	 * @post	The new bullet is not located in a world.
	 * 			| new.getWorld() == null
	 * @post	The new bullet has no owner.
	 * 			| new.getShip() == null
	 * @post	The new bullet has not been fired by a ship.
	 * 			| new.getSource() == null
	 * @post	The new maximum speed for this new bullet is equal to its upper bound.
	 * 			| new.getMaxSpeed() == MAX_SPEED
	 * @post	The new radius for this new bullet is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| ! isValidPosition(xpos,ypos)
	 */
	public Bullet(double xpos, double ypos, double xvel, double yvel, double radius) 
			throws IllegalPositionException {
		super(xpos,ypos,xvel,yvel,radius);
		setShip(null);
		setSource(null);
		setMassDensity(DENSITY);
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
	 *			|	||(((ypos == Double.POSITIVE_INFINITY)||(ypos == Double.NEGATIVE_INFINITY)||(Double.isNaN(ypos)))))
	 *			|	then result == false
	 * @return	If this bullet is not associated with a world, this method returns true. If it is associated
	 * 			with a world, the method returns true if and only if the bullet is located within bounds of its
	 * 			world and does not overlap with another entity in its world.
	 * 			| if this.getWorld() == null
	 * 			|	result == true
	 * 			|	else
	 * 			| 		result == (this.getRadius()<xpos<this.getWorld().getWidth()-getRadius())&&
	 * 			| 			(this.getRadius()<ypos<this.getWorld().getHeight()-getRadius())&&
	 * 			| 			(for each entity in this.getWorld().getEntities(): !this.overlap(entity))
	 * @return	If this bullet is associated with a ship, this method returns true.
	 * 			| if this.getShip() != null
	 * 			| 	result == true
	 */
	@Override
	public boolean isValidPosition(double xpos, double ypos) {
		if (this.getWorld()==null)
			return true;
		if (this.getShip()!=null)
			return true;
		return super.isValidPosition(xpos, ypos);
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
	 * Set the mass density of this bullet to a given value.
	 */
	public void setMassDensity(double massDensity) {
		this.massDensity=DENSITY;
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
	@Override
	public World getWorld() {
		if (this.world!=null)
			return this.world;
		if (this.getSource()!=null)
			return this.getSource().getWorld();
		else
			return null;
	}
	
	/**
	 * Locate this bullet in the given world.
	 * 
	 * @param 	world
	 * 			The world, in which this bullet has to be located.
	 * @post	If the bullet does not belong to a world/ship yet, the new world of this bullet 
	 * 			is the given world.
	 * 			| if (!old.hasPosition())
	 *			|	new.getWorld() == world;		
	 * @throws	IllegalBulletException
	 * 			This bullet is already located in a world/loaded by as ship/fired by a ship.
	 * 			| this.hasPosition()
	 */
	@Override
	public void setWorld(World world) throws IllegalBulletException{
		if (world==null) {
			this.world=null;
			if (getSource()!=null) {
				this.setSource(null);
			}
		}
		else if (this.hasPosition())
			throw new IllegalBulletException(this);
		this.world = world;
	}
	
	/**
	 * Return whether this bullet is located in a world/ship.$
	 * 
	 * @return	True if and only if this bullet is not located in a world, not fired by a ship
	 * 			and not loaded by a ship.
	 * 			| result == (getWorld()!=null)||(getShip()!=null)
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
	 * 			| new.getSource() == source
	 * @throws 	IllegalBulletException
	 * 			This bullet is already located in a world/loaded by as ship/fired by a ship.
	 * 			| this.hasPosition()
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
	 * 			| new.getRadius() == radius
	 * @throws	IllegalRadiusException
	 * 			The given radius does not exceed its minimum value.
	 * 			| radius < MIN_RADIUS
	 */
	public void setRadius(double radius) {
		if (radius <= MIN_RADIUS)
			throw new IllegalRadiusException(radius);
		this.radius = radius;
	}
	
	/**
	 * Return the number of times this bullet has bounced off the boundaries of its world, if any.
	 */
	public int getBounces() {
		return this.bounces;
	}
	
	/**
	 * Set the number of times this bullet has bounces of the boundaries of its world to a given value.
	 * If this given value exceeds the maximum number of times this bullet can bounce, this bullet
	 * is terminated.
	 * 
	 * @param	bounces
	 * 			The new number of bounces of this bullet.
	 * @post	The new number of bounces this bullet has done is equal to the given number 
	 * 			of bounces. If this number exceeds the maximum number of bounces, this bullet is
	 * 			terminated.
	 * 			| if (bounces >= old.getMaxBounces())
	 * 			| 	new.isTerminated()
	 * 			| else
	 * 			| 	new.getNbBullets() == bounces
	 */
	public void setBounces(int bounces) {
		if (bounces >= this.getMaxBounces())
			this.terminate();
		else
			this.bounces = bounces;
	}
	
	/**
	 * Return the maximum number of times this bullet can bounce of the boundaries of its world.
	 */
	public int getMaxBounces() {
		return this.maxBounces;
	}
	
	/**
	 * Set the maximum number of bounces of this bullet to a given value.
	 * 
	 * @param	bounces
	 * 			The new maximum number of bounces.
	 * @post	If the given number of bounces is larger than 0, the maximum number of bounces
	 * 			of this bullet is set to the given value. Otherwise, it is set to zero.
	 * 			| if (bounces>0)
	 * 			| 	new.getMaxBounces()==bounces
	 * 			| else
	 * 			| 	new.getMaxBounces()==0
	 */
	public void setMaxBounces(int bounces) {
		if (bounces<0)
			this.maxBounces=0;
		else
			this.maxBounces=bounces;
	}
	
	/**
	 * Terminate this bullet.
	 * 
	 * @post	If this bullet was located in a world, it is not anymore.
	 * 			| if old.getWorld()!=null
	 * 			| 	! old.getWorld().getEntities().contains(entity)
	 * 			| 	! old.getWorld().getBullets().contains(old)
	 * 			| 	new.getWorld() == null
	 * @post	If this bullet was associated with a ship, it is not anymore.
	 * 			| if old.getShip != null
	 * 			| 	if old.getShip().getBullets().contains(old)
	 * 			| 		! new.getShip().getBullets().contains(old)
	 * 			| 	new.getShip() == null
	 * @post	If this bullet was shot by a ship, it is not anymore.
	 * 			| new.getSource() == null
	 * 			| if (old.getSource()!=null)
	 * 			| 	! new.getSource().getBulletsFired().contains(new)
	 */
	public void terminate() {
		this.isTerminated = true;
		if (this.getWorld()!=null) {
			this.getWorld().removeEntity(this);
		}
		if (this.getShip()!=null) {
			this.getShip().getBullets().remove(this);
			this.setShip(null);
		}
		if (this.getSource()!=null) {
			this.getSource().getBulletsFired().remove(this);
		}
		setSource(null);
	}
	
	/**
	 * Move this bullet for a given amount of time according to its current position, velocity 
	 * and orientation.
	 * 
	 * @param 	dt
	 * 			The duration of the movement.
	 * @post	The new position of the bullet is the position it reaches if starts from its old position
	 * 			and its orientation and velocity do not change during the movement.
	 * 			| new.getXPosition() == old.getXPosition() + dt*old.getXVelocity()
	 * 			| new.getYPosition() == old.getYPosition() + dt*old.getYVelocity()
	 * @throws 	IllegalDurationException
	 * 			The given duration of the movement is negative.
	 * 			| dt < 0
	 */
	@Override
	public void move(double dt) throws IllegalDurationException {
		if (dt < 0)
			throw new IllegalDurationException(dt);
		if (this.getShip()==null) {
			super.move(dt);
		}
	}
	
	public static Set<Bullet> getAllBullets(World world) {
		Set<Bullet> set = new HashSet<Bullet>();
		Stream<Entity> stream = world.getAllEntities().stream().filter(entity -> (entity instanceof Bullet));
		stream.forEach(bullet -> set.add((Bullet)bullet));
		return set;
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
	 * A variable registering the number of times this bullet has bounced off the boundaries
	 * of its world.
	 */
	private int bounces = 0;
	
	/**
	 * A variable registering the maximum number of times this bullet can bounce of the boundaries 
	 * of its world. 
	 */
	private int maxBounces = 3;
	
	/**
	 * A variable registering the minimum radius of a bullet.
	 */
	private static double MIN_RADIUS = 1;
	
	/**
	 * A variable registering the mass density of a bullet.
	 */
	private static double DENSITY = 7.8*Math.pow(10,12);

}
