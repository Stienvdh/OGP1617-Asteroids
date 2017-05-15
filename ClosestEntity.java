package asteroids.model;

public class ClosestEntity<T extends Entity> {
	
	public ClosestEntity(Ship ship, Class<T> type) {
		double minDistance = Double.POSITIVE_INFINITY;
		T closestEntity = null;
		if (ship.getWorld()!=null) {
			for (T entity:
				(new EntitySet<T>(ship.getWorld(), type).getSet())) {
				if ((ship.getDistanceBetween((Entity) entity)<minDistance)&&
					(entity!=ship)){
					minDistance = ship.getDistanceBetween((Entity) entity);
					closestEntity = entity;
				}
			}
			this.closestEntity = closestEntity;
		}
	}
	
	public T getClosestEntity() {
		return this.closestEntity;
	}
	
	private T closestEntity = null;

}
