package asteroids.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.IllegalEntityException;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.model.Bullet;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class EntityTest {
	
	private static final double EPSILON = 0.0001;
	
	IFacade facade = new Facade();
	Ship ship;
	Ship ship2;
	World world;
	Bullet bullet;
	Bullet bullet2;

	@Before
	public void setUp() throws ModelException {
		facade = new Facade();
		world = new World(1000,1000);
		ship = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		world.addEntity(ship);
		ship2 = facade.createShip(200,200,-10,20,30,Math.PI, 100);
		world.addEntity(ship2);
		bullet = facade.createBullet(300, 300, 50, 40, 5);
		world.addEntity(bullet);
		bullet2 = facade.createBullet(800, 200, 30, -10, 5);
		world.addEntity(bullet2);
	}
	
	@Test
	public void testGetDistanceBetween_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 20, 30, Math.PI, 100);
		assertEquals(Math.sqrt(Math.pow(100, 2)+Math.pow(200, 2))-30-30, ship1.getDistanceBetween(ship2), EPSILON);
		assertEquals(Math.sqrt(Math.pow(800-300, 2)+Math.pow(200-300, 2))-5-5, bullet.getDistanceBetween(bullet2), EPSILON);
	}
	
	@Test
	public void testGetDistanceBetween_CaseSameEntity() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		assertEquals(0,ship1.getDistanceBetween(ship1),EPSILON);
		assertEquals(0,bullet.getDistanceBetween(bullet),EPSILON);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetDistanceBetween_IllegalCaseNullShip() throws ModelException {
		Ship ship2 = null;
		ship.getDistanceBetween(ship2);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetDistanceBetween_IllegalCaseNullBullet() throws ModelException {
		Bullet bullet = null;
		bullet2.getDistanceBetween(bullet);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetDistanceBetween_IllegalCaseTerminated() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 20, 30, Math.PI, 100);
		ship1.terminate();
		ship1.getDistanceBetween(ship2);
	}
	
	@Test
	public void testOverlapShip_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		assert ship1.overlap(ship2);
	}
	
	@Test
	public void testOverlapBullet_LegalCase() throws ModelException {
		bullet2 = facade.createBullet(303, 303, 30, -10, 5);
		assert bullet.overlap(bullet2);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testOverlapShip_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		Ship ship2 = facade.createShip(100, 200, 10, 20, 30, Math.PI, 100);
		ship2.terminate();
		ship1.overlap(ship2);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testOverlapBullet_IllegalCase() throws ModelException {
		bullet.terminate();
		bullet.overlap(bullet2);
	}
	
	@Test
	public void testGetTimeToCollision_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 0, 20, 0, 100);
		ship1.setWorld(world);
		ship2.setWorld(world);
		Bullet bullet = facade.createBullet(100, 0, 20, 0, 5);
		Bullet bullet2 = facade.createBullet(300, 0, -20, 0, 5);
		bullet.setWorld(world);
		bullet2.setWorld(world);
		assertEquals(3, ship1.getTimeToCollision(ship2), EPSILON);
		assertEquals(4.75, bullet.getTimeToCollision(bullet2), EPSILON);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetTimeToCollisionShip_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI, 100);
		Ship ship2 = null;
		world.addEntity(ship1);
		ship1.getTimeToCollision(ship2);
		world.removeEntity(ship1);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetTimeToCollisionBullet_IllegalCase() throws ModelException {
		Bullet bullet = facade.createBullet(100, 0, -10, 0, 20);
		Bullet bullet2 = null;
		world.addEntity(bullet);
		bullet.getTimeToCollision(bullet2);
	}
	
	@Test
	public void testGetTimeToCollision_CaseNoCollision() throws ModelException {
		assertEquals(Double.POSITIVE_INFINITY, bullet.getTimeToCollision(bullet2), EPSILON);
	}
	
	@Test
	public void testGetCollisionPosition_CaseCollision() throws ModelException {
		Ship ship1 = facade.createShip(200, 100, -10, 0, 20, Math.PI, 100);
		Ship ship2 = facade.createShip(100, 100, 10, 0, 20, 0, 100);
		Bullet bullet = facade.createBullet(100, 0, 20, 0, 5);
		Bullet bullet2 = facade.createBullet(300, 0, -20, 0, 5);
		bullet.setWorld(world);
		bullet2.setWorld(world);
		world.addEntity(ship1);
		world.addEntity(ship2);
		assertEquals(150, ship1.getCollisionPosition(ship2)[0], EPSILON);
		assertEquals(100, ship1.getCollisionPosition(ship2)[1], EPSILON);
		assertEquals(200, bullet.getCollisionPosition(bullet2)[0], EPSILON);
		assertEquals(0, bullet.getCollisionPosition(bullet2)[1], EPSILON);
		world.removeEntity(ship1);
		world.removeEntity(ship2);
	}
	
	@Test
	public void testGetCollisionPosition_CaseNoCollision() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 20, 20, Math.PI, 100);
		Ship ship2 = facade.createShip(0, 0, 10, 50, 20, 0, 100);
		assertEquals(null, ship1.getCollisionPosition(ship2));
		assertEquals(null, bullet.getCollisionPosition(bullet2));
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetCollisionPositionShip_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI, 100);
		Ship ship2 = null;
		ship1.getCollisionPosition(ship2);
	}
	
	@Test(expected = IllegalEntityException.class)
	public void testGetCollisionPositionBullet_IllegalCase() throws ModelException {
		Bullet bullet2 = null;
		bullet.getCollisionPosition(bullet2);
	}
	
	@Test
	public void testgetBoundaryPosition() throws ModelException {
		ship.setWorld(null);
		bullet.setWorld(null);
		assertEquals(Double.POSITIVE_INFINITY, bullet.getTimeToBoundary(), EPSILON);
		assertEquals(null, bullet.getBoundaryPosition());
		assertEquals(null, ship.getBoundaryPosition());
		Bullet bullet2 = facade.createBullet(690, 300, 50, 40, 10);
		bullet2.setWorld(world);
		assertEquals(1000, bullet2.getBoundaryPosition()[0],EPSILON);
		assertEquals(540, bullet2.getBoundaryPosition()[1],EPSILON);
		assertEquals(0, ship2.getBoundaryPosition()[0],EPSILON);
		assertEquals(540, ship2.getBoundaryPosition()[1],EPSILON);
	}
	
	@Test
	public void testgetTimeToBoundary() throws ModelException {
		assertEquals(17, ship2.getTimeToBoundary(),EPSILON);
		Bullet bullet2 = facade.createBullet(690, 300, 50, 40, 10);
		assertEquals(Double.POSITIVE_INFINITY, bullet2.getTimeToBoundary(),EPSILON);
		bullet2.setWorld(world);
		assertEquals(6, bullet2.getTimeToBoundary(),EPSILON);
		
	}
	
	@Test
	public void testCollide1() throws ModelException {
		ship.collide(bullet);
		assert ship.isTerminated();
		assert bullet.isTerminated();
	}
	
	@Test
	public void testCollide2() throws ModelException {
		bullet2.collide(bullet);
		assert bullet2.isTerminated();
		assert bullet.isTerminated();
	}
	
	@Test
	public void testCollide3() throws ModelException {
		ship.collide(ship2);
		assert ! ship.isTerminated();
		assert ! ship2.isTerminated();
	}
	
	@Test
	public void testCollide4() throws ModelException {
		bullet.setWorld(null);
		bullet.setSource(ship);
		ship.collide(bullet);
		assert bullet.getShip() == ship;
	}
	
	@Test
	public void testCollide5() throws ModelException {
		ship.loadBullet(bullet);
		ship.collide(bullet2);
		assert bullet.isTerminated();
	}
	
	@Test
	public void testCollideBoundary() throws ModelException {
		Ship ship = facade.createShip(100,400,-10,20,30,Math.PI,50);
		ship.collideBoundary();
		assertEquals(-10, ship.getXVelocity(), EPSILON);
		ship.setWorld(world);
		ship.collideBoundary();
		assertEquals(10, ship.getXVelocity(), EPSILON);
	}
	
	@Test
	public void testCollideBoundaryBullet() throws ModelException {
		bullet.collideBoundary();
		assertEquals(1, bullet.getBounces(),EPSILON);
		bullet.collideBoundary();
		assertEquals(2, bullet.getBounces(),EPSILON);
		bullet.collideBoundary();
		assert bullet.isTerminated();
	}
	
}
