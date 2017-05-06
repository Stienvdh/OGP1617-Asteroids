package asteroids.model.exceptions;

import asteroids.model.Ship;

/** 
 * A class of exceptions signaling an illegal ship as argument.
 */
public class IllegalShipException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize this new illegal ship exception with given ship.
	 * 
	 * @param 	ship
	 * 			The ship for this new illegal ship exception.
	 * @post	The ship of this new illegal ship exception is the given ship.
	 * 			| new.getShip() == ship
	 * @effect	This new illegal ship exception is further initialized as a new runtime
	 * 			exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalShipException(Ship ship) {
		this.ship = ship;
	}
	
	/**
	 * Return the duration of this illegal duration exception.
	 */
	public Ship getShip() {
		return this.ship;
	}

	/**
	 * A variable registering the duration of this illegal duration exception.
	 */
	private final Ship ship;
}