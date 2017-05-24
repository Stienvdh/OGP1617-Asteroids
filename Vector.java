package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A class of vectors, involving an x- and a y-component.
 */
@Value
public class Vector {
	
	public Vector(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getNorm() {
		return Math.sqrt(Math.pow(getX(),2)+Math.pow(getY(),2));
	}
	
	public boolean equals(Vector other) {
		return ((this.getX()==other.getX())&&(this.getY()==other.getY()));
	}
	
	private double x;
	
	private double y;

}
