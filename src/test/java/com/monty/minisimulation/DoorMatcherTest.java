package com.monty.minisimulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DoorMatcherTest {

	private Door doorWithCar;
	private Door doorWithGoat;

	@Before
	public void setup() {
		Doors doors = new Doors(0, 3);
		doorWithCar = doors.getDoor(0);
		doorWithGoat = doors.getDoor(1);
	}

	@Test
	public void testThatDoorMatcherForDoorWithCarMatchesDoorWithCar() {
		assertTrue("DoorMatcher.doorWithCar().matches(doorWithCar) returned false",
				    DoorMatcher.doorWithCar().matches(doorWithCar));
	}

	@Test
	public void testThatDoorMatcherForDoorWithCarDoesNotMatchDoorWithGoat() {
		assertFalse("DoorMatcher.doorWithCar().matches(doorWithGoat) returned true",
				     DoorMatcher.doorWithCar().matches(doorWithGoat));
	}

	@Test
	public void testThatDoorMatcherForDoorWithGoatMatchesDoorWithGoat() {
		assertTrue("DoorMatcher.doorWithGoat().matches(doorWithGoat) returned false",
				    DoorMatcher.doorWithGoat().matches(doorWithGoat));
	}

	@Test
	public void testThatDoorMatcherForDoorWithGoatDoesNotMatchDoorWithCar() {
		assertFalse("DoorMatcher.doorWithGoat().matches(doorWithCar) returned true",
				     DoorMatcher.doorWithGoat().matches(doorWithCar));
	}

}
