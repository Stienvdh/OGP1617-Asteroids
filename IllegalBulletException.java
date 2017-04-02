package asteroids.model;

/** 
 * A class of exceptions signaling an illegal bullet as argument.
 */
public class IllegalBulletException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal bullet exception with given bullet.
	 * 
	 * @param 	bullet
	 * 			The bullet for this new illegal bullet exception.
	 * @post	The bullet of this new illegal bullet exception is the given bullet.
	 * 			| new.getBullet() == bullet
	 * @effect	This new illegal bullet exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalBulletException(Bullet bullet) {
		this.bullet = bullet;
	}
	
	/**
	 * Return the duration of this illegal duration exception.
	 */
	public Bullet getBullet() {
		return this.bullet;
	}

	/**
	 * A variable registering the duration of this illegal duration exception.
	 */
	private final Bullet bullet;
}