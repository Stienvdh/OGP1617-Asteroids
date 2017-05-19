package asteroids.model;

import java.util.HashMap;
import java.util.Set;

import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalEntityException;
import asteroids.model.exceptions.IllegalWorldException;

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
	 * @post	If nor the given width, nor the given height exceeds its upper bound, the size of this
	 * 			world is set to the given width and height. Otherwise, its size is set to its
	 * 			upper bound.
	 * 			| if ! width > UPPER_WIDTH && ! height > UPPER_HEIGHT
	 * 			| 	then new.getWidth() == width && new.getHeight() == height
	 * 			| else
	 * 			| 	new.getWidth() == UPPER_WIDTH && new.getHeight() == UPPER_HEIGHT
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
	 * @post	The world contains the given entity.
	 * 			| new.getEntities().contains(entity)
	 * @post	The given entity is located in this world. 
	 * 			| (new entity).getWorld() == this
	 * 			| (new entity).hasPosition()
	 * @post	If the given entity does not have a valid position in this world, the entity is
	 * 			terminated.
	 * 			| if (! (old entity).isValidPosition(entity.getXPosition(),
	 * 			| 	(old entity).getYPosition()))
	 * 			| 	(new entity).isTerminated()
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
//				throw new IllegalEntityException(entity);
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
	 * @post	This world does not contain the given entity.
	 * 			| (! new.getEntities().contains(entity))
	 * 			| if entity instanceof Ship
	 * 			| 	(! new.getShips().contains(entity))
	 * 			| if entity instanceof Bullet
	 * 			| 	(! new.getBullets().contains(entity))
	 * @post	This entity is no longer associated with this world.
	 * 			| (new entity).getWorld() == null
	 */
	public void removeEntity(Entity entity) throws IllegalEntityException {
		if (entity == null)
			throw new IllegalEntityException(entity);
		else if (entity.getWorld() != this)
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
	 * 			| if this.getEntities().containsValue([xpos,ypos])
	 * 			| 	result.getXPosition() == xpos
	 * 			| 	result.getYPosition() == ypos
	 * 			| else
	 * 			| 	result == null
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
	 * @post	This world does not contain any entities.
	 * 			| (for each entity: old.getEntities())
	 * 			| 	entity.getWorld() == null
	 * 			| 	entity.getSource() == null
	 * 			| new.getEntities().size() == 0
	 * 			| new.getShips().size() == 0
	 * 			| new.getBullets().size() == 0
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
//				if (entity instanceof Ship) {
//					entity.setVelocity(entity.getXVelocity()+
//							Math.min(boundary, collision)*((Ship)entity).getAcceleration()
//							*Math.cos(((Ship) entity).getOrientation()), 
//							entity.getYVelocity()+Math.min(boundary, collision)
//							*((Ship)entity).getAcceleration()*
//							Math.sin(((Ship) entity).getOrientation()));
//				}
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
	 * A variable registering the width of this world.
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
	 * A variable registering the upper bound for the width of a world.
	 */
	private static final double UPPER_HEIGHT = Double.MAX_VALUE;
}
