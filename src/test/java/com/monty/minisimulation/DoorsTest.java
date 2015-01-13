package com.monty.minisimulation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DoorsTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private Doors doors;

	@Before
	public void setup() {
		doors = new Doors(2, 3);
	}

	@Test
	public void testThatGetDoorByMatcherReturnsMatchedDoor() {
		Door door0 = doors.getDoor(0);
		Door door1 = doors.getDoor(1);
		Door door2 = doors.getDoor(2);

		assertThat(doors.getDoor(matcherForDoor(door0)), is(sameInstance(door0)));
		assertThat(doors.getDoor(matcherForDoor(door1)), is(sameInstance(door1)));
		assertThat(doors.getDoor(matcherForDoor(door2)), is(sameInstance(door2)));
	}

	@Test
	public void testGetDoorThrowsIfNoDoorMatches() {
		exception.expect(NoSuchElementException.class);
		doors.getDoor(MATCHER_THAT_NEVER_MATCHES);
	}

	@Test
	public void testThatNumberOfDoorsReturnsNumberOfDoors() {
		for (int numberOfDoors = 1; numberOfDoors < 10; numberOfDoors++) {
			assertThat(new Doors(0, numberOfDoors).numberOfDoors(), is(equalTo(numberOfDoors)));
		}
	}

	@Test
	public void testThatAllDoorsAreDistinct() {

		for (PairOfDistinctIndexes distinctIndexes : PairOfDistinctIndexes.allLessThan(doors.numberOfDoors())) {
			Door oneDoor = doors.getDoor(distinctIndexes.oneIndex);
			Door anotherdoor = doors.getDoor(distinctIndexes.anotherIndex);

			assertThat(oneDoor, not(is(sameInstance(anotherdoor))));
		}
	}


	@Test
	public void testThatRequestedCarDoorHasCarAndOtherDoorsHaveNot() {
		final int numberOfDoors = 3;
		for (int carIndex = 0; carIndex < numberOfDoors; carIndex++) {
			Doors doors = new Doors(carIndex, numberOfDoors);
		
			for (int i = 0; i < numberOfDoors; i++) {
				assertThat("new Doors("+carIndex+", "+numberOfDoors+").getDoor("+i+").hasCar()", 
						doors.getDoor(i).hasCar(), is(equalTo(i == carIndex)));
			}
		}
	}

	@Test
	public void testThatExactlyOneDoorHasCar() {
		for (int nDoors = 3; nDoors < 10; nDoors++) {
			for (int carIndex = 0; carIndex < nDoors; carIndex++) {
				Doors doors = new Doors(carIndex, nDoors);
				assertThat("number of cars in new Doors("+ carIndex +","+nDoors+")", 
						numberOfCarsIn(doors), is(equalTo(1)));
			}
		}
	}

	private Integer numberOfCarsIn(Doors doors) {
		int nCars = 0;
		for (int i = 0; i < doors.numberOfDoors(); i++) {
			if (doors.getDoor(i).hasCar()) {
				nCars++;
			}
		}
		return nCars;
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

	private static class PairOfDistinctIndexes {
		final int oneIndex;
		final int anotherIndex;

		PairOfDistinctIndexes(int oneIndex, int anotherIndex) {
			if (oneIndex == anotherIndex) throw new IllegalArgumentException();
			if (oneIndex < 0) throw new IllegalArgumentException();
			if (anotherIndex < 0) throw new IllegalArgumentException();
			this.oneIndex = oneIndex;
			this.anotherIndex = anotherIndex;
		}

		static List<PairOfDistinctIndexes> allLessThan(int indexLimit) {
			List<PairOfDistinctIndexes> list = new ArrayList<>();
			for (int oneIndex = 0; oneIndex < indexLimit - 1; oneIndex++) {
				for (int anotherIndex = oneIndex + 1; anotherIndex < indexLimit; anotherIndex++) {
					list.add(new PairOfDistinctIndexes(oneIndex, anotherIndex));
				}
			}
			return list;
		}
	}
}
