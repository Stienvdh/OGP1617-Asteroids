package asteroids.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.exceptions.IllegalOrientationException;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.expressions.DoubleLiteralExpression;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of space ships involving a position, a velocity, a radius,
 * a mass, an acceleration and an orientation.
 * 
 * @invar	Each ship has a valid orientation.
 * 			| isValidOrientation(getOrientation())
 */
public class Ship extends Entity{
	
	/**
	 * Initialize this new ship with given position, velocity, radius and orientation.
	 * 
	 * @param	orientation
	 * 			The orientation for this ship.
	 * @param	mass
	 * 			The mass for this ship
	 * @pre		This ship can have the given orientation as its orientation.
	 * 			| isValidOrientation(orientation)
	 * @post	The new orientation for this new ship is equal to the given orientation.
	 * 			| new.getOrientation() == orientation
	 * @post	The new mass for this new ship is equal to the given mass.
	 * 			| new.getMass() == mass
	 * @post	The new mass density for this new ship is equal to its lower bound.
	 * 			| new.getMassDensity() == MIN_DENSITY
	 * @post	The new thrust force for this new ship is equal to its standard value.
	 * 			| new.getThrustForce() == STANDARD_FORCE
	 * @post	The new ship is not located in a world.
	 * 			| new.getWorld() == null
	 * @post	The thruster of the new ship is not activated.
	 * 			| new.thrusterEnabled() == false 
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| (! isValidPosition(xpos, ypos))
	 * @throws 	IllegalRadiusException
	 * 			The given radius does not exceed MIN_RADIUS.
	 * 			| (! isValidRadius(radius))
	 */
	@Raw
	public Ship(double xpos, double ypos, double xvel, double yvel,double radius, double orientation, 
			double mass) 
			throws IllegalRadiusException, IllegalPositionException {
		super(xpos,ypos,xvel,yvel,radius);
		setOrientation(orientation);
		setMassDensity(MIN_DENSITY);
		setMass(mass);
		setThrustForce(STANDARD_FORCE);
		thrustOff();
	}
	
	/**
	 * Initialize this new ship with no given position, velocity, radius or orientation.
	 * 
	 * @effect	| this(0.0,0.0,0.0,0.0,MIN_RADIUS,0.0,0.0)
	 */
	@Raw
	public Ship() {
		this(0.0,0.0,0.0,0.0,MIN_RADIUS,0.0,0.0);
	}
	
	/**
	 * Set the position of this ship to a given value.
	 * 
	 * @post	The bullets, loaded on this ship, have the same new position as this ship.
	 * 			| (for each bullet: new.getBullets()
	 * 			| 	(new bullet).getXPosition()==xpos
	 * 			| 	(new bullet).getYPosition()==ypos
	 */
	@Override @Basic @Raw
	public void setPosition(double xpos, double ypos) {
		super.setPosition(xpos, ypos);
		if (getBullets()!=null) {
			for (Bullet bullet: getBullets()) {
				bullet.setPosition(xpos, ypos);
			}
		}
	}

	/**
	 * Return whether the given position is a valid position for this ship.
	 * 
	 * @return	True if and only if 
	 * 			nor xpos, nor ypos is positive of negative infinity or not a number
	 * 			and 
	 * 			this ship is not associated with a world
	 * 				or this ship lies fully within the boundaries of its world.
	 * 					and no entity in its world overlaps with this ship.
	 * 			| result = (! xpos == Double.POSITIVE_INFINITY)&&(! xpos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(xpos))
	 *			| 	&& (! ypos == Double.POSITIVE_INFINITY)&&(! ypos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(ypos))
	 *			|	&& (
	 *			|	(this.getWorld() == null)
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
		if ((Double.isNaN(xpos)) || (Double.isNaN(ypos)))
			return false;
		if (this.getWorld() != null) {
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
	 * Return whether this ship is located in a world.
	 * 
	 * @result	Returns true if and only if this ship is located in a world.
	 * 			| result == (this.getWorld()!=0)
	 */
	public boolean hasPosition() {
		return this.getWorld() != null;
	}

