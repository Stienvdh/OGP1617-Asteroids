package asteroids.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;
import asteroids.model.IllegalPositionException;
import asteroids.model.IllegalRadiusException;
import asteroids.model.IllegalDurationException;
import asteroids.model.IllegalShipException;

public class ShipTest {

	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public void testGetXPosition() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		double xpos = ship.getXPosition();
		assertEquals(100, xpos, EPSILON);
	}
	
	@Test
	public void testGetYPosition() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		double ypos = ship.getYPosition();
		assertEquals(200, ypos, EPSILON);
	}
	
	@Test
	public void testSetPosition_LegalCase() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.setPosition(10, 20);
		assertEquals(10, ship.getXPosition(), EPSILON);
		assertEquals(20, ship.getYPosition(), EPSILON);
	}
	
	@Test(expected = IllegalPositionException.class)
	public void testSetPosition_IllegalCaseNaN() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.setPosition(Double.NaN, 50);
	}
	
	@Test(expected = IllegalPositionException.class)
	public void testSetPosition_IllegalCaseInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.setPosition(Double.POSITIVE_INFINITY, 50);
	}

	@Test
	public void testIsValidPosition() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assert ! ship.isValidPosition(Double.POSITIVE_INFINITY,50);
		assert ! ship.isValidPosition(20,Double.NEGATIVE_INFINITY);
		assert ! ship.isValidPosition(20,Double.NaN);
		assert ship.isValidPosition(10, 20);
	}
	
	@Test
	public void testGetXVelocity() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assertEquals(10, ship.getXVelocity(), EPSILON);
	}
	
	@Test
	public void testGetYVelocity() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assertEquals(20, ship.getYVelocity(), EPSILON);
	}
	
	@Test
	public void testSetVelocity_NormalCase() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.setVelocity(100,500);
		assertEquals(100, ship.getXVelocity(), EPSILON);
		assertEquals(500, ship.getYVelocity(), EPSILON);
	}
	
	@Test
	public void testSetVelocity_CaseMaxSpeed() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.setVelocity(MAX_SPEED,500);
		double xvel = ship.getXVelocity();
		double yvel = ship.getYVelocity();
		assertEquals(MAX_SPEED,Math.sqrt(Math.pow(xvel, 2)+Math.pow(yvel, 2)), EPSILON);
	}
	
	@Test
	public void testGetSpeed() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assertEquals(Math.sqrt(Math.pow(10, 2)+Math.pow(20, 2)),ship.getSpeed(), EPSILON);
	}
	
	@Test
	public void testGetMaxSpeed() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assertEquals(MAX_SPEED,ship.getMaxSpeed(), EPSILON);
	}
	
	@Test
	public void testSetMaxSpeed_NormalCase() throws ModelException {
		Ship ship = facade.createShip(100, 200, 1000, 1000, 30, Math.PI);
		ship.setMaxSpeed(1000);
		assertEquals(1000,ship.getMaxSpeed(), EPSILON);
		assertEquals(1000,Math.sqrt(Math.pow(ship.getXVelocity(), 2)+Math.pow(ship.getYVelocity(), 2)), EPSILON);
	}
	
	@Test
	public void testSetMaxSpeed_CaseMaxSpeed() throws ModelException {
		Ship ship = facade.createShip(100, 200, 1000, 1000, 30, Math.PI);
		ship.setMaxSpeed(MAX_SPEED+1);
		assertEquals(MAX_SPEED,ship.getMaxSpeed(), EPSILON);
	}
	
	@Test
	public void testGetRadius() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assertEquals(30,ship.getRadius(), EPSILON);
	}
	
	@Test
	public void testSetRadius_LegalCase() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 20, Math.PI);
		assertEquals(20,ship.getRadius(), EPSILON);
	}
	
	@Test(expected = IllegalRadiusException.class)
	public void testSetRadius_IllegalCase() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, MIN_RADIUS+1, Math.PI);
		ship.setRadius(MIN_RADIUS-1);
	}
	
	@Test
	public void testIsValidRadius() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assert ! ship.isValidRadius(MIN_RADIUS-1);
		assert ship.isValidRadius(MIN_RADIUS+1);
	}
	
	@Test
	public void testGetOrientation() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assertEquals(Math.PI,ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testSetOrientation() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.setOrientation(2*Math.PI);
		assertEquals(2*Math.PI,ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testIsValidOrientation() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assert ! ship.isValidOrientation(-Math.PI);
		assert ! ship.isValidOrientation(3*Math.PI);
		assert ship.isValidOrientation(Math.PI);
	}
	
	@Test
	public void testIsTerminated() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assert ! ship.isTerminated();
	}
	
	@Test
	public void testTerminate() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.terminate();
		assert ship.isTerminated();
	}
	
	@Test
	public void testMove_LegalCase() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.move(1);
		double xpos = ship.getXPosition();
		double ypos = ship.getYPosition();
		assertEquals(110, xpos, EPSILON);
		assertEquals(220, ypos, EPSILON);
	}
	
	@Test(expected = IllegalDurationException.class)
	public void testMove_IllegalCase() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.move(-1);
	}
	
	@Test
	public void testTurn() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship.turn(4.5*Math.PI);
		assertEquals(1.5*Math.PI, ship.getOrientation(), EPSILON);
		ship.turn(-7.5*Math.PI);
		assertEquals(0, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testgetDistanceBetween_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		Ship ship2 = facade.createShip(0, 0, 10, 20, 30, Math.PI);
		assertEquals(Math.sqrt(Math.pow(100, 2)+Math.pow(200, 2))-30-30, ship1.getDistanceBetween(ship2), EPSILON);
	}
	
	@Test
	public void testgetDistanceBetween_CaseSameShip() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assertEquals(0,ship1.getDistanceBetween(ship1),EPSILON);
	}
	
	@Test(expected = IllegalShipException.class)
	public void testgetDistanceBetween_IllegalCaseNullShip() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		Ship ship2 = null;
		ship1.getDistanceBetween(ship2);
	}
	
	@Test(expected = IllegalShipException.class)
	public void testgetDistanceBetween_IllegalCaseTerminated() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		Ship ship2 = facade.createShip(0, 0, 10, 20, 30, Math.PI);
		ship1.terminate();
		ship1.getDistanceBetween(ship2);
	}
	
	@Test
	public void testOverlap_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		Ship ship2 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		assert ship1.overlap(ship2);
	}
	
	@Test(expected = IllegalShipException.class)
	public void testOverlap_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		Ship ship2 = facade.createShip(100, 200, 10, 20, 30, Math.PI);
		ship2.terminate();
		ship1.overlap(ship2);
	}
	
	@Test
	public void testGetTimeToCollision_LegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI);
		Ship ship2 = facade.createShip(0, 0, 10, 0, 20, 0);
		assertEquals(3, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test(expected = IllegalShipException.class)
	public void testGetTimeToCollision_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI);
		Ship ship2 = null;
		ship1.getTimeToCollision(ship2);
	}
	
	@Test
	public void testGetTimeToCollision_CaseNoCollision() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 20, 20, Math.PI);
		Ship ship2 = facade.createShip(0, 0, 10, 50, 20, 0);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testGetCollisionPosition_CaseCollision() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI);
		Ship ship2 = facade.createShip(0, 0, 10, 0, 20, 0);
		assertEquals(50, ship1.getCollisionPosition(ship2)[0], EPSILON);
		assertEquals(0, ship1.getCollisionPosition(ship2)[1], EPSILON);
	}
	
	@Test
	public void testGetCollisionPosition_CaseNoCollision() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 20, 20, Math.PI);
		Ship ship2 = facade.createShip(0, 0, 10, 50, 20, 0);
		assertEquals(null, ship1.getCollisionPosition(ship2));
	}
	
	@Test(expected = IllegalShipException.class)
	public void testCollisionPosition_IllegalCase() throws ModelException {
		Ship ship1 = facade.createShip(100, 0, -10, 0, 20, Math.PI);
		Ship ship2 = null;
		ship1.getCollisionPosition(ship2);
	}
	
	public static double MAX_SPEED = 300000;
	
	public static double MIN_RADIUS = 10;
}

