package asteroids.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.programs.exceptions.IllegalExpressionException;
import asteroids.model.programs.expressions.DoubleLiteralExpression;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of space ships involving a position, a velocity, a radius
 * and an orientation.
 * @param <type>
 * 
 * @invar	Each ship has a valid position.
 * 			| isValidPosition(getXPosition(), getYPosition())
 * @invar	Each ship has a valid radius.
 * 			| isValidRadius(getRadius())
 * @invar	Each ship has a valid orientation.
 * 			| isValidOrientation(getOrientation())
 */
public class Ship extends Entity{
	
	/**
	 * Initialize this new ship with given position, velocity, radius and orientation.
	 * 
	 * @param	pos
	 * 			The initial position for this ship.
	 * @param	vel
	 * 			The initial velocity for this ship.
	 * @param	radius
	 * 			The radius for this ship.
	 * @param	orientation
	 * 			The orientation for this ship
	 * @param	mass
	 * 			The mass for this ship
	 * @pre		This ship can have the given orientation as orientation.
	 * 			| isValidOrientation(orientation)
	 * @post	The new position for this new ship is equal to the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @post	The new velocity for this new ship is equal to the given velocity.
	 * 			| new.getXVelocity() == xvel
	 * 			| new.getYVelocity() == yvel
	 * @post	The new radius for this new ship is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The new orientation for this new ship is equal to the given orientation.
	 * 			| new.getOrientation() == orientation
	 * @post	The new mass for this new ship is equal to the given mass.
	 * 			| new.getMass() == mass
	 * @post	The new mass density for this new ship is equal to its lower bound.
	 * 			| new.getMassDensity() == MIN_DENSITY
	 * @post	The new thrust force for this new ship is equal to its standard value.
	 * 			| new.getThrustForce() == STANDARD_FORCE
	 * @post	The new maximum speed for this new ship is equal to its upper bound.
	 * 			| new.getMaxSpeed() == MAX_SPEED
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
	 * @post	The new position for this new ship is equal to the middle of the unbounded
	 * 			two-dimensional space.
	 * 			| new.getXPosition() == 0
	 * 			| new.getYPosition() == 0
	 * @post	The new velocity for this new ship is equal to 0.
	 * 			| new.getXVelocity() == 0
	 * 			| new.getYVelocity() == 0
	 * @post	The new radius for this new ship is equal to MIN_RADIUS.
	 * 			| new.getRadius() == MIN_RADIUS
	 * @post	The new orientation for this new ship is equal to 0.
	 * 			| new.getOrientation() == 0
	 * @post	The mass of this new ship is equal to (4/3)*(Math.PI)*Math.pow(MIN_RADIUS, 3)*MIN_DENSITY)
	 * 			| new.getMass() == (4/3)*(Math.PI)*Math.pow(MIN_RADIUS, 3)*MIN_DENSITY)
	 * @post	The new mass density for this new ship is equal to its lower bound.
	 * 			| new.getMassDensity() == MIN_DENSITY
	 * @post	The new thrust force for this new ship is equal to its standard value.
	 * 			| new.getThrustForce() == STANDARD_FORCE
	 * @post	The new maximum speed for this new ship is equal to its upper bound.
	 * 			| new.getMaxSpeed() == MAX_SPEED
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
	public Ship() {
		this(0.0,0.0,0.0,0.0,MIN_RADIUS,0.0,0.0);
	}
	
	/**
	 * Return the acceleration of this ship.
	 */
	@Basic @Raw
	public double getAcceleration() {
		return this.acceleration;
	}
	
	@Override
	public void setVelocity(double xvel, double yvel) {
		super.setVelocity(xvel, yvel);
		if (getBullets()!=null) {
			for (Bullet bullet: getBullets()) {
				bullet.setVelocity(xvel, yvel);
			}
		}
	}
	