	/**
	 * Set the velocity of this ship to a given value.
	 * 
	 * @post	The bullets, loaded on this ship, have the same new velocity as this ship.
	 * 			| (for each bullet: new.getBullets()
	 * 			| 	(new bullet).getXVelocity()==xvel
	 * 			| 	(new bullet).getYVelocity()==yvel
	 */
	@Override @Basic @Raw
	public void setVelocity(double xvel, double yvel) {
		super.setVelocity(xvel, yvel);
		if (getBullets() != null) {
			if (! getBullets().isEmpty()) {
				for (Bullet bullet: getBullets()) {
					bullet.setVelocity(xvel, yvel);
				}
			}
		}
	}

	/**
	 * Checks whether the given radius is a valid radius.
	 * 
	 * @return	True if and only if the radius exceeds its minimal value.
	 * 			| result == (MIN_RADIUS >= radius)
	 */
	@Override
	public boolean isValidRadius(double radius){
		return (radius >= MIN_RADIUS);
	}

	/**
	 * Return the acceleration of this ship.
	 */
	@Basic @Raw
	public double getAcceleration() {
		return this.acceleration;
	}
	
	/**
	 * Set the acceleration of this ship to a given acceleration.
	 * 
	 * @param	acc
	 * 			The new acceleration of this ship
	 * @post	The new acceleration of this ship is equal to the given acceleration.
	 * 			| new.getAcceleration == acc
	 */
	@Raw
	public void setAcceleration(double acc) {
		this.acceleration = acc;
	}
	
	/**
	 * Return the orientation of the ship.
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Set the orientation of the ship to a given orientation.
	 * 
	 * @param	orientation
	 * 			The new orientation of the ship.
	 * @pre		This ship can have the given orientation as orientation.
	 * 			| isValidOrientation(orientation)
	 * @post	The new orientation for this ship is the given orientation.
	 * 			| new.getOrientation == orientation
	 */
	@Raw
	public void setOrientation(double orientation) throws IllegalOrientationException {
		try {
			assert isValidOrientation(orientation);
			this.orientation = orientation;
		}
		catch (AssertionError exc) {
			throw new IllegalOrientationException(orientation);
		}
	}
	
	/**
	 * Checks whether the given orientation is a valid orientation
	 * 
	 * @param 	orientation
	 * 			The orientation to check.
	 * @return	True if and only if the orientation is a value between zero and 2*PI.
	 * 			| result == (0 <= orientation <= 2*PI)
	 */
	@Raw
	public boolean isValidOrientation(double orientation) {
		return ((0<=orientation)&&(orientation<=2*Math.PI));
	}
	
	/**
	 * Return the mass of this ship.
	 */
	@Basic @Raw
	public double getShipMass() {
		return this.mass;
	}
	
	/**
	 * Set the mass of this ship to the given mass.
	 * 
	 * @param	mass
	 * 			The new mass of the ship.
	 * @post	If the given mass is at least 4/3*PI*radius^3*density, the new mass of the ship is
	 * 			the given mass. Otherwise, the mass is set to its minimal value.
	 * 			| if (mass >= (4/3.)*(Math.PI)*Math.pow(old.getRadius(), 3)*old.getMassDensity())
	 *			|	new.mass == mass;
	 *			| else
	 *			|	new.mass == (4/3.)*(Math.PI)*Math.pow(old.getRadius(), 3)*old.getMassDensity();
	 */
	@Raw
	public void setMass(double mass) {
		if (mass >= (4/3.)*(Math.PI)*Math.pow(this.getRadius(), 3)*this.getMassDensity())
			this.mass = mass;
		else
			this.mass = (4/3.)*(Math.PI)*Math.pow(this.getRadius(), 3)*this.getMassDensity();
	}
	
