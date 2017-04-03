package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of space ships involving a position, a velocity, a radius
 * and an orientation.
 * 
 * @invar	Each ship has a valid position.
 * 			| isValidPosition(getXPosition(), getYPosition())
 * @invar	Each ship has a valid radius.
 * 			| isValidRadius(getRadius())
 * @invar	Each ship has a valid orientation.
 * 			| isValidOrientation(getOrientation())
 * 
 * 
 */
public class Ship {
	
	/**
	 * Initialize this new ship with given position, velocity, radius and orientation.
	 * 
	 * @param	pos
	 * 			The initial position for this ship.
	 * @param	vel
	 * 			The initial velocity for this ship.
	 * @param	radius
	 * 			The radius for this ship.
	 * @pre		This ship can have the given orientation as orientation.
	 * 			| isValidOrientation(orientation)
	 * @post	The new position for this new ship is equal to the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @post	The new velocity for this new ship is equal to the given velocity.
	 * 			| new.getXVelocity() == xvel
	 * 			| new.getYVelocity() == yvel
	 * @post	The new radius for this new ship is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The new orientation for this new ship is equal to the given orientation.
	 * 			| new.getOrientation() == orientation
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| (! isValidPosition(xpos, ypos))
	 * @throws 	IllegalRadiusException
	 * 			The given radius does not exceed MIN_RADIUS.
	 * 			| (! isValidRadius(radius))
	 */
	@Raw
	public Ship(double xpos, double ypos, double xvel, double yvel,double radius, double orientation, 
			double maxSpeed) 
			throws IllegalRadiusException, IllegalPositionException {
		setMaxSpeed(maxSpeed);
		setPosition(xpos, ypos);
		setVelocity(xvel, yvel);
		setRadius(radius);
		setOrientation(orientation);
		for (int I=0; I==14; I++)
			this.loadBullet(new Bullet());
	}
	
	/**
	 * Initialize this new ship with no given position, velocity, radius or orientation.
	 * 
	 * @post	The new position for this new ship is equal to the middle of the unbounded
	 * 			two-dimensional space.
	 * 			| new.getXPosition() == 0
	 * 			| new.getYPosition() == 0
	 * @post	The new velocity for this new ship is equal to 0.
	 * 			| new.getXVelocity() == 0
	 * 			| new.getYVelocity() == 0
	 * @post	The new radius for this new ship is equal to MIN_RADIUS.
	 * 			| new.getRadius() == MIN_RADIUS
	 * @post	The new orientation for this new ship is equal to 0.
	 * 			| new.getOrientation() == 0
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| (! isValidPosition(xpos, ypos))
	 * @throws 	IllegalRadiusException
	 * 			The given radius does not exceed MIN_RADIUS.
	 * 			| (! isValidRadius(radius))
	 */
	@Raw
	public Ship() {
		this(0.0,0.0,0.0,0.0,MIN_RADIUS,0.0,MAX_SPEED);
	}
	
	/**
	 * Return the position of this ship along the x-axis.
	 */
	@Basic @Raw
	public double getXPosition() {
		return this.xPosition;
	}
	
	/**
	 * Return the position of this ship along the y-axis.
	 */
	@Basic @Raw
	public double getYPosition() {
		return this.yPosition;
	}
	
