package asteroids.model;

import asteroids.model.exceptions.IllegalRadiusException;
import java.util.Random;

public class Planetoid extends MinorPlanet {
	
	public Planetoid(double xpos, double ypos, double xvel, double yvel, double radius, double distance) {
		super(xpos,ypos,xvel,yvel,radius);
		setMassDensity(DENSITY);
		setTotalDistance(distance);
	}
	
	@Override
	public void setMassDensity(double massDensity) {
		this.massDensity=DENSITY;
	}

	@Override
	public void move(double dt) {
		super.move(dt);
		setTotalDistance(getTotalDistance()+getXVelocity()*dt);
		setRadius(getRadius()-0.000001*getXVelocity()*dt);
	}
	
	@Override
	public void setRadius(double radius) {
		try {
			super.setRadius(radius);
			}
		catch (IllegalRadiusException exc) {
			terminate();
		}
	}
	
	public double getTotalDistance() {
		return this.totalDistance;
	}
	
	public void setTotalDistance(double distance) {
		if (distance >= 0) {
			this.totalDistance = distance;
		}
	}
	
	@Override
	public void collideShip(Ship ship) {
		double randomx = ship.getRadius()*0.99 + (new Random().nextDouble() * (ship.getWorld().getWidth() - 2*ship.getRadius()*0.99));
		double randomy = ship.getRadius()*0.99 + (new Random().nextDouble() * (ship.getWorld().getHeight() - 2*ship.getRadius()*0.99));
		if (ship.isValidPosition(randomx, randomy))
			ship.setPosition(randomx, randomy);
		else
			ship.terminate();
	}
	
	@Override
	public void terminate() {
		if (this.getRadius() >= 30) {
			double randomAngle = new Random().nextDouble()*2*Math.PI;
			double newvel = 1.5*Math.sqrt(Math.pow(this.getXVelocity(),2) + Math.pow(this.getYVelocity(),2));
			Asteroid asteroid1 = new Asteroid(Math.cos(randomAngle)*this.getRadius()/2,Math.sin(randomAngle)*this.getRadius()/2,
									Math.cos(randomAngle)*newvel, Math.sin(randomAngle)*newvel, this.getRadius()/2);
			Asteroid asteroid2 = new Asteroid(-Math.cos(randomAngle)*this.getRadius()/2,-Math.sin(randomAngle)*this.getRadius()/2,
									-Math.cos(randomAngle)*newvel, -Math.sin(randomAngle)*newvel, this.getRadius()/2);
			this.getWorld().addEntity(asteroid1);
			this.getWorld().addEntity(asteroid2);
			asteroid1.setWorld(this.getWorld());
			asteroid2.setWorld(this.getWorld());
		}
		this.isTerminated=true;
		this.getWorld().removeEntity(this);
		this.setWorld(null);
	}
	
	/**
	 * A variable registering the minimum mass density of this asteroid.
	 */
	private static double DENSITY = 0.917*Math.pow(10, 12);
	
	/**
	 * A variable registering the total distance traveled by this planetoid.
	 */
	private double totalDistance=0;

}
