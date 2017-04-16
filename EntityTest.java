package asteroids.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.IllegalEntityException;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class EntityTest {
	
	private static final double EPSILON = 0.0001;
	
	IFacade facade = new Facade();
	Ship ship;
	World world;

	@Before
	public void setUp() throws ModelException {
		facade = new Facade();
		world = new World(1000,1000);
		ship = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		world.addEntity(ship);
		Ship ship2 = facade.createShip(200,200,-10,20,30,Math.PI, 100);
		world.addEntity(ship2);
	}
	
	@Test
	public void testGetDistanceBetween_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 20, 30, Math.PI, 100);
		assertEquals(Math.sqrt(Math.pow(100, 2)+Math.pow(200, 2))-30-30, ship1.getDistanceBetween(ship2), EPSILON);
	}
	
	@Test
	public void testGetDistanceBetween_CaseSameShip() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		assertEquals(0,ship1.getDistanceBetween(ship1),EPSILON);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetDistanceBetween_IllegalCaseNullShip() throws ModelException {
		Ship ship2 = null;
		ship.getDistanceBetween(ship2);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetDistanceBetween_IllegalCaseTerminated() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 20, 30, Math.PI, 100);
		ship1.terminate();
		ship1.getDistanceBetween(ship2);
	}
	
	@Test
	public void testOverlap_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		assert ship1.overlap(ship2);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testOverlap_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		ship2.terminate();
		ship1.overlap(ship2);
	}
	
	@Test
	public void testGetTimeToCollision_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 0, 20, 0, 100);
		ship1.setWorld(world);
		ship2.setWorld(world);
		assertEquals(3, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetTimeToCollision_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI, 100);
		Ship ship2 = null;
		world.addEntity(ship1);
		ship1.getTimeToCollision(ship2);
		world.removeEntity(ship1);
	}
	
	@Test
	public void testGetTimeToCollision_CaseNoCollision() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 20, 20, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 50, 20, 0, 100);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testGetCollisionPosition_CaseCollision() throws ModelException {
		Ship ship1 = facade.createShip(200, 100, -10, 0, 20, Math.PI, 100);
		Ship ship2 = facade.createShip(100, 100, 10, 0, 20, 0, 100);
		world.addEntity(ship1);
		world.addEntity(ship2);
		assertEquals(150, ship1.getCollisionPosition(ship2)[0], EPSILON);
		assertEquals(100, ship1.getCollisionPosition(ship2)[1], EPSILON);
		world.removeEntity(ship1);
		world.removeEntity(ship2);
	}
	
	@Test
	public void testGetCollisionPosition_CaseNoCollision() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 20, 20, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 50, 20, 0, 100);
		assertEquals(null, ship1.getCollisionPosition(ship2));
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testCollisionPosition_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI, 100);
		Ship ship2 = null;
		ship1.getCollisionPosition(ship2);
	}
	
}
