package asteroids.model;

import java.util.HashMap;
import java.util.Set;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalEntityException;
import asteroids.model.exceptions.IllegalWorldException;
import asteroids.model.exceptions.IllegalPositionException;

public class World {
	
	/**
	 * Initialize this new world with given width and height.
	 * 
	 * @param 	width
	 * 			The width of this new world.
	 * @param 	height
	 * 			The height of this new world.
	 * @post	The width of this new ship is equal to the given width.
	 * 			| new.getWidth() == width
	 * 			| new.getHeight() == height
	 */
	public World(double width, double height) {
		this.setSize(width,height);
	}
	
	/**
	 * Return the width of this world.
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Return the height of this world.
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Set the width and height to given values.
	 * 
	 * @param 	width
	 * 			The new width of this world.
	 * @param 	height
	 * 			The new height of this world.
	 * @post	If the given width and/or height doesn't exceed the upper bound or isn't NaN, the width and/or height of the world
	 * 			is set to the given width, else the upper bound.
	 * 			If a height or a width is negative, the height or the width of the world is set to zero.
	 * 			|if (height>UPPER_HEIGHT)|| Double.isNaN(height)
	 *			|	then new.getHeight() = UPPER_HEIGHT;
	 *			|else if (height < 0)
	 *	 		|	then new.getHeight() = 0.0;
	 *			|else
	 *			|	then new.getHeight() = height;
	 *			|if ((width>UPPER_WIDTH)|| Double.isNaN(width))
	 *			|	then new.getWidth() = UPPER_WIDTH;
	 *			|else if (width < 0)
	 *			|	then new.getWidth() = 0.0;
	 *			|else
	 *			|	then new.getWidth() = width;
	 */
	public void setSize(double width, double height) {
		if ((height>UPPER_HEIGHT)|| Double.isNaN(height))
			this.height = UPPER_HEIGHT;
		else if (height < 0)
			this.height = 0.0;
		else
			this.height = height;
		if ((width>UPPER_WIDTH)|| Double.isNaN(width))
			this.width = UPPER_WIDTH;
		else if (width < 0)
			this.width = 0.0;
		else
			this.width = width;
	}
	
	/**
	 * Return the entities, located in this world.
	 */
	public HashMap<Entity,double[]> getEntities() {
		return this.entities;
	}
	
	/**
	 * Add a given entity to this world.
	 * 
	 * @param 	entity
	 * 			The entity, that has to be added to this world.
	 * @post	The world contains the given entity and the entity is not null.
	 * 			| new.getEntities().contains(entity) || entity == null
	 * @post	If the entity already is located in a world, it is removed from that world. 
	 * 			|if entity.getWorld() != null
	 *			|	then entity.getWorld().removeEntity(entity)
	 * @post	If the given entity does not have a valid position in this world, the entity is
	 * 			terminated.
	 * 			| if ! (old entity).isValidPosition((old entity).getXPosition(),(old entity).getYPosition())
	 * 			| 	then (new entity).isTerminated()
	 * @post	If the entity is not terminated and is located in a world, the entity is added to the list of entities.
	 * 			|if (! entity.isTerminated()) && (entity.getWorld()!=null) 
	 *			|	then double[] pos = {entity.getXPosition(),entity.getYPosition()};
	 *			|	getEntities().put(entity, pos);
	 * @throws	IllegalEntityException
	 * 			The given entity has no valid position.
	 * 			|! (old entity).isValidPosition((old entity).getXPosition(),(old entity).getYPosition())
	 */
	public void addEntity(Entity entity) throws IllegalEntityException {
		if (getEntities().containsKey(entity) || entity == null)
			throw new IllegalEntityException(entity);
		if (entity.getWorld() != null) {
			entity.getWorld().removeEntity(entity);
		}
		if (((! (entity instanceof Bullet)))||(((Bullet)entity).getSource()==null)) {
			entity.setWorld(this);
			if (! entity.isValidPosition(entity.getXPosition(),entity.getYPosition())) {
				entity.terminate();
				throw new IllegalEntityException(entity);
			}
		}
		if ((! entity.isTerminated())&&(entity.getWorld()!=null)) {
			double[] pos = {entity.getXPosition(),entity.getYPosition()};
			this.getEntities().put(entity, pos);
		}
	}
	
