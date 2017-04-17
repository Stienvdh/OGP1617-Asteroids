package asteroids.tests;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.IllegalDurationException;
import asteroids.model.IllegalEntityException;
import asteroids.model.IllegalWorldException;
import asteroids.model.Ship;
import asteroids.model.World;

public class WorldTest {
	
	World world;
	
	@Before
	public void SetUp() {
		world = new World(1000,1000);
	}
	
	@Test
	public void testAddEntity_LegalCase() {
		Ship ship = new Ship(100,100,10,10,10,0,100);
		world.addEntity(ship);
		assert ship.getWorld()==world;
		assert world.getEntities().containsKey(ship);
		assert world.getShips().contains(ship);
		assert world.getEntities().get(ship)[0]==100;
		assert world.getEntities().get(ship)[1]==100;
	}
	
	@Test
	public void testAddEntity_CaseIllegalPosition() {
		Ship ship = new Ship(100,100,10,10,10,0,100);
		world.addEntity(ship);
		Ship ship2 = new Ship(100,100,10,10,10,0,100);
		world.addEntity(ship2);
		assert ship2.isTerminated();
	}
	
	@Test
	public void testRemoveEntity_LegalCaseShip() {
		Ship ship = new Ship(100,100,10,10,10,0,100);
		world.addEntity(ship);
		world.removeEntity(ship);
		assert ship.getWorld()==null;
		assert ! world.getEntities().containsKey(ship);
		assert ! world.getShips().contains(ship);
	}
	
	@Test
	public void testRemoveEntity_LegalCaseBullet() {
		Bullet bullet = new Bullet(100,100,10,10,5);
		Ship ship = new Ship(100,100,10,10,10,0,100);
		ship.loadBullet(bullet);
		ship.fireBullet();
		world.removeEntity(bullet);
		assert bullet.getWorld()==null;
		assert bullet.getSource()==null;
		assert ! world.getEntities().containsKey(bullet);
		assert ! world.getBullets().contains(bullet);
	}
	
	@Test
	public void testGetEntityAt_LegalCaseEntityContained() {
		Ship ship = new Ship(100,100,10,10,10,0,100);
		world.addEntity(ship);
		assert world.getEntityAt(100, 100)==ship;
	}
	
	@Test
	public void testGetEntityAt_LegalCaseEntityAbsent() {
		assert world.getEntityAt(100, 100)==null;
	}
	
	@Test
	public void testTerminate_LegalCase() {
		Ship ship = new Ship(100,100,10,10,10,0,100);
		world.addEntity(ship);
		world.terminate();
		assert world.isTerminated();
		assert world.getEntities().size()==0;
		assert world.getShips().size()==0;
		assert ship.getWorld()==null;
	}
	
	@Test
	public void testTimeToFirstCollision_LegalCaseBoundary() {
		Ship ship = new Ship(100,100,-10,0,10,0,100);
		world.addEntity(ship);
		assert world.getTimeFirstCollision()==9.01;
	}
	
	@Test
	public void testTimeToFirstCollision_LegalCaseCollision() {
		Ship ship = new Ship(100,100,10,0,10,0,100);
		world.addEntity(ship);
		Ship ship2 = new Ship(200,100,-10,0,10,0,100);
		world.addEntity(ship2);
		assert world.getTimeFirstCollision()==4;
	}
	
	@Test
	public void testGetFirstCollisionPosition_LegalCaseBoundary() {
		Ship ship = new Ship(100,100,-10,0,10,0,100);
		world.addEntity(ship);
		assert world.getFirstCollisionPosition()[0]==0;
		assert world.getFirstCollisionPosition()[1]==100;
	}
	
	@Test
	public void testGetFirstCollisionPosition_LegalCaseCollision() {
		Ship ship = new Ship(100,100,10,0,10,0,100);
		world.addEntity(ship);
		Ship ship2 = new Ship(200,100,-10,0,10,0,100);
		world.addEntity(ship2);
		assert world.getFirstCollisionPosition()[0]==150;
		assert world.getFirstCollisionPosition()[1]==100;
	}
	
	@Test
	public void testEvolve_CaseNoCollisionsAndThrust() throws IllegalEntityException, IllegalDurationException, IllegalWorldException{
		Ship ship = new Ship(100,100,10,0,10,0,100);
		world.addEntity(ship);
		Ship ship2 = new Ship(200,100,-10,0,10,0,100);
		world.addEntity(ship2);
		world.evolve(1);
		assert ship.getXPosition()==110;
		assert ship2.getXPosition()==190;
		ship.thrustOn();
		world.evolve(1);
		assert ship.getXPosition()==120;
		assert ship2.getXPosition()==180;
		assert ship.getXVelocity()==10+ship.getAcceleration();
	}
	
	@Test
	public void testEvolve_CaseCollision() throws IllegalEntityException, IllegalDurationException, IllegalWorldException {
		Ship ship = new Ship(100,100,-10,0,10,0,100);
		world.addEntity(ship);
		world.evolve(8);
		world.evolve(2);
		assert ship.getXVelocity()==10;
	}
	
	@Test (expected = IllegalDurationException.class)
	public void testEvolve_IllegalCaseDuration() throws IllegalEntityException, IllegalDurationException, IllegalWorldException {
		Ship ship = new Ship(100,100,-10,0,10,0,100);
		world.addEntity(ship);
		world.evolve(-10);
	}
	
	
}
