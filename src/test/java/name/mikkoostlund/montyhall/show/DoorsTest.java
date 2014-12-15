package name.mikkoostlund.montyhall.show;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DoorsTest {
	private static final Door GOAT_DOOR_A = Door.withGoat("GOAT_DOOR_A");
	private static final Door GOAT_DOOR_B = Door.withGoat("GOAT_DOOR_B");
	private static final Door CAR_DOOR_D  = Door.withCar("CAR_DOOR_D");

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private Doors doors;

	@Before
	public void setup() {
		doors = new Doors(GOAT_DOOR_A, GOAT_DOOR_B, CAR_DOOR_D);
	}

	@Test
	public void testThatGetDoorReturnsMatchedDoor() {
		assertThat(doors.getDoor(matcherForDoor(GOAT_DOOR_A)), is(sameInstance(GOAT_DOOR_A)));
		assertThat(doors.getDoor(matcherForDoor(GOAT_DOOR_B)), is(sameInstance(GOAT_DOOR_B)));
		assertThat(doors.getDoor(matcherForDoor(CAR_DOOR_D)),  is(sameInstance(CAR_DOOR_D)));
	}

	@Test
	public void testGetDoorThrowsIfNoDoorMatches() {
		exception.expect(NoSuchElementException.class);
		doors.getDoor(MATCHER_THAT_NEVER_MATCHES);
	}

	@Test
	public void testNumberOfDoors() {
		assertThat(doors.numberOfDoors(), is(equalTo(3)));
	}

	@Test
	public void testGetDoorByIndex() {
		assertThat(doors.getDoor(0), is(sameInstance(GOAT_DOOR_A)));
		assertThat(doors.getDoor(1), is(sameInstance(GOAT_DOOR_B)));
		assertThat(doors.getDoor(2), is(sameInstance(CAR_DOOR_D)));
	}

	private DoorMatcher matcherForDoor(final Door expectedDoor) {
		return new DoorMatcher() {
			@Override
			boolean matches(Door door) {
				return door == expectedDoor;
			}
		};
	}

	private static final DoorMatcher MATCHER_THAT_NEVER_MATCHES = new DoorMatcher() {			
		@Override
		boolean matches(Door door) {
			return false;
		}
	};
}
