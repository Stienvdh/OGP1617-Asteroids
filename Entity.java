package asteroids.model;

import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalEntityException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalShipException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import java.util.Random;

/**
 * @invar	Each entity has a valid position.
 * 			| entity.isValidPosition(getXPosition(),getYPosition())
 */
public abstract class Entity {
	
	public Entity(double xpos, double ypos, double xvel, double yvel, double radius) {
		setWorld(null);
		setPosition(xpos, ypos);
		setVelocity(xvel, yvel);
		setRadius(radius);
	}
	
	/**
	 * Return the position of this entity along the x-axis.
	 */
	@Basic @Raw
	public double getXPosition() {
		return this.xPosition;
	}
	
	/**
	 * Return the position of this entity along the y-axis.
	 */
	@Basic @Raw
	public double getYPosition() {
		return this.yPosition;
	}
	
	/**
	 * Set the position of this entity to a given position. If the entity is a ship with
	 * loaded bullets, this method also sets their positions to the given position.
	 * 
	 * @param	xpos
	 * 			The position of this entity along the x-axis.
	 * @param	ypos
	 * 			The position of this entity along the y-axis.
	 * @post	The new position of this entity is equal to the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @post	If the entity is a ship, the positions of its bullets are equal the given
	 * 			position.
	 * 			| if (this instanceof Ship)
	 * 			|	for (Bullet bullet: ((Ship)this).getBullets())
	 * 			|		bullet.getXPosition() == xpos
	 * 			| 		bullet.getYPosition() == ypos
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| ! isValidPosition()
	 */
	@Basic @Raw
	public void setPosition(double xpos, double ypos) {
		if (! isValidPosition(xpos, ypos)) {
			throw new IllegalPositionException(xpos, ypos);
		}
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
	 * 			If the ship is associated with a certain world, it must be positioned within its bounds and it
	 * 			cannot overlap with any other entity, located in that world in order to have a valid
	 * 			position.
	 * 			| if (! xpos == Double.POSITIVE_INFINITY)&&(! xpos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(xpos))
	 *			| 	&& (! ypos == Double.POSITIVE_INFINITY)&&(! ypos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(ypos))
	 *			|	if (this.getWorld() != null) 
	 *			|		if (this.getRadius()<xpos)&&(xpos<(this.getWorld().getWidth()-this.getRadius()))&&
	 *			|			(this.getRadius()<ypos)&&(ypos<(this.getWorld().getHeight()-this.getRadius()))
	 *			| 			if (for each entity in getWorld().getAllEntities():
	 *			| 				Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
	 *			| 					Math.pow(ypos-entity.getYPosition(),2)))>
	 *			| 					0.99*(entity.getRadius()+getRadius()))
	 *			| 			then result == true
	 *			|		else
	 *			|			result == false
	 *			| 	else
	 *			|		result == true
	 *			| else
	 *			| 	result == false
	 */
	public boolean isValidPosition(double xpos, double ypos) {
		if ((Double.isNaN(xpos))
				|| (Double.isNaN(ypos)))
			return false;
		if (this.getWorld() != null) {
			if ((xpos>0.99*getRadius())&&(xpos<1.01*(getWorld().getWidth()-getRadius()))&&
					(ypos>0.99*getRadius())&&(ypos<1.01*(getWorld().getHeight()-getRadius()))) {
				for (Entity entity: getWorld().getEntities().keySet()) {
					if ((entity!=this)&&
							(Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
									Math.pow(ypos-entity.getYPosition(),2)))<
									0.99*(entity.getRadius()+getRadius()))
						return false;
				}
				return true;
			}
			return false;
			}
		return true;
	}
	
	/**
	 * Return the velocity of this entity along the x-axis.
	 */
	@Basic @Raw
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	/**
	 * Return the velocity of this entity along the y-axis.
	 */
	@Basic @Raw
	public double getYVelocity() {
		return this.yVelocity;
	}
	
	/**
	 * Set the velocity of this entity to a given velocity. If the entity is a ship with
	 * loaded bullets, this method also sets their velocities to the given velocity.
	 * 
	 * @param	xvel
	 * 			The new velocity of this entity along the x-axis.
	 * @param	yvel
	 * 			The new velocity of this entity along the y-axis.
	 * @post	If the speed of the entity with the new velocity does not exceed its maximum
	 * 			value, the new velocity of the entity is the given velocity. Otherwise,
	 * 			the velocity of the new ship is equal to the maximum speed of this entity.
	 * 			| if (sqrt(xvel^2 + yvel^2) <= old.getMaxSpeed())
	 * 			| 	then (new.getXVelocity() == xvel &&
	 * 			|		new.getYVelocity() == yvel);
	 * 			| else
	 * 			| 	(new.getXVelocity == xvel/sqrt(xvel^2 + yvel^2)*old.getMaxSpeed() &&
	 * 			|		new.getYVelocity == yvel/sqrt(xvel^2 + yvel^2)*old.getMaxSpeed());
	 * @post	If this entity is a ship, the velocities of its bullets are equal the given
	 * 			velocity.
	 * 			|if (this instanceof Ship)
	 * 			|	for (Bullet bullet: ((Ship)this).getBullets())
	 * 			|		bullet.getXVelocity() == xvel
	 * 			| 		bullet.getYVelocity() == yvel
	 */
	@Raw
	public void setVelocity(double xvel, double yvel) {
		if (Double.isNaN(xvel) || Double.isNaN(yvel)) {
			xvel = new Random().nextDouble() * this.maxSpeed;
			yvel = new Random().nextDouble() * this.maxSpeed;
		}
		double absVel = Math.sqrt(Math.pow(xvel,2) + Math.pow(yvel,2));
		if (absVel <= this.maxSpeed) {
			this.xVelocity = xvel;
			this.yVelocity = yvel; }
		else {
			this.xVelocity = xvel/absVel*this.maxSpeed;
			this.yVelocity = yvel/absVel*this.maxSpeed; }
	}
	
	/**
	 * Return the maximal speed of this entity.
	 */
	@Basic @Raw
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	
	/**
	 * Return the absolute value of the speed of the entity.
	 */
	@Raw
	public double getSpeed() {
		return Math.sqrt(Math.pow(getXVelocity(),2) + Math.pow(getYVelocity(),2));
	}
	
	/**
	 * Return the radius of this entity.
	 */
	@Basic @Raw
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Return whether this entity overlaps with the given entity.
	 * 
	 * @param 	other
	 * 			The entity with which this entity might overlap.
	 * @return	True if and only if the distance between the entities is nonpositive. 
	 * 			An entity thus always overlaps with itself.
	 * 			| result == (getDistanceBetween(this,other) <= 0)
	 * @throws 	IllegalEntityException
	 * 			At least one of the ships involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public boolean overlap(Entity other) throws IllegalArgumentException{
		if ((other == null)||(other.isTerminated()))
			throw new IllegalEntityException(other);
		if ((this == null)||(this.isTerminated()))
			throw new IllegalEntityException(this);
		return (getDistanceBetween(other) <= -0.01);
	}
	
	/**
	 * Return the distance between this entity and a given entity.
	 * 
	 * @param 	other
	 * 			The entity with which this entity will collide.
	 * @return	The distance between two entities is the distance between its centers 
	 * 			minus the radiuses of both entities. The distance can thus be negative. 
	 * 			The distance between an entity and itself is zero.
	 * 			| if (this == other)
	 *			| 	then result == 0
	 *			| else
	 *			|	result == (Math.sqrt(Math.pow(this.getXPosition()-other.getXPosition(),2)
	 *			|	+ Math.pow(this.getYPosition()-other.getYPosition(),2))
	 *			|	- this.getRadius() - other.getRadius())
	 * @throws 	IllegalEntityException
	 * 			At least one of the entities involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public double getDistanceBetween(Entity other) throws IllegalShipException {
		if ((other == null)||(other.isTerminated()))
			throw new IllegalEntityException(other);
		if (this.isTerminated())
			throw new IllegalEntityException(this);
		if (this == other)
			return 0;
		return (Math.sqrt(Math.pow(this.getXPosition()-other.getXPosition(),2)
					+ Math.pow(this.getYPosition()-other.getYPosition(),2))
					- this.getRadius() - other.getRadius());
	}
	
	/**
	 * Return the time until the first collision of this ship with the boundaries of its world, if any.
	 * 
	 * @return 	If this ship is not located in a world, positive infinity is returned.
	 * 			| if this.getWorld() == null
	 * 			| 	result == Double.POSITIVE_INFINITY	
	 * @return	If the ship doesn't have a velocity and thus never will reach the boundaries
	 * 			of its world, positive infinity is returned.
	 * 			| if (this.getXVelocity()==this.getYVelocity()==0)
	 * 			| 	result == Double.POSITIVE_INFINITY
	 * @return 	If this ship is located in a world, the first collision of this ship with the 
	 * 			boundaries of that world will happen after the returned time. Other entities in 
	 * 			this world are not taken into account.
	 * 			| result == Math.min(Math.max((0.99*getRadius()-getXPosition())/getXVelocity(),
	 * 			|	(1.01*(getWorld().getWidth()-getRadius())-getXPosition())/getXVelocity()),
	 * 			|	Math.max((0.99*getRadius()-getYPosition())/getYVelocity(),
	 * 			|	(1.01*(getWorld().getHeight()-getRadius())-getYPosition())/getYVelocity())
	 */
	public double getTimeToBoundary() {
		if (this.getWorld()==null)
			return Double.POSITIVE_INFINITY;
		double X;
		double Y;
		if (this.getXVelocity() < 0) {
			X = (getRadius()-getXPosition())/getXVelocity();
		}
		else if (this.getXVelocity() > 0) {
			X = ((getWorld().getWidth()-getRadius())-getXPosition())/getXVelocity();
		}
		else
			X = Double.POSITIVE_INFINITY;
		if (this.getYVelocity() < 0) {
			Y = (getRadius()-getYPosition())/getYVelocity();
		}
		else if (this.getYVelocity() > 0) {
			Y = ((getWorld().getWidth()-getRadius())-getYPosition())/getYVelocity();
		}
		else
			Y = Double.POSITIVE_INFINITY;
		return Math.min(X, Y);
		}
	
	/**
	 * Return where this entity will first collide with a boundary of its world, if anywhere.
	 * 
	 * @return	If the time to the boundary is infinity, null is returned.
	 * 			| if (this.getTimeToBoundary()==Double.POSITIVE_INFINITY)
	 * 			|	result == null
	 * @return	If this entity will reach a boundary of its world, the position of its first
	 * 			collision is returned.
	 * 			| xpos = getXPosition()+getXVelocity()*getTimeToBoundary()
	 * 			| ypos = getYPosition()+getYVelocity()*getTimeToBoundary()
	 * 			| if (xpos<=getRadius())
	 * 			|	result == {0,getYPosition()+getYVelocity()*getTimeToBoundary()};
	 * 			| if (xpos>=getWorld().getWidth()-getRadius())
	 * 			|	result == {getWorld().getWidth(),getYPosition()+getYVelocity()*getTimeToBoundary()};
	 * 			| if (ypos<=getRadius())
	 * 			|	result == {getXPosition()+getXVelocity()*getTimeToBoundary(),0};
	 * 			| else
	 * 			|	result == {getXPosition()+getYVelocity()*getTimeToBoundary(),getWorld().getHeight()};		
	 */
	public double[] getBoundaryPosition() {
		double time = this.getTimeToBoundary();
		if (time==Double.POSITIVE_INFINITY)
			return null;
		double xpos = getXPosition()+getXVelocity()*time;
		double ypos = getYPosition()+getYVelocity()*time;
		if (xpos<=getRadius())
			return new double[]{0,ypos};
		else if (xpos>=getWorld().getWidth()-getRadius())
			return new double[]{getWorld().getWidth(),ypos};
		else if (ypos<=getRadius())
			return new double[]{xpos,0};
		else 
			return new double[]{xpos,getWorld().getHeight()};
	}
	
	/**
	 * Return the time until this ship will collide with the given ship for the first time.
	 * 
	 * @param	other
	 * 			The second ship, with which the collision will happen.
	 * @return	If the ships do not yet overlap, they will if they move for the returned amount of time.
	 * 			If the ships will never collide, they are considered to collide after an infinite 
	 * 			amount of time.
	 * 			| if (! overlap(other))
	 * 			| 	this.move(result)
	 * 			| 	other.move(result)
	 * 			| this.getDistanceBetween(other) == 0
	 * @return	No collision between this ship and the other ship will occur within the returned amount
	 * 			of time.
	 * 			| time = Math.random()*result
	 * 			| if (! overlap(other))
	 * 			| 	if (time<result)
	 * 			| 		this.move(Math.random()*result)
	 *			| 		other.move
	 *			| this.getDistanceBetween(other) > 0
	 * @throws	IllegalEntityException
	 * 			The ships already overlap.
	 * 			| overlap(other)
	 * @throws	IllegalEntityException
	 * 			At least one of the ships involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public double getTimeToCollision(Entity other) throws IllegalEntityException {
		if ((other == null)||(other.isTerminated()))
			return Double.POSITIVE_INFINITY;
		if ((this == null)||(this.isTerminated()))
			return Double.POSITIVE_INFINITY;
		if (this.getWorld()!=other.getWorld())
			return Double.POSITIVE_INFINITY;
		if (this.getWorld()==null)
			return Double.POSITIVE_INFINITY;
		if (this.overlap(other))
			throw new IllegalEntityException(this);
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
	 * Return the position where this entity will collide with another entity.
	 * 
	 * @param	other
	 * 			The entity with which this entity will collide.
	 * @return	The position of collision if they collide. Null if they never will collide.
	 * 			| dt = this.getTimeToCollision(other)
	 * 			| if (dt != Double.POSITIVE_INFINITY)
	 * 			| 	result == {other.getXPosition() - this.getXPosition() + dt*(other.getXVelocity() - this.getXVelocity()) *
	 * 			| 		(this.getRadius() / other.getRadius()) + this.getXPosition(),
	 *			| 		other.getYPosition() - this.getYPosition() + dt * (other.getYVelocity() - this.getYVelocity()) *
	 *			| 		(this.getRadius() / other.getRadius()) + this.getYPosition()}
	 *			| else
	 *			| 	result == null
	 */
	public double[] getCollisionPosition(Entity other) throws IllegalEntityException {
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
	 * Return whether this entity is terminated.
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * Resolve the collision of this entity with another given entity.
	 * 
	 * @param 	other
	 * 			The entity to collide with.
	 * @post	If this entity and the other entity are both bullets, the method will
	 * 			terminate them.
	 * 			| if (old instanceof Bullet) && ((old other) istanceof Bullet)
	 * 			| 	new.isTerminated()
	 * 			|	(new other).isTerminated()
	 * @post	If one of the entities is a ship and the other is a bullet that is not
	 * 			fired by that ship, the method will terminate them. If the bullet is
	 * 			fired by the ship, the bullet will be loaded into the ship.
	 * 			| if ((old instanceof Bullet) && ((old other) instanceof Ship) && 
	 * 			| 	old.getSource != other)) || 
	 * 			|	((old other) instanceof Bullet) && (old instanceof Ship)
	 * 			|	&& (old other).getSource != this)))
	 * 			| 		new.isTerminated()
	 * 			|		(new other).isTerminated()
	 * 			| else
	 * 			|	if (old instanceof Ship)
	 * 			|		new.getBullets().contains(new other)
	 * 			|	else if (old instanceof Bullet)
	 * 			|		(new other).getBullets().contains(new)
	 * @post	If the two entities are ships, they will bounce off each other.
	 * 			| if (this instanceof ship && other instanceof Ship)
	 * 			| 	@see implementation
	 */
	public void collide(Entity other){
		if (this instanceof Bullet) {
			if (((Bullet)this).getSource()==other)
				((Ship)other).loadBullet((Bullet)this);
			else {
				this.terminate();
				other.terminate();
			}
		}
		else if (other instanceof Bullet) {
			if (((Bullet)other).getSource()==this)
				((Ship)this).loadBullet((Bullet)other);
			else {
				this.terminate();
				other.terminate();
			}
		}
		else if ((this instanceof Ship && other instanceof Ship) || (this instanceof MinorPlanet && other instanceof MinorPlanet)){
			double dvdr = (other.getXVelocity()-this.getXVelocity())*
				(other.getXPosition()-this.getXPosition()) +
				(other.getYVelocity()-this.getYVelocity())*
				(other.getYPosition()-this.getYPosition());
			double sigma = Math.sqrt(Math.pow(this.getXPosition()-other.getXPosition(), 2)+
				Math.pow(this.getYPosition()-other.getYPosition(), 2));
			double J = 2*this.getMass()*other.getMass()*
					dvdr/(sigma*(this.getMass()+other.getMass()));
			double JX = J*(other.getXPosition()-this.getXPosition())/sigma;
			double JY = J*(other.getYPosition()-this.getYPosition())/sigma;
			this.setVelocity(getXVelocity()+JX/this.getMass(), 
					getYVelocity()+JY/this.getMass());
			other.setVelocity(other.getXVelocity()-JX/other.getMass(), 
					other.getYVelocity()-JY/other.getMass());
			}
		else if (this instanceof Ship && other instanceof MinorPlanet) {
			((MinorPlanet)other).collideShip(((Ship)this));
		}
		else if (other instanceof Ship && this instanceof MinorPlanet) {
			((MinorPlanet)this).collideShip(((Ship)other));
		}
	}
	
	/**
	 * Resolve the first collision of this entity with a boundary.
	 * 
	 * @post	If the collision position of this entity with a boundary of its world is not null,
	 * 			the velocity of the entity will be altered. If the entity collides
	 * 			with a vertical boundary, its X velocity is reversed.
	 * 			If he hits a horizontal boundary, its Y velocity is reversed.
	 * 			| pos = old.getBoundaryPosition()
	 * 			| if (pos!=null)
	 * 			|	if ((pos[0]==0)||(pos[0]==old.getWorld().getWidth()))
	 * 			|		new.getXVelocity() == -old.getXVelocity()
	 * 			|	else
	 * 			|		new.getYVelocity() == -old.getYVelocity()
	 * @post	If this entity is a bullet, the amount of bounces done increases by
	 * 			one.
	 * 			| if (old instanceof Bullet)
	 * 			|	(new.getNbBounces()==old.getNbBounces()+1)||(new.isTerminated())
	 */
	public void collideBoundary() {
		double[] pos = getBoundaryPosition();
		if (pos!=null) {
			if ((pos[0]==0)||(pos[0]==this.getWorld().getWidth())) {
				if (0 == pos[1] - getRadius() || getWorld().getHeight() == pos[1] + getRadius())
					setVelocity(-getXVelocity(),-getYVelocity());
				else
					setVelocity(-getXVelocity(),getYVelocity());
			}
			else if ((pos[1]==0)||(pos[1]==this.getWorld().getHeight())) {
				if (0 == pos[0] - getRadius() || getWorld().getHeight() == pos[0] + getRadius())
					setVelocity(-getXVelocity(),-getYVelocity());
				else 
					setVelocity(getXVelocity(),-getYVelocity());
				}
				
		}
		if (this instanceof Bullet)
			((Bullet)this).setBounces(((Bullet)this).getBounces()+1);
	}
	
	/**
	 * Move the ship for a given amount of time according to its current position, velocity, 
	 * and orientation.
	 * 
	 * @param 	dt
	 * 			The duration of the movement.
	 * @post	The new position of the ship is the position it reaches if starts from its old position
	 * 			and its orientation and acceleration do not change during the movement.
	 * 			| new.getXPosition() == old.getXPosition() + dt*old.getXVelocity()
	 * 			| new.getYPosition() == old.getYPosition() + dt*old.getYVelocity()
	 * @post	Each bullet loaded in this ship is moved along with this ship.
	 * 			| for (bullet: old.getBullets()
	 * 			| 	new.getXPosition() == old.getXPosition() + dt*old.getXVelocity()
	 * 			| 	new.getYPosition() == old.getYPosition() + dt*old.getYVelocity()
	 * @throws 	IllegalDurationException
	 * 			The given duration of the movement is negative.
	 * 			| dt < 0
	 */
	public void move(double dt) throws IllegalDurationException, IllegalPositionException {
		if (dt <= 0)
			throw new IllegalDurationException(dt);
		else if (dt > 0) {
			this.setPosition(getXPosition()+getXVelocity()*dt,
					getYPosition()+getYVelocity()*dt);
		}
	}
	
	/**
	 * Return the world with which this entity is associated. Null if none.
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Set the world of this ship to the given world.
	 * 
	 * @param 	world
	 * 			The world, in which the ship has to be located.
	 * @post	If the ship does not belong to a world yet, the new world of this ship 
	 * 			is the given world.
	 * 			| if (old.getWorld() == null)
	 *			|	new.world == world;		
	 * @post	If the given world is null, the world of this ship is null.
	 * 			| if (world==null)
	 * 			| 	new.world == null;
	 */
	@Raw
	public void setWorld(World world) {
		if ((this.getWorld()==null)||(world==null))
				this.world = world;
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
	 * 			| 	new.getMaxSpeed == maxSpeed
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
	
	public double getMassDensity() {
		return this.massDensity;
	}
	
	/**
	 * A variable registering the position of this entity along the x-axis.
	 */
	private double xPosition;
	
	/**
	 * A variable registering the position of this entity along the y-axis. 
	 */
	private double yPosition;
	
	/**
	 * A variable registering the velocity of this entity along the x-axis.
	 */
	private double xVelocity;
	
	/**
	 * A variable registering the velocity of this entity along the y-axis.
	 */
	private double yVelocity;
	
	/**
	 * A variable registering the maximum speed of this entity.
	 */
	protected double maxSpeed = MAX_SPEED;
	
	/**
	 * A variable registering the radius of this entity.
	 */
	protected double radius;
	
	/**
	 * A variable registering whether this entity is terminated.
	 */
	protected boolean isTerminated;
	
	/**
	 * A variable registering the world of this entity.
	 */
	protected World world;
	
	/**
	 * A variable registering the mass density of this entity.
	 */
	protected double massDensity;
	
	/**
	 * A variable registering the maximum speed of an entity.
	 */
	protected static double MAX_SPEED = 300000;

	public abstract void setRadius(double radius);
	public abstract double getMass();
	public abstract void terminate();
	public abstract void setMassDensity(double massDensity);
	
}

