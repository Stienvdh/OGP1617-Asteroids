package asteroids.model.exceptions;

import asteroids.model.Entity;

/** 
 * A class of exceptions signaling an illegal entity argument.
 */
public class IllegalEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new entity exception with given entity.
	 * 
	 * @param 	entity
	 * 			The entity for this new illegal entity exception.
	 * @post	The entity of this new illegal entity exception is the given entity.
	 * 			| new.getEntity() == entity
	 * @effect	This new illegal entity exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalEntityException(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Return the entity of this illegal entity exception.
	 */
	public Entity getEntity() {
		return this.entity;
	}

	/**
	 * A variable registering the entity of this illegal entity exception.
	 */
	private final Entity entity;
}
