package asteroids.model;

import java.util.HashMap;
import java.util.HashSet;

public class World {
	
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
		if ((! (width>UPPER_WIDTH))&&(! (height>UPPER_HEIGHT))) {
			this.width = width;
			this.height = height; }
		else
			this.width = UPPER_WIDTH;
			this.height = UPPER_HEIGHT;
	}
	
	/**
	 * Return the entities, located in this world.
	 */
	public HashMap<double[],Entity> getEntities() {
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
	 * @throws	IllegalEntityException
	 * 			The given entity is already located in another world or ship.
	 * 			| entity.hasPosition()
	 *OVERLAPDINGEN
	 */
	public void addEntity(Entity entity) {
		if (entity instanceof Ship)
			this.getShips().add((Ship) entity);
		else if (entity instanceof Bullet)
			this.getShips().add((Ship) entity);
		double[] pos = {entity.getXPosition(),entity.getYPosition()};
		this.getEntities().put(pos, entity);
		entity.setWorld(this);
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
		double[] pos = {xpos,ypos};
		if (this.getEntities().containsKey(pos))
				return this.getEntities().get(pos);
		else
			return null;
	}
	
	/**
	 * Return the ships, located in this world.
	 */
	public HashSet<Ship> getShips() {
		return this.ships;
	}
	
	/**
	 * Return the bullets, located in this world.
	 */
	public HashSet<Bullet> getBullets() {
		return this.bullets;
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
	public HashMap<double[],Entity> entities;
	
	/**
	 * A variable registering the ships, located in this world.
	 */
	public HashSet<Ship> ships;
	
	/**
	 * A variable registering the bullets, located in this world.
	 */
	public HashSet<Bullet> bullets;
	
	/**
	 * A variable registering the upper bound for the width of a world.
	 */
	private static final double UPPER_WIDTH = Double.MAX_VALUE;
	
	/**
	 * A variable registering the upper bound for the width of a world.
	 */
	private static final double UPPER_HEIGHT = Double.MAX_VALUE;
}
