package asteroids.model;

public abstract class Entity {
	
	public abstract boolean hasPosition();
	public abstract double getXPosition();
	public abstract double getYPosition();
	public abstract void setWorld(World world);
}
