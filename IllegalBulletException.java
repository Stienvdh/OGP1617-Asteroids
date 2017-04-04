package asteroids.model;

/** 
 * A class of exceptions signaling an illegal bullet argument.
 */
public class IllegalBulletException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new bullet exception with given bullet.
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
	 * Return the bullet of this illegal bullet exception.
	 */
	public Bullet getBullet() {
		return this.bullet;
	}

	/**
	 * A variable registering the bullet of this illegal bullet exception.
	 */
	private final Bullet bullet;
}