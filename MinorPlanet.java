package asteroids.model;

import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of minor planets, involving a position, velocity and radius.
 */
public abstract class MinorPlanet extends Entity {

	/**
	 * Initialize this new minor planet with given position, velocity and radius.
	 */
	public MinorPlanet(double xpos, double ypos, double xvel, double yvel, double radius) 
		throws IllegalPositionException, IllegalRadiusException {
		super(xpos, ypos, xvel, yvel, radius);
	}
	
	/**
	 * Return whether the given position is a valid position for this minor planet.
	 * 
	 * @return	True if and only if 
	 * 			nor xpos, nor ypos is positive of negative infinity or not a number
	 * 			and 
	 * 			this minor planet is not associated with a world
	 * 				or this minor planet lies fully within the boundaries of its world.
	 * 					and no entity in its world overlaps with this minor planet.
	 * 			| result = (! xpos == Double.POSITIVE_INFINITY)&&(! xpos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(xpos))
	 *			| 	&& (! ypos == Double.POSITIVE_INFINITY)&&(! ypos == Double.NEGATIVE_INFINITY)&&(! Double.isNaN(ypos))
	 *			|	&& (
	 *			|	(this.getWorld() == null)
	 *			| 	|| (
	 *			| 	(xpos>0.99*getRadius())&&(xpos<1.01*(getWorld().getWidth()-getRadius()))
	 *			| 	&& (ypos>0.99*getRadius())&&(ypos<1.01*(getWorld().getHeight()-getRadius()))
	 *			| 	&& (for each entity in getWorld().getEntities():
	 *			| 		(entity == this)
	 *			| 		|| ((Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
	 *			|					Math.pow(ypos-entity.getYPosition(),2)))>=
	 *			|					0.99*(entity.getRadius()+getRadius())))
	 *			|	)
	 *			|	)
	 */
	@Override @Raw
	public boolean isValidPosition(double xpos, double ypos) {
		if ((Double.isNaN(xpos)) || (Double.isNaN(ypos)))
			return false;
		if (this.getWorld() != null) {
			if ((xpos>0.99*getRadius())&&(xpos<1.01*(getWorld().getWidth()-getRadius()))&&
					(ypos>0.99*getRadius())&&(ypos<1.01*(getWorld().getHeight()-getRadius()))) {
				for (Entity entity: getWorld().getEntities().keySet()) {
					if ((entity!=this)&&
							(Math.sqrt(Math.pow(xpos-entity.getXPosition(),2)+
									Math.pow(ypos-entity.getYPosition(),2)))<
									0.99*(entity.getRadius()+getRadius())) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return true;
	}

	/**
	 * Return whether the given radius is valid for this minor planet.
	 * 
	 * @return	True if and only if radius exceeds MIN_RADIUS.
	 * 			| result == (radius >= MIN_RADIUS)
	 */
	@Override
	public boolean isValidRadius(double radius) {
		return (radius >= MIN_RADIUS);
	}

	/**
	 * Return the mass of this minor planet.
	 * 
	 * @return 	| result == (4.0/3.0)*Math.PI*Math.pow(getRadius(),3)*getMassDensity()
	 */
	@Override
	public double getMass() {
		return (4.0/3.0)*Math.PI*Math.pow(getRadius(),3)*getMassDensity();
	}
	
	/**
	 * A method to resolve the collision of this minor planet with a given ship.
	 * 
	 * @param 	ship
	 * 			The given ship.
	 */
	abstract public void collideShip(Ship ship);

	/**
	 * Terminate this minor planet. 
	 */
	@Override
	public void terminate() {
		super.terminate();
	}
	
	/**
	 * A variable registering the minimum value of a minor planet.
	 */
	private final static double MIN_RADIUS = 5;

}