	/**
	 * Return the total mass of this ship.
	 * 
	 * @result	The resulted mass is equal to the sum of the mass of the ship and the sum
	 * 			of the masses of the bullets the ship has loaded.
	 *			| totalMass = this.getMass()
	 *			| for (Bullet bullet: this.getBullets())
	 *			|	totalMass = totalMass + bullet.getMass()
	 *			| result == totalMass
	 */
	@Override
	public double getMass() {
		double totalMass = this.getShipMass();
		for (Bullet bullet: this.getBullets()) {
			totalMass = totalMass + bullet.getMass();
		}
		return totalMass;
	}
	
	
	/**
	 * Return whether the given density is valid for this ship.
	 * 
	 * @return	True if and only if density exceeds MIN_DENSITY.
	 * 			| result == density >= MIN_DENSITY)
	 */
	@Override
	public boolean isValidDensity(double density) {
		return (density >= MIN_DENSITY);
	}
	
	/**
	 * Return the bullets, loaded on this ship.
	 */
	public Set<Bullet> getBullets() {
		return this.bullets;
	}
	
	/**
	 * Return a copy of the set of bullets, loaded on this ship.
	 */
	public Set<Bullet> getCurrentBullets() {
		return new HashSet<Bullet>(this.bullets);
	}
	
	/**
	 * Remove the given bullet from the ship.
	 * 
	 * @param	bullet
	 * 			The bullet that has to be removed from this ship.
	 * @post	This ship does not have the given bullet loaded.
	 * 			| ! new.getBullets().contains(bullet)
	 * @post	The given bullet is no longer associated with any ship.
	 * 			| (new bullet).getShip() == null
	 * @throws	IllegalBulletException
	 * 			This ship does not contain the given bullet.
	 * 			| ! this.getBullets().contains(bullet)
	 */
	public void removeBullet(Bullet bullet) throws IllegalBulletException {
		if (!this.getBullets().contains(bullet))
			throw new IllegalBulletException(bullet);
		this.bullets.remove(bullet);
		bullet.setShip(null);
	}
	
	/**
	 * Return the total number of bullets, loaded in this ship.
	 * 
	 * @result	The returned integer is equal to the number of bullets, loaded in this ship.
	 * 			| result == this.getBullets().size()
	 */
	public int getNbBullets() {
		return this.getBullets().size();
	}
	
	/**
	 * Load this ship with a given bullet.
	 * 
	 * @param	bullet
	 * 			The bullet to load this ship with.
	 * @post	This ship is loaded with the given bullet.
	 * 			| new.getBullets().contains(bullet)
	 * 			| new.getNbBullets() == old.getNbBullets() + 1
	 * @post	The given bullet is loaded in the given ship.
	 * 			| (new bullet).getShip() == new
	 * 			| (new bullet).getXPosition() == old.getXPosition()
	 * 			| (new bullet).getYPosition() == old.getYPosition()
	 * 			| (new bullet).getXVelocity() == old.getXVelocity()
	 * 			| (new bullet).getYVelocity() == old.getYVelocity()
	 * @post	If the given bullet was associated with a world, it no longer is.
	 * 			| if ((old bullet).getWorld()!=null)
	 *			|	(new bullet).getWorld() == null
	 * @post	This ship no longer fired this bullet.
	 * 			| ! new.getBulletsFired().contains(bullet)
	 * @post	The given bullet has not bounced of the boundaries of its world.
	 * 			| (new bullet).getBounces() == 0
	 * @throws	IllegalBulletException
	 * 			This ship and the given bullet both belong to a world, and these worlds are
	 * 			not the same.
	 * 			| (this.getWorld()!=null)&&(bullet.getWorld()!=null)
	 * 			| 	&&(this.getWorld()!=bullet.getWorld())
	 * @throws 	IllegalBulletException
	 * 			The given bullet does not lie fully within this ship.
	 * 			| Math.sqrt(Math.pow(bullet.getXPosition()-this.getXPosition(), 2)+
	 *			| Math.pow(bullet.getYPosition()-this.getYPosition(), 2))
	 *			| +bullet.getRadius() > this.getRadius()
	 */
	public void loadBullet(Bullet bullet) throws IllegalBulletException {
		if (((bullet.getWorld()!=null)&&(this.getWorld()!=bullet.getWorld())))
			throw new IllegalBulletException(bullet);
		if (bullet.getWorld()!=null)
			bullet.getWorld().removeEntity(bullet);
		if (Math.sqrt(Math.pow(bullet.getXPosition()-this.getXPosition(), 2)+
				Math.pow(bullet.getYPosition()-this.getYPosition(), 2))
				+bullet.getRadius() > this.getRadius()
				)
			throw new IllegalBulletException(bullet);
		bullet.setSource(null);
		if (bullet.getShip()==null)
			bullet.setShip(this);
		this.addBullet(bullet);
		bullet.setPosition(getXPosition(), getYPosition());
		bullet.setVelocity(getXVelocity(), getYVelocity());
		bullet.setBounces(0);
		this.getBulletsFired().remove(bullet);
	}	
	
