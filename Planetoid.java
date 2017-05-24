package asteroids.model;

import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import java.util.Random;

/**
 * A class of planetoids, involving a position, velocity and radius.
 */
public class Planetoid extends MinorPlanet {
	
	/**
	 * Initialize this planetoid with given position, velocity, radius and traveled distance.
	 * 
	 * @param 	distance
	 * 			The given traveled distance.
	 * @post	The traveled distance of this planetoid is set to the given value.
	 * 			| new.getTotalDistance() == distance
	 */
	public Planetoid(double xpos, double ypos, double xvel, double yvel, double radius, double distance) 
		throws IllegalPositionException, IllegalRadiusException {
		super(xpos,ypos,xvel,yvel,radius);
		setMassDensity(DENSITY);
		setTotalDistance(distance);
		setRadius(radius);
	}

	/**
	 * Move this planetoid for a given amount of time.
	 * 
	 * @effect	The total traveled distance of this planetoid is increased and its
	 * 			radius is adjusted accordingly.
	 * 			| traveledDistance = dt*Math.sqrt(Math.pow(getXVelocity(), 2)+(Math.pow(getYVelocity(), 2)));
	 *			| setTotalDistance(getTotalDistance()+traveledDistance);
	 *			| setRadius(getRadius());
	 */
	@Override
	public void move(double dt) {
		super.move(dt);
		double traveledDistance = dt*Math.sqrt(Math.pow(getXVelocity(), 2)+(Math.pow(getYVelocity(), 2)));
		setTotalDistance(getTotalDistance()+traveledDistance);
		setRadius(getRadius());
	}
	
	/**
	 * Set the radius of this planetoid to a given value.
	 * 
	 * @post	The radius of this planetoid is adjusted, taken into account the
	 * 			total traveled distance of this planetoid.
	 * 			| newRadius = radius-0.000001*old.getTotalDistance()
	 * 			| if (! old.isValidRadius(newRadius)
	 * 			| 	new.isTerminated()
	 * 			| else
	 * 			| 	new.getRadius() == newRadius
	 */
	@Override
	public void setRadius(double radius) {
		double newRadius = radius-0.000001*getTotalDistance();
		try {
			super.setRadius(newRadius);
			}
		catch (IllegalRadiusException exc) {
			terminate();
		}
	}
	
	/**
	 * Return whether the given density is valid for this planetoid.
	 * 
	 * @result	True if and only if density equals DENSITY.
	 * 			| result == (density == DENSITY)
	 */
	@Override
	public boolean isValidDensity(double density) {
		return (density == DENSITY);
	}

	/**
	 * Return the total traveled distance of this planetoid.
	 */
	public double getTotalDistance() {
		return this.totalDistance;
	}
	
	/**
	 * Set the total traveled distance of this planetoid to a given value.
	 * 
	 * @param 	distance
	 * 			The given traveled distance.
	 * @post	If the given distance exceeds zero, the new distance of this planetoid
	 * 			is set to the given distance.	
	 * 			| new.getTotalDistance() == distance
	 */
	public void setTotalDistance(double distance) {
		if (distance >= 0) {
			this.totalDistance = distance;
		}
	}
	
	/**
	 * Resolve a collision between this planetoid and a given ship.
	 * 
	 * @post	This method generates a new, random position for this ship in its world.
	 * 			If that new position is valid for this ship, the new position of this ship
	 * 			is that random position. Otherwise, this ship is terminated.
	 * 			| double randomx = ship.getRadius()*0.99 + (new Random().nextDouble() * (ship.getWorld().getWidth() - 2*ship.getRadius()*0.99));
	 *			| double randomy = ship.getRadius()*0.99 + (new Random().nextDouble() * (ship.getWorld().getHeight() - 2*ship.getRadius()*0.99));
	 *			| if (ship.isValidPosition(randomx, randomy))
	 *			| 	(new ship).getXPosition() == randomx
	 *			| 	(new ship).getYPosition() == randomy
	 *			| else
	 *			| 	(new ship).isTerminated()
	 */
	@Override
	public void collideShip(Ship ship) {
		double randomx = ship.getRadius()*0.99 + (new Random().nextDouble() * (ship.getWorld().getWidth() - 2*ship.getRadius()*0.99));
		double randomy = ship.getRadius()*0.99 + (new Random().nextDouble() * (ship.getWorld().getHeight() - 2*ship.getRadius()*0.99));
		if (ship.isValidPosition(randomx, randomy))
			ship.setPosition(randomx, randomy);
		else
			ship.terminate();
	}
	
	
	/**
	 * Terminate this planetoid.
	 * 
	 * @post	This planetoid is terminated.
	 * 			| new.isTerminated()
	 * @post	If the radius of this planetoid is larger than 30, it is split into 2
	 * 			new asteroids, positioned at a random angle beside this planetoid.
	 * 			| @see implementation
	 * @post	If the radius of this planetoid is smaller than 30, this planetoid is
	 * 			terminated.
	 * 			| if (old.getRadius()<30)
	 * 			| 	! old.getWorld().contains(new)
	 * 			| 	new.isTerminated()
	 */
	@Override
	public void terminate() {
		if (this.getRadius() >= 30) {
			double randomAngle = new Random().nextDouble()*2*Math.PI;
			double newvel = 1.5*Math.sqrt(Math.pow(this.getXVelocity(),2) + Math.pow(this.getYVelocity(),2));
			Asteroid asteroid1 = new Asteroid(this.getXPosition()+Math.cos(randomAngle)*this.getRadius()/2,this.getYPosition()+Math.sin(randomAngle)*this.getRadius()/2,
									Math.cos(randomAngle)*newvel, Math.sin(randomAngle)*newvel, this.getRadius()/2);
			Asteroid asteroid2 = new Asteroid(this.getXPosition()-Math.cos(randomAngle)*this.getRadius()/2,this.getYPosition()+-Math.sin(randomAngle)*this.getRadius()/2,
									-Math.cos(randomAngle)*newvel, -Math.sin(randomAngle)*newvel, this.getRadius()/2);
			super.terminate();
			World world = this.getWorld();
			this.getWorld().removeEntity(this);
			this.setWorld(null);
			world.addEntity(asteroid1);
			world.addEntity(asteroid2);
			asteroid1.setWorld(world);
			asteroid2.setWorld(world);
		}
		else {
			super.terminate();
			if (this.getWorld() != null)
				this.getWorld().removeEntity(this);
			
		}
		
	}
	
	/**
	 * A variable registering the minimum mass density of this asteroid.
	 */
	private static final double DENSITY = 0.917*Math.pow(10, 12);
	
	/**
	 * A variable registering the total distance traveled by this planetoid.
	 */
	private double totalDistance=0;

}
