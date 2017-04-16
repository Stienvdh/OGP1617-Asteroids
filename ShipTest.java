package asteroids.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;
import asteroids.model.Bullet;
import asteroids.model.IllegalBulletException;
import asteroids.model.IllegalDurationException;

public class ShipTest {

	private static final double EPSILON = 0.0001;

	IFacade facade;
	World world;
	Ship ship;
	Ship ship2;

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
	public void testIsValidPosition() throws ModelException {
		assert ! ship.isValidPosition(Double.POSITIVE_INFINITY,50);
		assert ! ship.isValidPosition(20,Double.NEGATIVE_INFINITY);
		assert ! ship.isValidPosition(20,Double.NaN);
		assert ! ship.isValidPosition(200, 200);
		assert ! ship.isValidPosition(5,100);
		assert ship.isValidPosition(100, 200);
	}
	
	@Test
	public void testTerminate() throws ModelException {
		ship.terminate();
		assert ship.isTerminated();
		assert ship.getWorld()==null;
		assert ship.getBullets().size()==0;
		assert ship.getBulletsFired().size()==0;
	}
	
	@Test
	public void testMove_LegalCase() throws ModelException {
		ship.move(1);
		double xpos = ship.getXPosition();
		double ypos = ship.getYPosition();
		assertEquals(110, xpos, EPSILON);
		assertEquals(220, ypos, EPSILON);
	}
	
	@Test(expected = IllegalDurationException.class)
	public void testMove_IllegalCaseNegativeDuration() throws ModelException {
		ship.move(-1);
	}
	
	@Test
	public void testTurn() throws ModelException {
		ship.turn(4.5*Math.PI);
		assertEquals(1.5*Math.PI, ship.getOrientation(), EPSILON);
		ship.turn(-7.5*Math.PI);
		assertEquals(0, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testLoadBullet_LegalCase() {
		Bullet bullet = new Bullet(100,200,10,10,5);
		world.addEntity(bullet);
		ship.loadBullet(bullet);
		assert bullet.getShip()==ship;
		assert bullet.getWorld()==null;
		assert ship.getBullets().contains(bullet);
		assert bullet.getXPosition()==ship.getXPosition();
		assert bullet.getXVelocity()==ship.getXVelocity();
		world.removeEntity(bullet);
	}
	
	@Test (expected = IllegalBulletException.class)
	public void testLoadBullet_IllegalCaseDifferentWorlds() throws ModelException {
		Bullet bullet = facade.createBullet(100,200,10,10,5);
		World world2 = new World(1000,1000);
		facade.addBulletToWorld(world2, bullet);
		ship.loadBullet(bullet);
	}
	
	@Test 
	public void testFireBullet_LegalCase() {
		Bullet bullet = new Bullet(100,100,10,10,5);
		ship.loadBullet(bullet);
		ship.fireBullet();
		assert ! ship.getBullets().contains(bullet);
		assert bullet.getSource()==ship;
		assert world.getBullets().contains(bullet);
		assert ship.getNbBullets()==0;
		assert bullet.getSpeed()==INITIAL_SPEED;
	}
	
	@Test
	public void testFireBullet_CaseNoWorld() {
		Ship ship2 = new Ship(100,200,10,10,10,Math.PI,100);
		ship2.loadBullet(new Bullet(100,100,10,10,5));
		ship2.loadBullet(new Bullet(100,100,10,10,5));
		ship2.fireBullet();
		assert ship2.getNbBullets()==2;
	}
	
	@Test
	public void testFireBullet_CaseOutOfBounds() {
		Ship ship2 = new Ship(990,100,10,10,10,0,100);
		world.addEntity(ship2);
		Bullet bullet = new Bullet(100,100,10,10,10);
		ship2.loadBullet(bullet);
		ship2.fireBullet();
		assert bullet.isTerminated();
		world.removeEntity(ship2);
	}
	
	@Test
	public void testFireBullet_CaseImmediateCollision() {
		Ship ship2 = new Ship(100,100,10,10,10,0,100);
		Ship ship3 = new Ship(120,100,10,10,10,0,100);
		world.addEntity(ship2);
		world.addEntity(ship3);
		Bullet bullet = new Bullet(100,100,10,10,5);
		ship2.loadBullet(bullet);
		ship2.fireBullet();
		assert bullet.isTerminated();
		assert ship3.isTerminated();
	}
	
	private static final double INITIAL_SPEED = 250;
}

