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
	 * @post	The new mass for this new ship is equal to the given mass.
	 * 			| new.getMass() == mass
	 * @post	The new mass density for this new ship is equal to its lower bound.
	 * 			| new.getMassDensity() == MIN_DENSITY
	 * @post	The new thrust force for this new ship is equal to its standard value.
	 * 			| new.getThrustForce() == STANDARD_FORCE
	 * @post	The new maximum speed for this new ship is equal to its upper bound.
	 * 			| new.getMaxSpeed() == MAX_SPEED
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| (! isValidPosition(xpos, ypos))
	 * @throws 	IllegalRadiusException
	 * 			The given radius does not exceed MIN_RADIUS.
	 * 			| (! isValidRadius(radius))
	 */
	@Raw
	public Ship(double xpos, double ypos, double xvel, double yvel,double radius, double orientation, 
			double mass) 
			throws IllegalRadiusException, IllegalPositionException {
		setPosition(xpos, ypos);
		setMaxSpeed(MAX_SPEED);
		setVelocity(xvel, yvel);
		setRadius(radius);
		setOrientation(orientation);
		setMass(mass);
		setMassDensity(MIN_DENSITY);
		setThrustForce(STANDARD_FORCE);
		setWorld(null);
		for (int I=0; I==14; I++)
			this.loadBullet(new Bullet(mass, mass, mass, mass, mass));
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
		this(0.0,0.0,0.0,0.0,MIN_RADIUS,0.0,0.0);
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
		if ((((xpos == Double.POSITIVE_INFINITY)||(xpos == Double.NEGATIVE_INFINITY)||(Double.isNaN(xpos))))
				&& (((ypos == Double.POSITIVE_INFINITY)||(ypos == Double.NEGATIVE_INFINITY)||(Double.isNaN(ypos)))))
			return false;
		if (this.getWorld() != null) {
			if (((this.getRadius()<xpos)&&(xpos<(this.getWorld().getWidth()-this.getRadius())))&&
					((this.getRadius()<ypos)&&(ypos<(this.getWorld().getHeight()-this.getRadius())))) {
				for (Entity entity: world.getEntities().values()) {
					if ((this.overlap(entity))&&(entity!=this)&&(!this.getBullets().contains(entity)))
						return false;
				}
				return true;
			}
			return false;
			}
		return true;
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
	 * 			| new.getAcceleration == acc
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
	 * @post	The new radius for this ship is the given radius.
	 * 			| new.getRadius() == radius
	 * @throws 	IllegalRadiusException
	 * 			The given radius does not exceed MIN_RADIUS.
	 * 			| (! isValidRadius())
	 */
	@Raw
	public void setRadius(double radius) throws IllegalRadiusException{
		if (radius <= MIN_RADIUS)
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
	 * 			The world, in which the ship has to be located.
	 * @post	If the ship does not belong to a world yet and if the given world already associates 
	 * 			the ship with itself, the new world of this ship is the given world.
	 * 			| if (old.getWorld() == null)&&(world.getEntities().contains(this))
	 *			|	new.world == world;		
	 */
	@Raw
	public void setWorld(World world) {
		if ((this.getWorld() == null)||(world==null))
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
	 * 			A given bullet is already located in a ship/world.
	 * 			| for at least one bullet in bullets:
	 * 			| 	bullet.hasPosition()
	 */
	public void loadBullet(Bullet... bullets) throws IllegalBulletException {
		for (Bullet bullet: bullets) {
			if (bullet.hasPosition())
				throw new IllegalBulletException(bullet);
			this.getBullets().add(bullet);
			bullet.setShip(this);
		}
	}
	
	/**
	 * Fire a bullet.
	 */
	public void fireBullet() {
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
	 * 			| new.getAcceleration == this.getThrustForce()/this.getTotalMass()
	 */
	public void thrustOn() {
		this.thruster = true;
		this.setAcceleration(this.getThrustForce()/this.getTotalMass());
	}
	
	/**
	 * Return whether this ship is located in a world.
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
	 * 			| new.getXAcceleration == 0
	 * 			| new.getYAcceleration == 0
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
	 * 			| new.getWorld() == null
	 * 			| ! old.getWorld().contains(this)
	 * @post	This ship does not load any bullets.
	 * 			| for each bullet in old.getBullets():
	 * 			| 	(new bullet).getShip() == null
	 * 			| 	! new.getBullets().contains(bullet)
	 */
	@Raw
	public void terminate() {
		this.isTerminated = true;
		this.setWorld(null);
		double[] pos = {this.getXPosition(),this.getYPosition()};
		this.getWorld().getEntities().remove(pos);
		this.getWorld().getShips().remove(this);
		for (Bullet bullet: this.getBullets()) {
			this.getBullets().remove(bullet);
			bullet.setShip(null);
		}
	}
	
	/**
	 * Move the ship for a given amount of time according to its current position, velocity, 
	 * acceleration and orientation.
	 * 
	 * @param 	dt
	 * 			The duration of the movement.
	 * @post	The new position of the ship is the position it reaches if starts from its old position
	 * 			and its orientation and acceleration do not change during the movement.
	 * 			| new.getXPosition() == old.getXPosition() + dt*old.getXVelocity() +
	 * 			| 	old.getAcceleration()*Math.cos(old.getOrientation())*Math.pow(dt,2)/2
	 * 			| new.getYPosition() == old.getYPosition() + dt*old.getYVelocity() +
	 * 			| 	old.getAcceleration()*Math.sin(old.getOrientation())*Math.pow(dt,2)/2
	 * @post 	The new velocity of this ship is the velocity it reaches if its acceleration and
	 * 			orientation do not change during the movement.
	 * 			| new.getXVelocity() = old.getXVelocity()+
	 * 			| 	old.getAcceleration()*Math.cos(old.getOrientation())*dt
	 * 			| new.getYVelocity() = old.getYVelocity()+
	 * 			| 	old.getAcceleration()*Math.sin(old.getOrientation())*dt
	 * @throws 	IllegalDurationException
	 * 			The given duration of the movement is negative.
	 * 			| dt < 0
	 */
	public void move(double dt) throws IllegalDurationException {
		if (dt < 0)
			throw new IllegalDurationException(dt);
		else if (dt > 0) {
			this.setPosition(getXPosition()+getXVelocity()*dt+
					this.getAcceleration()*Math.cos(this.getOrientation())*Math.pow(dt,2)/2,
								getYPosition()+getYVelocity()*dt+
					this.getAcceleration()*Math.sin(this.getOrientation())*Math.pow(dt,2)/2);
			this.setVelocity(getXVelocity()+getAcceleration()*Math.cos(this.getOrientation())*dt, 
					getYVelocity()+getAcceleration()*Math.sin(this.getOrientation())*dt);
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
	 * Return the time until the first collision of this ship with the boundaries of its world, if any.
	 * 
	 * @return 	If this ship is not located in a world, positive infinity is returned.
	 * 			| if this.getWorld() == null
	 * 			| 	result == Double.POSITIVE_INFINITY
	 * @return 	If this ship is located in a world, the first collision of this ship with the 
	 * 			boundaries of that world will happen after the returned time. Other entities in 
	 * 			this world are not taken into account.
	 * 			| xpos = getAcceleration()*Math.cos(getOrientation())*Math.pow(result,2)/2
	 * 			| 	+getXVelocity()*result+getXPosition()
	 * 			| ypos = getAcceleration()*Math.sin(getOrientation())*Math.pow(result,2)/2
	 * 			| 	+getYVelocity()*result+getYPosition()
	 *			| xpos == this.getWorld().getWidth()-this.getRadius() ||
	 *			| xpos == this.getRadius ||
	 *			| ypos == this.getWorld().getHeight()-this.getRadius() ||
	 *			| ypos == this.getRadius() ||
	 */
	public double getTimeToBoundary() {
		if (this.getWorld()==null)
			return Double.POSITIVE_INFINITY;
		double timeX1 = solveQuad(this.getAcceleration()*Math.cos(getAcceleration())/2,this.getXVelocity(),
					-getWorld().getWidth()+getXPosition()+this.getRadius());
		double timeX2 = solveQuad(this.getAcceleration()*Math.cos(getAcceleration())/2,this.getXVelocity(),
					getXPosition()-getRadius());
		double timeY1 = solveQuad(this.getAcceleration()*Math.sin(getAcceleration())/2,this.getYVelocity(),
				-getWorld().getHeight()+getYPosition()+this.getRadius());
		double timeY2 = solveQuad(this.getAcceleration()*Math.sin(getAcceleration())/2,this.getYVelocity(),
					getYPosition()-getRadius());
		double timeX = Math.min(timeX1,timeX2);
		double timeY = Math.min(timeY1,timeY2);
		return Math.min(timeX, timeY);
	}
	
	public double solveQuad(double a,double b,double c) {
		if (a==0)
			if (b==0)
				return Double.POSITIVE_INFINITY;
			else
				return -c/b;
		double discr = Math.pow(b, 2)-4*a*c;
		if (discr > 0) {
			double root1 = (-b-Math.sqrt(discr))/2*a;
			double root2 = (-b+Math.sqrt(discr))/2*a;
			if ((root1>=0)||(root2>=0)) {
				if ((root1>=0)&&(root2>=0))
					return Math.min(root1,root2);
				else
					return Math.max(root1, root2);
			}
			else
				return Double.POSITIVE_INFINITY;
		}
		else if (discr == 0)
			return -b/2*a;
		else
			return Double.POSITIVE_INFINITY;
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
	 * A variable registering the acceleration of this ship.
	 */
	private double acceleration = 0;
	
	/**
	 * A variable registering the orientation of this ship.
	 */
	private double orientation;
	
	/**
	 * A variable registering the world of a ship.
	 */
	private World world = null;
	
	/**
	 * A variable registering the mass of this ship.
	 */
	private double mass;
	
	/**
	 * A variable registering the mass density of this entity.
	 */
	protected double massDensity = MIN_DENSITY;
	
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
	 * A variable registering the maximum speed of a ship.
	 */
	private static double MAX_SPEED = 300000;
	
	/**
	 * A variable registering the minimum radius of a ship.
	 */
	private static double MIN_RADIUS = 10;
	
	/** 
	 * A variable registering the minimum density of a ship.
	 */
	private static final double MIN_DENSITY = 1.42*Math.pow(10, 12);
	
	/** 
	 * A variable registering the standard force the active thruster of a ship exerts.
	 */
	private static final double STANDARD_FORCE = 1.1*Math.pow(10, 21);
	
	
}