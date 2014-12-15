package name.mikkoostlund.montyhall.show;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoorMatcherTest {

	@Test
	public void testThatDoorMatcherForDoorWithCarMatchesDoorWithCar() {
		assertTrue("DoorMatcher.doorWithCar().matches(Door.withCar()) returned false",
				    DoorMatcher.doorWithCar().matches(Door.withCar()));
	}

	@Test
	public void testThatDoorMatcherForDoorWithCarDoesNotMatchDoorWithGoat() {
		assertFalse("DoorMatcher.doorWithCar().matches(Door.withGoat()) returned true",
				     DoorMatcher.doorWithCar().matches(Door.withGoat()));
	}

	@Test
	public void testThatDoorMatcherForDoorWithGoatMatchesDoorWithGoat() {
		assertTrue("DoorMatcher.doorWithGoat().matches(Door.withGoat()) returned false",
				    DoorMatcher.doorWithGoat().matches(Door.withGoat()));
	}

	@Test
	public void testThatDoorMatcherForDoorWithGoatDoesNotMatchDoorWithCar() {
		assertFalse("DoorMatcher.doorWithGoat().matches(Door.withCar()) returned true",
				     DoorMatcher.doorWithGoat().matches(Door.withCar()));
	}
}