	@Override
	public void setPosition(double xpos, double ypos) {
		super.setPosition(xpos, ypos);
		if (getBullets()!=null) {
			for (Bullet bullet: getBullets()) {
				bullet.setPosition(xpos, ypos);
			}
		}
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
	 * Set the radius of this ship to a given value.
	 * 
	 * @param 	radius
	 * 			The new radius for this ship.
	 * @post	The new radius for this ship is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @throws 	IllegalRadiusException
	 * 			The given radius does not exceed MIN_RADIUS.
	 * 			| (! isValidRadius())
	 */
	@Raw
	public void setRadius(double radius) throws IllegalRadiusException{
		if (radius < MIN_RADIUS)
			throw new IllegalRadiusException(radius);
		this.radius = radius;
	}
	
	/**
	 * Checks whether the given radius is a valid radius.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the radius exceeds its minimal value.
	 * 			| result == (MIN_RADIUS <= radius)
	 */
	public boolean isValidRadius(double radius){
		return (radius >= MIN_RADIUS);
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
	public void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
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
	 * 			| if (mass >= (4/3)*(Math.PI)*Math.pow(old.getRadius(), 3)*old.getMassDensity())
	 *			|	new.mass == mass;
	 *			| else
	 *			|	new.mass == (4/3)*(Math.PI)*Math.pow(old.getRadius(), 3)*old.getMassDensity();
	 */
	@Raw
	public void setMass(double mass) {
		if (mass >= (4/3)*(Math.PI)*Math.pow(this.getRadius(), 3)*this.getMassDensity())
			this.mass = mass;
		else
			this.mass = (4/3)*(Math.PI)*Math.pow(this.getRadius(), 3)*this.getMassDensity();
	}
	
	/**
	 * Return the total mass of this ship.
	 * 
	 * @result	The resulted mass is equal to the sum of the mass of the ship and the sum
	 * 			of the masses of the bullets the ship has loaded.
	 *			| totalMass = this.getMass()
	 *			| for (Bullet bullet: this.getBullets())
				|	totalMass = totalMass + bullet.getMass()
				| result == totalMass
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
	 * Return the bullets, loaded in this ship.
	 */
	public Set<Bullet> getBullets() {
		return this.bullets;
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
		this.getBullets().remove(bullet);
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
	 * 			| ! new.getBulletsFired().contains(bullet)
	 * @throws	IllegalBulletException
	 * 			This ship and the given bullet both belong to a world, and these worlds are
	 * 			not the same.
	 * 			| (this.getWorld()!=null)&&(bullet.getWorld()!=null)
	 * 			| 	&&(this.getWorld()!=bullet.getWorld())
	 */
	public void loadBullet(Bullet bullet) throws IllegalBulletException {
		if ((this.getWorld()!=null)&&(bullet.getWorld()!=null)&&(this.getWorld()!=bullet.getWorld()))
			throw new IllegalBulletException(bullet);
		if (bullet.getWorld()!=null)
			bullet.getWorld().removeEntity(bullet);
		this.getBullets().add(bullet);
		bullet.setSource(null);
		bullet.setShip(this);
		bullet.setPosition(getXPosition(), getYPosition());
		bullet.setVelocity(getXVelocity(), getYVelocity());
		bullet.setBounces(0);
		this.getBulletsFired().remove(bullet);
	}	
	
	/**
	 * Load this ship with a given series of bullets.
	 * 
	 * @param	bullet
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
	 * 			| for each (bullet: bullets)
	 * 			|	 ! new.contains(new bullet)
	 * @throws	IllegalBulletException
	 * 			This ship and a given bullet both belong to a world, and these worlds are
	 * 			not the same.
	 * 			| for a (bullet: bullets)
	 * 			| 	(this.getWorld()!=null)&&(bullet.getWorld()!=null)
	 * 			| 	&&(this.getWorld()!=bullet.getWorld())
	 */
	public void loadBullet(Collection<Bullet> bullets) throws IllegalBulletException {
		for (Bullet bullet: bullets) {
			if ((this.getWorld()!=null)&&(bullet.getWorld()!=null)&&(this.getWorld()!=bullet.getWorld()))
				throw new IllegalBulletException(bullet);
			if (bullet.getWorld()!=null)
				bullet.getWorld().removeEntity(bullet);
			this.getBullets().add(bullet);
			bullet.setSource(null);
			bullet.setShip(this);
			bullet.setPosition(getXPosition(), getYPosition());
			bullet.setVelocity(getXVelocity(), getYVelocity());
			bullet.setBounces(0);
			this.getBulletsFired().remove(bullet);
		}
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
			double xpos = getXPosition()+(getRadius()+1.5*bullet.getRadius())*Math.cos(getOrientation());
			double ypos = getYPosition()+(getRadius()+1.5*bullet.getRadius())*Math.sin(getOrientation());
			if ((xpos<0.99*bullet.getRadius())||
				(xpos>1.01*(this.getWorld().getWidth()-bullet.getRadius()))||
				(ypos<0.99*bullet.getRadius())||
				(ypos>1.01*(this.getWorld().getHeight()-bullet.getRadius())))
				bullet.terminate();
			else {
				for (Entity entity: getWorld().getEntities().keySet()) {
					if ((!bullet.isTerminated())&&(entity!=bullet)&&(entity!=this)&&
							((Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
							Math.pow(ypos-entity.getYPosition(),2)))<
							0.99*(entity.getRadius()+bullet.getRadius()))) {
						bullet.isTerminated=true;
						entity.isTerminated=true;
					}
				}
				if (! bullet.isTerminated()) {
					bullet.setSource(this);
					bullet.setPosition(xpos, ypos);
					getWorld().addEntity(bullet);
					this.getBulletsFired().add(bullet);
				}
			}
		}
	}
	
	/**
	 * Return the bullets fired by this ship.
	 */
	public Set<Bullet> getBulletsFired() {
		return this.bulletsFired;
	}
	
	/**
	 * Return the mass density of this ship.
	 */
	@Basic @Raw
	public double getMassDensity() {
		return this.massDensity;
	}
	
	/**
	 * Set the mass density of this ship to the given value.
	 * 
	 * @param	massDensity
	 * @post	If the given density exceeds its minimal value, the new mass density of this ship
	 * 			is equal to the given value. Otherwise, the mass density of this ship is set to its minimal value.
	 * 			| if (massDensity > MIN_DENSITY)
	 * 			| 	new.getMassDensity == massDensity
	 * 			| else
	 * 			| 	new.getMassDensity == MIN_DENSITY
	 * @post	The mass of this ship is adjusted to the new mass density of this ship.
	 */
	@Override
	public void setMassDensity(double massDensity) {
		if (massDensity > MIN_DENSITY)
			this.massDensity = massDensity;
		else
			this.massDensity = MIN_DENSITY;
		this.setMass(this.getMass());
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
	 * Return whether this ship is located in a world.
	 * 
	 * @result	Returns true if and only if this ship is located in a world.
	 * 			| result == (this.getWorld()!=0)
	 */
	public boolean hasPosition() {
		return this.getWorld() != null;
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
		this.isTerminated = true;
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
	 * Turn the ship by a given angle.
	 * 
	 * @param 	angle
	 * 			The angle by which the ship turns. A positive angle causes a counterclockwise rotation,
	 * 			a negative angle causes a clockwise rotation.
	 * @post	The new orientation of the ship is equal to the old orientation of the ship, incremented
	 * 			with the part of the given angle that changes the netto orientation of the ship.
	 * 			| while ((this.getOrientation()) + angle >= 2 * Math.PI) 
	 *			|	angle -= 2 * Math.PI;
	 *			| while ((this.getOrientation() + angle) < 0) {
	 *			|	angle += 2 * Math.PI;
	 *			| new.getOrientation() == old.getOrientation() + angle
	 */
	public void turn(double angle) {
		if ((angle<-2*Math.PI)||(angle>2*Math.PI))
			throw new IllegalExpressionException(new DoubleLiteralExpression(angle));
		setOrientation(getOrientation()+angle);
	}
	
	@Override
	public void move(double dt) throws IllegalDurationException {
		super.move(dt);
		for (Bullet bullet: getBullets()) {
			bullet.setPosition(getXPosition()+dt*getXVelocity(), 
					getYPosition() + dt*getYVelocity());
		}
		setVelocity(getXVelocity()+dt*getAcceleration()*Math.cos(getOrientation()), 
				getYVelocity()+dt*getAcceleration()*Math.sin(getOrientation()));
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
		program.setShip(this);
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