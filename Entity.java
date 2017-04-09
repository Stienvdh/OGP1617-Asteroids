package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class Entity {
	
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
	 * Set the position of this entity to a given position.
	 * 
	 * @param	xpos
	 * 			The position of this entity along the x-axis.
	 * @param	ypos
	 * 			The position of this entity along the y-axis.
	 * @post	The new position of this entity is equal to the given position.
	 * 			| new.getXPosition() == xpos
	 * 			| new.getYPosition() == ypos
	 * @throws	IllegalPositionException
	 * 			The given position is not valid.
	 * 			| ! isValidPosition()
	 */
	@Basic @Raw
	public void setPosition(double xpos, double ypos) {
		if (! isValidPosition(xpos, ypos))
			throw new IllegalPositionException(xpos, ypos);
		this.xPosition = xpos;
		this.yPosition = ypos;
	}
	
	public abstract boolean isValidPosition(double xpos,double ypos);
	
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
	 * Set the velocity of this entity to a given velocity.
	 * 
	 * @param	xvel
	 * 			The new velocity of this entity along the x-axis.
	 * @param	yvel
	 * 			The new velocity of this entity along the y-axis.
	 * @post	If the speed of the entity with the new velocity does not exceed its maximum
	 * 			value, the new velocity of the entity is the given velocity. Otherwise,
	 * 			the velocity of the new ship is equal to the maximum speed of this entity..
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
	 * Return the radius of this ship.
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
	 * @return	True if and only if the distance between the entities is positive. An entity thus always overlaps 
	 * 			with itself.
	 * 			| result == (getDistanceBetween(this,other) <= 0)
	 * @throws 	IllegalEntityException
	 * 			At least one of the ships involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
	 */
	public boolean overlap(Entity other) throws IllegalArgumentException{
		if ((other == null)||(other.isTerminated()))
			throw new IllegalEntityException(other);
		if ((this == null)||(this.isTerminated()))
			throw new IllegalEntityException(this);
		return (getDistanceBetween(other) <= 0);
	}
	
	/**
	 * Return the distance between this entity and a given entity.
	 * 
	 * @param 	other
	 * 			The entity with which this entity will collide.
	 * @return	The distance between two entities is the distance between its centers minus the radiuses of both 
	 * 			entities. The distance can thus be negative. The distance between an entity and itself is zero.
	 * 			| if (this == other)
	 *			| 	then result == 0
	 *			| else
	 *			|	result == (Math.sqrt(Math.pow(this.getXPosition()-other.getXPosition(),2)
	 *			|	+ Math.pow(this.getYPosition()-other.getYPosition(),2))
	 *			|	- this.getRadius() - other.getRadius())
	 * @throws 	IllegalEntityException
	 * 			At least one of the entities involved is ineffective or terminated.
	 * 			| (other == null) || (other.isTerminated()) || (this == null) || (this.isTerminated())
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
	 * Return whether this bullet is terminated.
	 */
	public boolean isTerminated() {
		return this.isTerminated;
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
	protected double maxSpeed;
	
	/**
	 * A variable registering the radius of this entity.
	 */
	protected double radius;
	
	/**
	 * A variable registering whether this bullet is terminated.
	 */
	protected boolean isTerminated;
	
	public abstract void setMaxSpeed(double maxSpeed);
	public abstract void setRadius(double radius);
	public abstract double getMass();
	public abstract double getMassDensity();
	public abstract void setWorld(World world);
	public abstract void move(double dt);
	public abstract double getTimeToBoundary();
}
