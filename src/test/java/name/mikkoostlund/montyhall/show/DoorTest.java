package name.mikkoostlund.montyhall.show;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoorTest {

	@Test
	public void testWithGoat() {
		assertFalse(Door.withGoat().hasCar());
	}

	@Test
	public void testWithCar() {
		assertTrue(Door.withCar().hasCar());
	}
}
