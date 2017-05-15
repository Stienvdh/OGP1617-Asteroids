package asteroids.model;

import java.util.HashSet;
import java.util.Set;

public class EntitySet<T extends Entity> {
	
	@SuppressWarnings("unchecked")
	public EntitySet(World world, Class<T> type) {
		for (Entity entity: world.getAllEntities()) {
			if (type.isAssignableFrom(entity.getClass())) {
				entitySet.add((T) entity);
			}
		}
	}
	
	public Set<T> getSet() {
		return this.entitySet;
	}
	
	private Set<T> entitySet = new HashSet<T>();

}
