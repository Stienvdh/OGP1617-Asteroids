package asteroids.model.exceptions;

import asteroids.model.World;

public class IllegalWorldException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new world exception with given world.
	 * 
	 * @param 	world
	 * 			The world for this new illegal world exception.
	 * @post	The world of this new illegal world exception is the given world.
	 * 			| new.getWorld() == world
	 * @effect	This new illegal world exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalWorldException(World world) {
		this.world = world;
	}
	
	/**
	 * Return the world of this illegal world exception.
	 */
	public World getWorld() {
		return this.world;
	}

	/**
	 * A variable registering the world of this illegal world exception.
	 */
	private final World world;
}