	/**
	 * Remove a given entity from this world.
	 * 
	 * @param	entity
	 * 			The entity to remove from this world.
	 * @post	The entity is removed from this world.
	 * 			|this.getEntities().remove(entity);
	 *			|entity.setWorld(null);
	 * @throws	IllegalEntityException
	 * 			The given entity is null or isn't located in this world.
	 * 			|entity == null || entity.getWorld() != this
	 */
	public void removeEntity(Entity entity) throws IllegalEntityException {
		if (entity == null || entity.getWorld() != this)
			throw new IllegalEntityException(entity);
		else {
			this.getEntities().remove(entity);
			entity.setWorld(null);
		}
	}
	
	/**
	 * Return, if any, the entity whose center coincides with the given position. 
	 * 
	 * @param	xpos
	 * 			The position along the x-axis to investigate.
	 * @param	ypos
	 * 			The position along the y-axis to investigate. 
	 * @return 	Return the entity whose center coincides with the given position. Null if none.
	 * 			|for (Entity entity: getEntities().keySet())
	 *			|	if (getEntities().get(entity)[0]==xpos) && (getEntities().get(entity)[1]==ypos)
	 *			|		result == entity
	 * 			|result == null
	 */
	public Entity getEntityAt(double xpos,double ypos) {
		for (Entity entity: getEntities().keySet()) {
			if ((getEntities().get(entity)[0]==xpos)&&(getEntities().get(entity)[1]==ypos))
				return entity;
		}
		return null;
	}
	
	/**
	 * Return whether this world is terminated.
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * Terminate this world.
	 *
	 * @post	The state of this world is terminated.
	 * 			| new.isTerminated()
	 * @post	This world does not contain any entities and the bullets don't have sources.
	 * 			| (for each entity: old.getEntities())
	 * 			|		if (entity instanceof Bullet)
	 *			|			then entity.getSource(null)
	 *			|		entity.getWorld(null);
	 * @post 	The list of entities is cleared.
	 * 			|getEntities.isEmpty()
	 */
	public void terminate() {
		this.isTerminated=true;
		for (Entity entity: this.getEntities().keySet()) {
			if (entity instanceof Bullet)
				((Bullet) entity).setSource(null);
			entity.setWorld(null);
		}
		this.entities.clear();
	}
	
	/**
	 * Return the time until the first collision in this world.
	 * 
	 * @return	The time until the first collision, that will occur in this world, of an entity 
	 * 			within that world with either a boundary of that world or another entity 
	 * 			in that world.
	 * 			| for (entity1: getAllEntities())
	 * 			| 	result <= entity1.getTimeToBoundary()
	 * 			| 		for (entity2: getAllEntities())
	 * 			| 			result <= entity1.getTimeToCollision(entity2)
	 */
	public double getTimeFirstCollision() {
		double boundary = Double.POSITIVE_INFINITY;
		double collision = Double.POSITIVE_INFINITY;
		double bound = Double.POSITIVE_INFINITY;
		double coll = Double.POSITIVE_INFINITY;
		for (Entity entity1: this.getEntities().keySet()) {
			bound = entity1.getTimeToBoundary();
			if (bound < boundary) {
				boundary = bound;
			}
			for (Entity entity2: this.getEntities().keySet()) {
				coll = entity1.getTimeToCollision(entity2);
				if (coll<collision) {
					collision = coll;
				}
		}
	}
		return Math.min(boundary, collision);
	}
	
	/**
	 * Return the position of the first collision in this world.
	 * 
	 * @return	The position, where the first collision of an entity within this world
	 * 			with either a boundary of this world or another entity in that world.
	 * 			| getAllEntities().move(getTimeFirstCollision)
	 * 			| for (entity: getAllEntities())
	 * 			|	if (! entity1.isValidPosition(entity1.getXPosition(),entity1.getYPosition())
	 * 			| 		if (entity1.getTimeToBoundary()==0)
	 * 			| 			result == entity1.getBoundaryPosition()
	 * 			| 		for (entity2: getAllEntities())
	 * 			| 			if (entity2!=entity1)&&(!entity2.isValidPosition(
	 * 			|				entity2.getXPosition(),entity2.getYPosition())
	 * 			| 				result == entity1.getCollisionPosition(entity2)
	 * @return	Null if nothing collides.
	 * 			|if (Math.min(boundary, collision)==Double.POSITIVE_INFINITY)
	 *			|	then result == null
	 */
	public double[] getFirstCollisionPosition() {
		double boundary = Double.POSITIVE_INFINITY;
		Entity entityB = null;
		double collision = Double.POSITIVE_INFINITY;
		Entity entityC1 = null;
		Entity entityC2 = null;
		double bound = Double.POSITIVE_INFINITY;
		double coll = Double.POSITIVE_INFINITY;
		for (Entity entity1: this.getEntities().keySet()) {
			bound = entity1.getTimeToBoundary();
			if (bound < boundary) {
				boundary = bound;
				entityB = entity1;
			}
			for (Entity entity2: this.getEntities().keySet()) {
				coll = entity1.getTimeToCollision(entity2);
				if (coll<collision) {
					collision = coll;
					entityC1 = entity1;
					entityC2 = entity2;
				}
			}
		}
		if (Math.min(boundary, collision)==Double.POSITIVE_INFINITY) {
			return null;
		}
		if (boundary<collision)
			return entityB.getBoundaryPosition();
		else
			return entityC1.getCollisionPosition(entityC2);	
		}
	
