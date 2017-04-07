package asteroids.model;

public abstract class Entity {
	
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
	
	public abstract boolean hasPosition();
	public abstract double getXPosition();
	public abstract double getYPosition();
	public abstract double getRadius();
	public abstract void setWorld(World world);
	public abstract boolean isTerminated();
}