	/**
	 * Load this ship with a given series of bullets.
	 * 
	 * @param	bullets
	 * 			The bullets to load this ship with.
	 * @post	This ship is loaded with the given bullets.
	 * 			| for each (bullet: bullets)
	 * 			|  	new.getBullets().contains(bullet)
	 * 			| new.getNbBullets() == old.getNbBullets() + bullets.size()
	 * @post	The given bullet is loaded in the given ship.
	 * 			| for each (bullet: bullets)
	 * 			| 	(new bullet).getShip() == new
	 * 			| 	(new bullet).getXPosition() == old.getXPosition()
	 * 			| 	(new bullet).getYPosition() == old.getYPosition()
	 * 			| 	(new bullet).getXVelocity() == old.getXVelocity()
	 * 			| 	(new bullet).getYVelocity() == old.getYVelocity()
	 * @post	If a given bullet was associated with a world, it no longer is.
	 * 			| for each (bullet: bullets)
	 * 			| 	if ((old bullet).getWorld()!=null)
	 *			|		(new bullet).getWorld() == null
	 * @post	This ship does not fire the given bullets anymore.
	 * 			| for each (bullet: bullets)
	 * 			|	 ! new.contains(new bullet)
	 * @post	The given bullets have not bounced of the boundaries of their world.
	 * 			| for each bullet: bullets
	 * 			| 	(new bullet).getBounces() == 0
	 * @throws	IllegalBulletException
	 * 			This ship and a given bullet both belong to a world, and these worlds are
	 * 			not the same.
	 * 			| for a (bullet: bullets)
	 * 			| 	(this.getWorld()!=null)&&(bullet.getWorld()!=null)
	 * 			| 	&&(this.getWorld()!=bullet.getWorld())
	 * @throws	IllegalBulletException
	 * 			A given bullet does not lie fully within this ship.
	 * 			| for a (bullet: bullets)
	 * 			|  	Math.sqrt(Math.pow(bullet.getXPosition()-this.getXPosition(), 2)+
	 *			| 	Math.pow(bullet.getYPosition()-this.getYPosition(), 2))
	 *			| 	+bullet.getRadius() > this.getRadius()
	 */
	public void loadBullet(Collection<Bullet> bullets) throws IllegalBulletException {
		for (Bullet bullet: bullets) {
			if (bullet != null) {
				if ((this.getWorld()!=null)&&(bullet.getWorld()!=null)&&(this.getWorld()!=bullet.getWorld()))
					throw new IllegalBulletException(bullet);
				if (bullet.getWorld()!=null)
					bullet.getWorld().removeEntity(bullet);
				if (Math.sqrt(Math.pow(bullet.getXPosition()-this.getXPosition(), 2)+
						Math.pow(bullet.getYPosition()-this.getYPosition(), 2))
						+bullet.getRadius() > this.getRadius()
						)
					throw new IllegalBulletException(bullet);
				this.addBullet(bullet);
				bullet.setSource(null);
				if (bullet.getShip()==null)
					bullet.setShip(this);
				bullet.setPosition(getXPosition(), getYPosition());
				bullet.setVelocity(getXVelocity(), getYVelocity());
				bullet.setBounces(0);
				this.getBulletsFired().remove(bullet);
			}
			else
				throw new IllegalBulletException(bullet);
		}
	}
	