	/**
	 * A method to evolve this world for a given duration.
	 * 
	 * @param	dt
	 * 			The duration time of the evolution of this world.
	 * @post	If the time until the first collision is shorter than dt, all the entities
	 * 			move for that period of time. The collision is resolved and the world	
	 * 			evolves with the remaining time. If the time until the first collision is
	 * 			longer than dt, all the entities move for the time dt.
	 * 			|for (entity1: getAllEntities())
	 * 			|	time <= entity1.getTimeToBoundary()
	 * 			|		for (entity2: getAllEntities())
	 * 			|			time <= entity1.getTimeToCollision(entity2)
	 * 			|if (time < dt)
	 * 			|	for (entity: getAllEntities())
	 *			|		entity.move(time)
	 *			|	if (boundary<collision)
	 *			|		then entity1.collideBoundary()
	 *			|			 this.evolve(dt-time)
	  *			|	else
	 *			|		entity1.collide(entity2)
	 *			|		this.evolve(dt-time)
	 * 			
	 */
	public void evolve(double dt) throws IllegalEntityException, IllegalWorldException, IllegalDurationException {
		if (dt<0 || Double.isNaN(dt))
			throw new IllegalDurationException(dt);
		double boundary = Double.POSITIVE_INFINITY;
		Entity entityB = null;
		double collision = Double.POSITIVE_INFINITY;
		Entity entityC1 = null;
		Entity entityC2 = null;
		double bound = Double.POSITIVE_INFINITY;
		double coll = Double.POSITIVE_INFINITY;
		for (Entity entity1: this.getEntities().keySet()) {
			bound = entity1.getTimeToBoundary();
			if (bound < boundary) {
				boundary = bound;
				entityB = entity1;
			}
			for (Entity entity2: this.getEntities().keySet()) {
				coll = entity1.getTimeToCollision(entity2);
				if ((coll<collision)&&(coll>0)) {
					collision = coll;
					entityC1 = entity1;
					entityC2 = entity2;
				}
			}
		}
		if (Math.min(boundary, collision)>dt) {
			for (Entity entity: this.getAllEntities()) {
				entity.move(dt);
			}
		}
		else if (Math.min(boundary, collision)<dt) {
			for (Entity entity: this.getAllEntities()) {
				entity.move(Math.min(boundary, collision));
			}
			if (boundary<collision) {
				entityB.collideBoundary();
				this.evolve(dt-boundary);
			}
			else {
				entityC1.collide(entityC2);
				this.evolve(dt-collision);
			}
		}
	}
	
	/**
	 * Return the entities in this world.
	 */
	public Set<Entity> getAllEntities() {
		return getEntities().keySet();
	}

	/**
	 * A variable registering the height of this world.
	 */
	public double height;
	
	/**
	 * A variable registering the width of this world.
	 */
	public double width;
	
	/**
	 * A variable registering the entities, located in this world, and the position of their center.
	 */
	public HashMap<Entity,double[]> entities = new HashMap<Entity,double[]>();
	
	/**
	 * A variable registering whether this world is terminated.
	 */
	public boolean isTerminated = false;
	
	/**
	 * A variable registering the upper bound for the width of a world.
	 */
	private static final double UPPER_WIDTH = Double.MAX_VALUE;
	
	/**
	 * A variable registering the upper bound for the height of a world.
	 */
	private static final double UPPER_HEIGHT = Double.MAX_VALUE;
}
