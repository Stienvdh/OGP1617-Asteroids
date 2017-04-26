package asteroids.model;

import asteroids.model.exceptions.IllegalRadiusException;

public class Planetoid extends MinorPlanet {
	
	public Planetoid(double xpos, double ypos, double xvel, double yvel, double radius) {
		super(xpos,ypos,xvel,yvel,radius);
		setMassDensity(DENSITY);
	}
	
	@Override
	public void setMassDensity(double massDensity) {
		this.massDensity=DENSITY;
	}

	@Override
	public void move(double dt) {
		super.move(dt);
		setRadius(0.000001*getRadius());
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
	
	/**
	 * A variable registering the minimum mass density of this asteroid.
	 */
	protected static double DENSITY = 0.917*Math.pow(10, 12);

}