	/**
	 * Add a given bullet to the collection of bullets, loaded on this ship.
	 * 
	 * @param 	bullet
	 * 			The bullet that has to be loaded.
	 * @post	The collection of bullets, loaded on this ship contains the given bullet.
	 * 			| new.getBullets().contains(bullet)
	 */
	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
	}
	
	/**
	 * Fire a bullet.
	 * 
	 * @post	This ship has one less bullet loaded.
	 * 			| new.getNbBullets() == old.getNbBullets() - 1
	 * @post	The fired bullet has this ship as its source.
	 * 			| (new removed bullet).getSource() == new;
	 * 			| (new removed bullet).getWorld() == new.getWorld();
	 * @post	The speed of the fired bullet is equal to INITIAL_SPEED.
	 * 			| (new removed bullet).getXVelocity() == INITIAL_SPEED*Math.cos(old.getOrientation())
	 * 			| (new removed bullet).getYVelocity() == INITIAL_SPEED*Math.sin(old.getOrientation())
	 * @post	The fired bullet is positioned next to this ship.
	 * 			| (new removed bullet).getXPosition() == old.getXPosition()+(old.getRadius()+
	 * 			| 	2*(old removed bullet).getRadius())*Math.cos(old.getOrientation())
	 * 			| (new removed bullet).getYPosition() == old.getYPosition()+(old.getRadius()+
	 * 			| 	2*(old removed bullet).getRadius())*Math.sin(old.getOrientation())
	 * @post	If the new position of the fired bullet is out of bounds of its world, 
	 * 			the fired bullet is terminated.
	 * 			| if (((new removed bullet).getXPosition()<0.99*(new removed bullet).getRadius())||
	 *			|	((new removed bullet).getXPosition()>1.01*(old.getWorld().getWidth()-
	 *			| 	(new removed bullet).getRadius()))||
	 *			|	((new removed bullet).getYPosition()<0.99*(new removed bullet).getRadius())||
	 *			|	((new removed bullet).getYPosition()>1.01*(old.getWorld().getHeight()-
	 *			|	(new removed bullet).getRadius())))
	 *			| 	then (new removed bullet).isTerminated()
	 * @post	If the fired bullet overlaps with another entity in its world, the bullet and the
	 * 			other entity are terminated.
	 * 			| for (entity: getWorld().getEntities().values())
	 *			|	if ((entity!=(new removed bullet))&&(entity!=old)&&
	 *			|			((Math.sqrt(Math.pow((new removed bullet).getXPosition()-entity.getXPosition(),2)+
	 *			|			Math.pow((new removed bullet).getYPosition-entity.getYPosition(),2)))<
	 *			|			0.99*(entity.getRadius()+(new removed bullet).getRadius())))
	 *			|		(new removed bullet).isTerminated()
	 *			|		(new entity).isTerminated()
	 * @post	This ship has fired a bullet.
	 * 			| (new removed bullet).getSource() == new
	 * 			| new.getBulletsFired().contains((new removed bullet))
	 */
	public void fireBullet() {
		if ((this.getNbBullets()>0)&&(this.getWorld()!=null)) {
			Bullet bullet = (Bullet) this.getBullets().toArray()[0];
			this.removeBullet(bullet);
			bullet.setShip(null);
			bullet.setWorld(null);
			bullet.setSource(null);
			bullet.setVelocity(INITIAL_SPEED*Math.cos(getOrientation()), 
				INITIAL_SPEED*Math.sin(getOrientation()));
			double xpos = getXPosition()+(getRadius()+1*bullet.getRadius())*Math.cos(getOrientation());
			double ypos = getYPosition()+(getRadius()+1*bullet.getRadius())*Math.sin(getOrientation());
			if ((xpos<0.99*bullet.getRadius())||
				(xpos>1.01*(this.getWorld().getWidth()-bullet.getRadius()))||
				(ypos<0.99*bullet.getRadius())||
				(ypos>1.01*(this.getWorld().getHeight()-bullet.getRadius())))
				bullet.terminate();
			else {
				Set<Entity> terminateEntities = new HashSet<>();
				for (Entity entity: getWorld().getEntities().keySet()) {
					if ((!bullet.isLoopTerminated())&&(entity!=bullet)&&(entity!=this)&&
							((Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
							Math.pow(ypos-entity.getYPosition(),2)))<
							0.99*(entity.getRadius()+bullet.getRadius()))) {
						bullet.setLoopTerminated();
						terminateEntities.add(entity);
						terminateEntities.add(bullet);
					}
				}
				if (! bullet.isLoopTerminated()) {
					bullet.setPosition(xpos, ypos);
					getWorld().addEntity(bullet);
					this.getBulletsFired().add(bullet);
				}
				if (! terminateEntities.isEmpty())
					for (Entity entity: terminateEntities)
						entity.terminate();
			}
			bullet.setWorld(null);
			bullet.setSource(this);
		}
	}
	
	/**
	 * Return the bullets fired by this ship.
	 */
	public Set<Bullet> getBulletsFired() {
		return this.bulletsFired;
	}
	
	/**
	 * Return the thrust force of this ship.
	 */
	public double getThrustForce() {
		return this.thrustForce;
	}
	
	/**
	 * Set the thrust force of this ship to a given value.
	 * 
	 * @param	force
	 * 			The new thrust force of this ship.
	 * @post	The new thrust force of this ship is equal to the given thrust force.
	 * 			| new.getThrustForce == force
	 */
	public void setThrustForce(double force) {
		this.thrustForce = force;
	}
	
	/**
	 * Return whether the thruster of this ship is enabled.
	 */
	public boolean thrusterEnabled() {
		return this.thruster;
	}
	
	/**
	 * Enable the thruster of this ship.
	 * 
	 * @post	The thruster of the ship is enabled.
	 * 			| new.thrusterEnabled()
	 * @post	The new acceleration of this ship is set to the value, derived from Newton's second
	 * 			law of motion (F=m*a)
	 * 			| new.getAcceleration == old.getThrustForce()/old.getTotalMass()
	 */
	public void thrustOn() {
		this.thruster = true;
		this.setAcceleration(this.getThrustForce()/this.getMass());
	}
	
	/**
	 * Disable the thruster of this ship.
	 * 
	 * @post	The thruster of the ship is disabled.
	 * 			| ! new.thrusterEnabled()
	 * @post	The acceleration of this ship is set to zero.
	 * 			| new.getXAcceleration == 0
	 * 			| new.getYAcceleration == 0
	 */
	public void thrustOff() {
		this.thruster = false;
		this.setAcceleration(0);
	}

	/**
	 * Return the program, loaded on this ship.
	 */
	public Program getProgram() {
		return this.program;
	}

	/**
	 * Load the given program on this ship.
	 * 
	 * @param 	program
	 * 			The given program.
	 * @post	This ship is now loaded with the given program.
	 * 			| new.getProgram() == program
	 */
	public void setProgram(Program program) {
		this.program = program;
		program.setShip(this);
	}

	/**
	 * Turn this ship by a given angle.
	 * 
	 * @param 	angle
	 * 			The angle by which the ship turns. A positive angle causes a counterclockwise rotation,
	 * 			a negative angle causes a clockwise rotation.
	 * @post	The new orientation of the ship is equal to the old orientation of the ship, incremented
	 * 			with the given orientation.
	 * 			| new.getOrientation() = old.getOrientation + angle
	 * @throws	IllegalOrientationExpression
	 * 			The given angle exceeds 2*PI in absolute value
	 * 			| (angle<-2*Math.PI)||(angle>2*Math.PI)
	 */
	public void turn(double angle) {
		if ((angle<-2*Math.PI)||(angle>2*Math.PI))
			throw new IllegalExpressionException(new DoubleLiteralExpression(angle));
		setOrientation(getOrientation()+angle);
	}

	/**
	 * Move this ship for a given duration.
	 * 
	 * @post	The velocity of this ship is set to a new value, taken into account its
	 * 			acceleration.
	 * 			| new.getXVelocity() = old.getXVelocity+dt*old.getAcceleration()*Math.cos(old.getOrientation())
	 * 			| new.getYVelocity() = old.getYVelocity+dt*old.getAcceleration()*Math.sin(old.getOrientation())
	 */
	@Override
	public void move(double dt) throws IllegalDurationException {
		super.move(dt);
		setVelocity(getXVelocity()+dt*getAcceleration()*Math.cos(getOrientation()), 
				getYVelocity()+dt*getAcceleration()*Math.sin(getOrientation()));
	}

	/**
	 * Terminate this ship.
	 * 
	 * @post	The new state of this ship is terminated.
	 * 			| new.isTerminated()
	 * @post	This ship is no longer associated with its world, if any. 
	 * 			| new.getWorld() == null
	 * 			| ! old.getWorld().contains(this)
	 * @post	This ship does not load any bullets.
	 * 			| for each bullet in old.getBullets():
	 * 			| 	(new bullet).getShip() == null
	 * 			| 	! new.getBullets().contains(bullet)
	 * 			| 	! new.getBulletsFired().contains(this)
	 * 			| 	(new bullet).isTerminated()
	 * 			| 	(new bullet).getSource() == null
	 * 			| 	(new bullet).getWorld() == old.getWorld()
	 */
	@Raw
	public void terminate() {
		super.terminate();
		World world = getWorld();
		for (Bullet bullet: this.getBulletsFired()) {
			bullet.setSource(null);
			bullet.setWorld(world);
		}
		if (this.getWorld()!=null)
			this.getWorld().removeEntity(this);
		for (Bullet bullet: this.getBullets()) {
			bullet.setShip(null);
			bullet.terminate();
		}
		this.getBullets().clear();
		this.getBulletsFired().clear();
	}
	
	/**
	 * A variable registering the acceleration of this ship.
	 */
	private double acceleration = 0;
	
	/**
	 * A variable registering the orientation of this ship.
	 */
	private double orientation;
	
	/**
	 * A variable registering the mass of this ship.
	 */
	private double mass;
	
	/**
	 * A variable registering the bullets of a ship.
	 */
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * A variable registering whether the thruster of this ship is enabled.
	 */
	private boolean thruster = false; 
	
	/**
	 * A variable registering the bullets this ship fired.
	 */
	private Set<Bullet> bulletsFired = new HashSet<Bullet>();
	
	/**
	 * A variable registering the force the active thruster of this ship exerts.
	 */
	private double thrustForce = STANDARD_FORCE;
	
	/**
	 * A variable registering the minimum radius of a ship.
	 */
	private static double MIN_RADIUS = 10;
	
	/**
	 * A variable registering the program of a ship.
	 */
	private Program program;
	
	/** 
	 * A variable registering the minimum density of a ship.
	 */
	private static final double MIN_DENSITY = 1.42*Math.pow(10, 12);
	
	/** 
	 * A variable registering the standard force the active thruster of a ship exerts.
	 */
	private static final double STANDARD_FORCE = 1.1*Math.pow(10, 18);
	
	/**
	 * A variable registering the initial speed of a bullet when fired.
	 */
	private static final double INITIAL_SPEED = 250;
	
}