	/**
	 * Set the position of this ship to a given position.
	 * 
	 * @param	xpos
	 * 			The new position of this ship along the x-axis.
	 * @param	ypos
	 * 			The new position of this ship along the y-axis.
	 * @post	The new position for this new ship is equal to the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| ! isValidPosition(xpos, ypos)
	 */
	@Raw
	public void setPosition(double xpos, double ypos) {
		if (! isValidPosition(xpos,ypos))
			throw new IllegalPositionException(xpos, ypos, this);
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
	 * @return	True if and only if neither xpos, nor ypos is negative or positive infinity or not a number.
	 * 			If the ship is associated with a certain world, it must be positioned within its bounds.
	 * 			| if ((! ((xpos == Double.POSITIVE_INFINITY)||(xpos == Double.NEGATIVE_INFINITY)||(Double.isNaN(xpos))))
	 *			| 	&& (! ((ypos == Double.POSITIVE_INFINITY)||(ypos == Double.NEGATIVE_INFINITY)||(Double.isNaN(ypos)))))
	 *			|	if (this.getWorld() != null) 
	 *			|		if (((this.getRadius()<xpos)&&(xpos<(this.getWorld().getWidth()-this.getRadius())))&&
	 *			|			((this.getRadius()<ypos)&&(ypos<(this.getWorld().getHeight()-this.getRadius()))))
	 *			|		then result == true
	 *			| 	then result == true
	 *			| else
	 *			| 	result == false
	 */
	public boolean isValidPosition(double xpos, double ypos) {
		if ((! ((xpos == Double.POSITIVE_INFINITY)||(xpos == Double.NEGATIVE_INFINITY)||(Double.isNaN(xpos))))
				&& (! ((ypos == Double.POSITIVE_INFINITY)||(ypos == Double.NEGATIVE_INFINITY)||(Double.isNaN(ypos))))) {
			if (this.getWorld() != null) {
				if (((this.getRadius()<xpos)&&(xpos<(this.getWorld().getWidth()-this.getRadius())))&&
						((this.getRadius()<ypos)&&(ypos<(this.getWorld().getHeight()-this.getRadius()))))
					return true;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Return the velocity of this ship along the x-axis.
	 */
	@Basic @Raw
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	/**
	 * Return the velocity of this ship along the y-axis.
	 */
	@Basic @Raw
	public double getYVelocity() {
		return this.yVelocity;
	}
	
	/**
	 * Set the velocity of this ship to a given velocity.
	 * 
	 * @param	xvel
	 * 			The new velocity of this ship along the x-axis.
	 * @param	yvel
	 * 			The new velocity of this ship along the y-axis.
	 * @post	If the speed of the ship with the new velocity does not exceed its maximum
	 * 			value, the new velocity of the ship is the given velocity. Otherwise,
	 * 			the velocity of the new ship is equal to the maximum speed of this ship..
	 * 			| if (sqrt(xvel^2 + yvel^2) <= old.getMaxSpeed())
	 * 			| 	then (new.getXVelocity() == xvel &&
	 * 			|		new.getYVelocity() == yvel);
	 * 			| else
	 * 			| 	(new.getXVelocity == xvel/sqrt(xvel^2 + yvel^2)*old.getMaxSpeed() &&
	 * 			|		new.getYVelocity == yvel/sqrt(xvel^2 + yvel^2)*old.getMaxSpeed());
	 */
	@Raw
	public void setVelocity(double xvel, double yvel) {
		if (Math.sqrt(Math.pow(xvel,2) + Math.pow(yvel,2)) <= this.maxSpeed) {
			this.xVelocity = xvel;
			this.yVelocity = yvel; }
		else {
			this.xVelocity = xvel/Math.sqrt(Math.pow(xvel,2) + Math.pow(yvel,2))*this.maxSpeed;
			this.yVelocity = yvel/Math.sqrt(Math.pow(xvel,2) + Math.pow(yvel,2))*this.maxSpeed; }
	}
	
	/**
	 * Return the acceleration of this ship along the x-axis.
	 */
	@Basic @Raw
	public double getXAcceleration() {
		return this.xAcceleration;
	}
	
	/**
	 * Return the acceleration of this ship along the y-axis.
	 */
	@Basic @Raw
	public double getYAcceleration() {
		return this.yAcceleration;
	}
	
	/**
	 * Set the acceleration of this ship to a given acceleration.
	 * 
	 * @param	xacc
	 * 			The new acceleration of this ship along the x-axis.
	 * @param	yacc
	 * 			The new acceleration of this ship along the y-axis.
	 * @post	The new acceleration of this ship is equal to the given acceleration.
	 * 			| new.getXAcceleration == xacc
	 * 			| new.getYAcceleration == yacc
	 */
	@Raw
	public void setAcceleration(double xacc, double yacc) {
		this.xAcceleration = xacc;
		this.yAcceleration = yacc;
	}
	
	/**
	 * Return the absolute value of the speed of the ship.
	 */
	@Raw
	public double getSpeed() {
		return Math.sqrt(Math.pow(getXVelocity(),2) + Math.pow(getYVelocity(),2));
	}
	
	/**
	 * Return the maximal speed of this ship.
	 */
	@Basic @Raw
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	
	/**
	 * Set the maximum speed of this ship to a given speed.
	 * 
	 * @param 	maxSpeed	
	 * 			The new maximum speed of this ship.
	 * @post	If the given speed does not exceed the maximum speed of a ship, the new
	 * 			maximum speed of this ship is the given speed. Otherwise, the new maximum speed of
	 * 			this ship is MAX_SPEED.
	 * 			| if maxSpeed <= MAX_SPEED
	 * 			| 	new.getMaxSpeed == maxSpeed
	 * 			| else
	 * 			| 	new.getMaxSpeed == MAX_SPEED
	 * @post	If the current velocity of this ship exceeds its maximum value, the velocity of
	 * 			this ship is set to its maximum value. 
	 * 			| if (old.getSpeed() > new.getMaxSpeed())
	 *			| 	new.getXVelocity == new.getMaxSpeed()*Math.cos(old.getOrientation(), 
	 *			| 	new.getYVelocity == new.getMaxSpeed()*Math.sin(old.getOrientation();
	 */
	@Raw
	public void setMaxSpeed(double maxSpeed) {
		if (Math.abs(maxSpeed) > MAX_SPEED)
			this.maxSpeed = MAX_SPEED;
		else
			this.maxSpeed = maxSpeed;
		this.setVelocity(this.getXVelocity(), this.getYVelocity());;
	}
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Raw
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Set the radius of this ship to a given value.
	 * 
	 * @param 	radius
	 * 			The new radius for this ship.
	 * @post	The new radius for this ship is the given radius.
	 * 			| new.getRadius() == radius
	 * @throws 	IllegalRadiusException
	 * 			The given radius does not exceed MIN_RADIUS.
	 * 			| (! isValidRadius())
	 */
	@Raw
	public void setRadius(double radius) throws IllegalRadiusException{
		if (radius < MIN_RADIUS)
			throw new IllegalRadiusException(radius, this);
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
	 * 			| isValidOrientation(orientation)
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
	 * 			| result == (0 <= orientation <= 2*PI)
	 */
	public boolean isValidOrientation(double orientation) {
		return ((0<=orientation)&&(orientation<=2*Math.PI));
	}
	
	/**
	 * Return the world with which this ship is associated. Null if none.
	 */
	@Basic @Raw
	public World getWorld() {
		if (this.getWorld() != null)
				return new World(this.world.getWidth(), this.world.getHeight());
		return null;
	}
	
	/**
	 * Set the world of this ship to the given world.
	 * 
	 * @param 	world
	 * @post	If the ship does not belong to a world yet and if the given world already associates 
	 * 			the ship with itself, the new world of this ship is the given world.
	 * 			| if (old.getWorld() == null)&&(world.getEntities().contains(this))
	 *			|	new.world == world;		
	 */
	@Raw
	public void setWorld(World world) {
		if ((this.getWorld() == null)&&(world.getEntities().contains(this)))
				this.world = world;
	}
	
	/**
	 * Return the mass of this ship.
	 */
	@Basic @Raw
	public double getMass() {
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
	 * 			of the masses of the bullets the ship has.
	 *			| totalMass = this.getMass()
	 *			| for (Bullet bullet: this.getBullets())
				|	totalMass = totalMass + bullet.getMass()
				| result == totalMass
	 */
	public double getTotalMass() {
		double totalMass = this.getMass();
		for (Bullet bullet: this.getBullets())
			totalMass = totalMass + bullet.getMass();
		return totalMass;
	}
	
	/**
	 * Return the bullets, loaded in this ship.
	 */
	public Set<Bullet> getBullets() {
		return this.bullets;
	}
	
	/**
	 * Return the total number of bullets, loaded in this ship.
	 */
	public int getNbBullets() {
		return this.getBullets().size();
	}
	
	/**
	 * Load this ship with the given series of bullets.
	 * 
	 * @param	bullets
	 * 			The series of bullets this ship has to be loaded with.
	 * @post	| for each I in 0..bullets.length-1:
	 * 			| 	new.getBullets().contains(bullets[I])
	 * 			| 	bullets[I].getShip() == new
	 * @throws	IllegalBulletException
	 * 			The given bullet is already loaded into another ship.
	 * 			| ! for each bullet in bullets:
	 * 			| 	bullet.getShip != this
	 * @throws	IllegalBulletException
	 * 			The given ship is not located in the same world as this bullet.
	 * 			| ! for each bullet in bullets:
	 * 			| 	this.getWorld() != bullet.getWorld()
	 */
	public void loadBullet(Bullet... bullets) {
		for (Bullet bullet: bullets) {
			if (bullet.getShip() != null)
				throw new IllegalBulletException(bullet);
			if (bullet.getWorld() != this.getWorld())
				throw new IllegalBulletException(bullet);
			this.getBullets().add(bullet);
			bullet.setShip(this);
		}
	}
	
	/**
	 * Fire a bullet.
	 */
	public void fireBullet() {
			//
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
	 * 			| if (massDensity > MIN_DENSITY)
	 * 			| 	new.getMassDensity == massDensity
	 * 			| else
	 * 			| 	new.getMassDensity == MIN_DENSITY
	 */
	public void setMassDensity(double massDensity) {
		if (massDensity > MIN_DENSITY)
			this.massDensity = massDensity;
		else
			this.massDensity = MIN_DENSITY;
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
	 * 			| new.getThrustForce == force
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
	 * 			| new.getXAcceleration == getThrustForce()/getTotalMass()*Math.cos(getOrientation())
	 * 			| new.getYAcceleration == getThrustForce()/getTotalMass()*Math.sin(getOrientation())
	 */
	public void thrustOn() {
		this.thruster = true;
		this.setAcceleration(this.getThrustForce()/this.getTotalMass()*Math.cos(this.getOrientation()), 
				this.getThrustForce()/this.getTotalMass()*Math.sin(this.getOrientation()));
	}
	
	/**
	 * Disable the thruster of this ship.
	 * 
	 * @post	The thruster of the ship is disabled.
	 * 			| ! new.thrusterEnabled()
	 * @post	The acceleration of this ship is set to zero.
	 * 			| new.getXAcceleration == 0
	 * 			| new.getYAcceleration == 0
	 */
	public void thrustOff() {
		this.thruster = false;
	}
	
	/**
	 * Return whether this ship is terminated.
	 * 
	 * @return	True if and only if this ship is terminated.
	 * 			| result == this.isTerminated()
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * Terminate this ship.
	 * 
	 * @post	The new state of this ship is terminated.
	 * 			| new.isTerminated()
	 */
	@Raw
	public void terminate() {
		this.isTerminated = true;
	}
	
	/**
	 * Move the ship for a given amount of time according to its current position, velocity and orientation.
	 * 
	 * @param 	dt
	 * 			The duration of the movement.
	 * @post	The new position of the ship is the position it reaches if starts from its old position
	 * 			and orientation and does not change its velocity while moving. 
	 * 			| new.getXPosition() == old.getXPosition() + dt*old.getXVelocity()
	 * 			| new.getYPosition() == old.getYPosition() + dt*old.getYVelocity()
	 * @throws 	IllegalDurationException
	 * 			The given duration of the movement is negative.
	 * 			| dt < 0
	 */
	public void move(double dt) throws IllegalDurationException {
		if (dt < 0)
			throw new IllegalDurationException(dt);
		else if (dt > 0) {
			this.setPosition(getXPosition()+getXVelocity()*dt,
								getYPosition()+getYVelocity()*dt);
		}
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
		while ((this.getOrientation()) + angle >= 2 * Math.PI) {
			angle -= 2 * Math.PI;
		}
		while ((this.getOrientation() + angle) < 0) {
			angle += 2 * Math.PI;
		}
		setOrientation(getOrientation()+angle);
	}
	
	/**
	 * Return 
	 */
	
	/**
	 * Change the ship's velocity by a given amount, based on its current velocity and orientation.
	 * 
	 * @param 	amount
	 * 			The amount of velocity the ship gains.
	 * @post	If the given amount is negative, the amount is set to zero.
	 * 			| if (amount < 0)
	 * 			| 	then (new.getXVelocity() == old.getXVelocity() &&
	 * 			|			new.getYVelocity() == old.getYVelocity())
	 * @post	If the thrust causes the ship's velocity to exceed its maximum value, the ship's velocity 
	 * 			is set to its maximum value.
	 * 			| if (old.getMaxSpeed()-old.getSpeed() < amount)
	 * 			| 	then new.getSpeed() == old.getMaxSpeed()
	 * @post	If the given amount is nonnegative and does not cause the ship to exceed its maximum speed,
	 * 			the ship's velocity is increased by the given amount. 
	 * 			| if ((amount >= 0) && (old.getMaxSpeed()-old.getSpeed() >= amount))
	 * 			| 	then new.getSpeed() == old.getSpeed() + amount
	 */
	public void thrust(double dt) {
		this.thrustOn();
		if	(this.getMaxSpeed()-this.getSpeed() < dt) {
			this.setVelocity(this.getMaxSpeed()*Math.cos(this.getOrientation()),
								this.getMaxSpeed()*Math.sin(this.getOrientation())); }
		else {
			this.setVelocity(this.getXVelocity() + dt*Math.cos(this.getOrientation()),
								this.getYVelocity() + dt*Math.sin(this.getOrientation()));
		}
	}
	
	/**
	 * Return the distance between this ship and a given ship.
	 * 
	 * @param 	other
	 * 			The ship with which this ship will collide.
	 * @return	The distance between two ships is the distance between its centers minus the radiuses of both 
	 * 			ships. The distance can thus be negative. The distance between a ship and itself is zero.
	 * 			| if (this == other)
	 *			| 	then result == 0
	 *			| else
	 *			|	result == (Math.sqrt(Math.pow(this.getXPosition()-other.getXPosition(),2)
	 *			|	+ Math.pow(this.getYPosition()-other.getYPosition(),2))
	 *			|	- this.getRadius() - other.getRadius())
	 * @throws 	IllegalShipException
	 * 			At least one of the ships involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public double getDistanceBetween(Ship other) throws IllegalShipException {
		if ((other == null)||(other.isTerminated()))
			throw new IllegalShipException(other);
		if (this.isTerminated())
			throw new IllegalShipException(this);
		if (this == other)
			return 0;
		return (Math.sqrt(Math.pow(this.getXPosition()-other.getXPosition(),2)
					+ Math.pow(this.getYPosition()-other.getYPosition(),2))
					- this.getRadius() - other.getRadius());
	}
	
	/**
	 * Return whether this ship overlaps with the given ship.
	 * 
	 * @param 	other
	 * 			The ship with which this ship might overlap.
	 * @return	True if and only if the distance between the ships is positive. A ship thus always overlaps 
	 * 			with itself.
	 * 			| result == (getDistanceBetween(this,other) <= 0)
	 * @throws 	IllegalShipException
	 * 			At least one of the ships involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public boolean overlap(Ship other) throws IllegalArgumentException{
		if ((other == null)||(other.isTerminated()))
			throw new IllegalShipException(other);
		if ((this == null)||(this.isTerminated()))
			throw new IllegalShipException(this);
		return (getDistanceBetween(other) <= 0);
	}
	
	/**
	 * Return the time until this ship will collide with the given ship.
	 * 
	 * @param	other
	 * 			The second ship, with which the collision will happen.
	 * @return	If the ships do not yet overlap, they will if they move for the returned amount of time.
	 * 			If the ships will never collide, they are considered to collide after an infinite 
	 * 			amount of time.
	 * 			| if (! overlap(other))
	 * 			| 	this.move(result)
	 * 			| 	other.move(result)
	 * 			| this.getDistanceBetween(other) == 0
	 * @return	No collision between this ship and the other ship will occur within the returned amount
	 * 			of time.
	 * 			|
	 * 			|
	 * 			|
	 * 			|
	 * @throws	IllegalShipException
	 * 			The ships already overlap.
	 * 			| overlap(other)
	 * @throws	IllegalShipException
	 * 			At least one of the ships involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public double getTimeToCollision(Ship other) throws IllegalShipException {
		if ((other == null)||(other.isTerminated()))
			throw new IllegalShipException(other);
		if ((this == null)||(this.isTerminated()))
			throw new IllegalShipException(this);
		if (this.overlap(other))
			throw new IllegalShipException(other);
		double[] dr = {other.getXPosition() - this.getXPosition(),
						other.getYPosition() - this.getYPosition()};
		double[] dv = {other.getXVelocity() - this.getXVelocity(),
						other.getYVelocity() - this.getYVelocity()};
		double d = Math.pow(dv[0]*dr[0]+dv[1]*dr[1],2)-(dv[0]*dv[0]+dv[1]*dv[1])
				*(dr[0]*dr[0]+dr[1]*dr[1]-Math.pow(this.getRadius()+other.getRadius(), 2));
		if ((dv[0]*dr[0]+dv[1]*dr[1] >= 0) || (d <= 0))
			return Double.POSITIVE_INFINITY;
		else
			return -(dv[0]*dr[0]+dv[1]*dr[1]+Math.sqrt(d))/(dv[0]*dv[0]+dv[1]*dv[1]);	
	}
	
	/**
	 * Return the position where this ship will collide with another ship.
	 * 
	 * @param	other
	 * 			The ship with which this ship will collide.
	 * @return	The position of collision if they collide. Null if they never will collide.
	 * 			| dt = this.getTimeToCollision(other)
	 * 			| if (dt != Double.POSITIVE_INFINITY)
	 * 			| 	result == {other.getXPosition() - this.getXPosition() + dt*(other.getXVelocity() - this.getXVelocity()) *
	 * 			| 		(this.getRadius() / other.getRadius()) + this.getXPosition(),
	 *			| 		other.getYPosition() - this.getYPosition() + dt * (other.getYVelocity() - this.getYVelocity()) *
	 *			| 		(this.getRadius() / other.getRadius()) + this.getYPosition()}
	 *			| else
	 *			| 	result == null
	 * @throws	IllegalShipException
	 * 			At least one of the ships involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public double[] getCollisionPosition(Ship other) throws IllegalShipException {
		if (this.getTimeToCollision(other) != Double.POSITIVE_INFINITY) {
			double dt = this.getTimeToCollision(other);
			double[] drc = {other.getXPosition() - this.getXPosition() + dt*(other.getXVelocity() - this.getXVelocity()),
					other.getYPosition() - this.getYPosition() + dt*(other.getYVelocity() - this.getYVelocity())};
			double[] c = {drc[0] * (this.getRadius() / (other.getRadius()+this.getRadius())) + this.getXPosition()
					+ dt*this.getXVelocity(),
					drc[1] * (this.getRadius() / (other.getRadius()+this.getRadius())) + this.getYPosition()
					+dt*this.getYVelocity()};
			return c;
		}
		else
			return null;
	}
	
	
	/**
	 * A variable registering the position of this ship along the x-axis.
	 */
	private double xPosition;
	
	/**
	 * A variable registering the position of this ship along the y-axis. 
	 */
	private double yPosition;
	
	/**
	 * A variable registering the velocity of this ship along the x-axis.
	 */
	private double xVelocity;
	
	/**
	 * A variable registering the velocity of this ship along the y-axis.
	 */
	private double yVelocity;
	
	/**
	 * A variable registering the acceleration of this ship along the x-axis.
	 */
	private double xAcceleration = 0;
	
	/**
	 * A variable registering the acceleration of this ship along the y-axis.
	 */
	private double yAcceleration = 0;
	
	/**
	 * A variable registering the orientation of this ship.
	 */
	private double orientation;
	
	/**
	 * A variable registering the radius of this ship.
	 */
	private double radius = MIN_RADIUS;
	
	/**
	 * A variable registering the maximum speed of this ship.
	 */
	private double maxSpeed = MAX_SPEED;
	
	/**
	 * A variable registering whether a ship is terminated.
	 */
	private boolean isTerminated;
	
	/**
	 * A variable registering the world of a ship.
	 */
	private World world = null;
	
	/**
	 * A variable registering the mass of a ship.
	 */
	private double mass;
	
	/**
	 * A variable registering the mass density of a ship.
	 */
	private double massDensity = MIN_DENSITY;
	
	/**
	 * A variable registering the bullets of a ship.
	 *|| INVARIANTEN HIEROP HIER BESPREKEN
	 */
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * A variable registering whether the thruster of this ship is enabled.
	 */
	private boolean thruster = false; 
	
	/**
	 * A variable registering the force the active thruster of this ship exerts.
	 */
	private double thrustForce = STANDARD_FORCE;
	
	/**
	 * A variable registering the minimum radius of a ship.
	 */
	private static double MIN_RADIUS = 10;
	
	/** 
	 * A variable registering the maximum velocity of a ship.
	 */
	private static final double MAX_SPEED = 300000;
	
	/** 
	 * A variable registering the minimum density of a ship.
	 */
	private static final double MIN_DENSITY = 1.42*Math.pow(10, 12);
	
	/** 
	 * A variable registering the standard force the active thruster of a ship exerts.
	 */
	private static final double STANDARD_FORCE = 1.1*Math.pow(10, 21);
	
	
}