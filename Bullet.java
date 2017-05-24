package asteroids.model;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of bullets involving a position, velocity and radius.
 * 
 * @invar	Each bullet cannot be located in a world and associated with a ship at the 
 * 			same time.
 * 			| if (getWorld()!=null)
 * 			| 	getShip() == null
 * 			| else if (getShip()!=null)
 * 			| 	getWorld() == null
 */
public class Bullet extends Entity{
	
	/**
	 * Create a new bullet with given position, velocity and radius.
	 * 
	 * @post	The new bullet is not located in a world.
	 * 			| new.getWorld() == null
	 * @post	The new bullet has no owner.
	 * 			| new.getShip() == null
	 * @post	The new bullet has not been fired by a ship.
	 * 			| new.getSource() == null
	 * @post	The new radius for this new bullet is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The new mass density of this bullet is set to DENSITY.
	 * 			| new.getMassDensity() == DENSITY.
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| ! isValidPosition(xpos,ypos)
	 */
	public Bullet(double xpos, double ypos, double xvel, double yvel, double radius) 
			throws IllegalPositionException, IllegalRadiusException {
		super(xpos,ypos,xvel,yvel,radius);
		setShip(null);
		setSource(null);
		setMassDensity(DENSITY);
	}
	
	/**
	 * Checks whether the given position is a valid position for this bullet.
	 * 
	 * @return	True if and only if 
	 * 			nor xpos, nor ypos is positive of negative infinity or not a number
	 * 			and 
	 * 			this bullet is not associated with a world
	 * 				or this bullet is loaded on a ship
	 * 				or this bullet lies fully within the boundaries of its world.
	 * 					and no entity in its world overlaps with this bullet.
	 * 			| result == (! xpos == Double.POSITIVE_INFINITY)&&(! xpos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(xpos))
	 *			| 	&& (! ypos == Double.POSITIVE_INFINITY)&&(! ypos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(ypos))
	 *			|	&& (
	 *			|	(this.getWorld() == null)
	 *			| 	|| (this.getShip()!=null) 
	 *			| 	|| (
	 *			| 	(xpos>0.99*getRadius())&&(xpos<1.01*(getWorld().getWidth()-getRadius()))
	 *			| 	&& (ypos>0.99*getRadius())&&(ypos<1.01*(getWorld().getHeight()-getRadius()))
	 *			| 	&& (for each entity in getWorld().getEntities():
	 *			| 		(entity == this)
	 *			| 		|| ((Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
	 *			|					Math.pow(ypos-entity.getYPosition(),2)))>=
	 *			|					0.99*(entity.getRadius()+getRadius())))
	 *			|	)
	 *			|	)
	 */
	@Override @Raw
	public boolean isValidPosition(double xpos, double ypos) {
		if (this.getShip()!=null)
			return true;
		if ((Double.isNaN(xpos))||(Double.isNaN(ypos)))
			return false;
		if (getWorld()!=null) {
			if ((xpos>0.99*getRadius())&&(xpos<1.01*(getWorld().getWidth()-getRadius()))&&
					(ypos>0.99*getRadius())&&(ypos<1.01*(getWorld().getHeight()-getRadius()))) {
				for (Entity entity: getWorld().getEntities().keySet()) {
					if ((entity!=this)&&
							(Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
									Math.pow(ypos-entity.getYPosition(),2)))<
									0.99*(entity.getRadius()+getRadius())) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return true;
	}
	
	
	/**
	 * Return whether this bullet is associated with a world/ship.
	 * 
	 * @return	True if and only if this bullet is not located in a world, not fired by a ship
	 * 			and not loaded by a ship.
	 * 			| result == (getWorld()!=null)||(getShip()!=null)||(getSource()!=null)
	 */
	public boolean hasPosition() {
		return ((this.getWorld()!=null)||(this.getShip()!=null)||(this.getSource()!=null));
	}

	/**
	 * Return whether the given radius is valid for this bullet.
	 * 
	 * @result 	True if and only if radius exceeds MIN_RADIUS.
	 * 			| result = (radius >= MIN_RADIUS)
	 */
	@Override
	public boolean isValidRadius(double radius) {
		return (radius >= MIN_RADIUS);
	}

	/**
	 * Return the mass of this bullet.
	 * 
	 * @return	| result == (4.0/3.0)*Math.PI*Math.pow(this.getRadius(),3)*this.getMassDensity()
	 */
	public double getMass() {
		return (4.0/3.0)*Math.PI*Math.pow(this.getRadius(),3)*this.getMassDensity();
	}
	
	/**
	 * Return whether the given density is valid for this bullet.
	 * 	
	 * @return	True if and only if density equals DENSITY.
	 * 			| result == (density == DENSITY)
	 */
	@Override
	public boolean isValidDensity(double density) {
		return (density == DENSITY);
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
	 * @post	If the given ship is ineffective, the ship of this bullet is set to null.
	 * 			| if (ship == null)
	 * 			| 	new.getShip()==null
	 * @post	This bullet is loaded in the given ship.
	 * 			| new.ship == ship
	 * @throws	IllegalBulletException
	 * 			This bullet is already located in a world/loaded by as ship/fired by a ship.
	 * 			| this.hasPosition()
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
	 * 
	 * @return	If this bullet is associated with a world, that world is returned.
	 * 			If this bullet is associated with a source, the world associated
	 * 			with that source is returned. 
	 * 			Otherwise, null is returned.
	 * 			| if (getWorld()!=null)
	 * 			| 	result == getWorld()
	 * 			| if (getSource()!=null)
	 * 			| 	result == getSource().getWorld()
	 * 			| else
	 * 			| 	result == null
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
	 * @post	If the given world is ineffective, the world of this bullet is set to null.
	 * 			| if (world == null)
	 * 			| 	new.getWorld() == null
	 * @post	The new world of this bullet is the given world.
	 * 			| new.getWorld() == world;		
	 * @throws	IllegalBulletException
	 * 			This bullet is already located in a world/loaded by as ship/fired by a ship.
	 * 			| old.hasPosition()
	 */
	@Override @Raw
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
	 * 			| if (bounces >= old.getMaxBounces())
	 * 			| 	new.isTerminated()
	 * 			| else
	 * 			| 	new.getNbBullets() == bounces
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
	 * 			| 	new.getMaxBounces()==bounces
	 * 			| else
	 * 			| 	new.getMaxBounces()==0
	 */
	public void setMaxBounces(int bounces) {
		if (bounces<0)
			this.maxBounces=0;
		else
			this.maxBounces=bounces;
	}
	
	/**
	 * Resolve the collision of this bullet with a boundary of its world.
	 * 
	 * @effect	The number of bounces of this bullet is increased by 1.
	 * 			| setBounces(getBounces+1)
	 */
	public void collideBoundary() {
		super.collideBoundary();
		this.setBounces(getBounces()+1);
	}
	
	/**
	 * Return whether this bullet has been terminated in a loop.
	 */
	public boolean isLoopTerminated() {
		return this.loopTerminated;
	}
	
	/**
	 * Terminate this bullet in a loop.
	 * 
	 * @post	This bullet is terminated in a loop.
	 * 			| new.isLoopTerminated() == true
	 */
	public void setLoopTerminated() {
		this.loopTerminated = true;
	}
	
	/**
	 * Terminate this bullet.
	 * 
	 * @post	If this bullet was located in a world, it is not anymore.
	 * 			| if old.getWorld()!=null
	 * 			| 	! old.getWorld().getEntities().contains(entity)
	 * 			| 	! old.getWorld().getBullets().contains(old)
	 * 			| 	new.getWorld() == null
	 * @post	If this bullet was associated with a ship, it is not anymore.
	 * 			| if old.getShip != null
	 * 			| 	if old.getShip().getBullets().contains(old)
	 * 			| 		! new.getShip().getBullets().contains(old)
	 * 			| 	new.getShip() == null
	 * @post	If this bullet was shot by a ship, it is not anymore.
	 * 			| new.getSource() == null
	 * 			| if (old.getSource()!=null)
	 * 			| 	! new.getSource().getBulletsFired().contains(new)
	 */
	public void terminate() {
		super.terminate();
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
	 * A variable registering whether this bullet was terminated in a loop.
	 */
	private boolean loopTerminated = false;
	
	/**
	 * A variable registering the minimum radius of a bullet.
	 */
	private static double MIN_RADIUS = 1;
	
	/**
	 * A variable registering the mass density of a bullet.
	 */
	private final static double DENSITY = 7.8*Math.pow(10,12);

}