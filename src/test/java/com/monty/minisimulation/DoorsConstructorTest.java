package com.monty.minisimulation;

import static com.monty.minisimulation.DoorsConstructorTest.ExceptionExpectation.EXPECT_OK;
import static com.monty.minisimulation.DoorsConstructorTest.ExceptionExpectation.expect;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DoorsConstructorTest {

	static final class ExceptionExpectation {
		public static final ExceptionExpectation EXPECT_OK = new ExceptionExpectation();

		public static ExceptionExpectation expect(Class<IllegalArgumentException> clazz) {
			return new ExceptionExpectation(clazz);
		}

		private final Class<? extends Throwable> expectedException;

		private ExceptionExpectation() { // Used for instantiation of ExceptionExpectation.EXPECT_OK only.
			this.expectedException = null;
		}

		private ExceptionExpectation(Class<? extends Throwable> expectedException) {
			if (expectedException == null) throw new IllegalArgumentException();
			this.expectedException = expectedException;
		}

		boolean expectException() {
			return this != EXPECT_OK;
		}

		Class<? extends Throwable> getExpectedException() {
			if (this == EXPECT_OK) {
				throw new IllegalStateException("Called getExpectedThrowable on ExceptionExpectation.NONE");
			}
			return expectedException;
		}

		@Override
		public String toString() {
			return expectException() ? "throws "+ getExpectedException().getName() : "does not throw";
		}

	}


	@Parameterized.Parameters(name ="{index}: test new Doors[{0}',' {1}] {2}")
	public static Collection<Object[]> constructorParameters() {
	      return Arrays.asList(new Object[][] {
		  	         {-1, 3,  expect(IllegalArgumentException.class) },
		  	         { 0, 3,  EXPECT_OK     },
		  	         { 1, 3,  EXPECT_OK     },
		  	         { 2, 3,  EXPECT_OK     },
		  	         { 3, 3,  expect(IllegalArgumentException.class) },
		  	         { 3, 4,  EXPECT_OK },
		  	         { 4, 4,  expect(IllegalArgumentException.class) },
		      });
	}

	private int indexOfCarDoor;
	private int numberOfDoors;

	@Rule
	public ExpectedException expect = ExpectedException.none();
	
	public DoorsConstructorTest(int indexOfCarDoor, int numberOfDoors, ExceptionExpectation expectation) {
		this.indexOfCarDoor = indexOfCarDoor;
		this.numberOfDoors = numberOfDoors;

		if (expectation.expectException()) {
			expect.expect(expectation.getExpectedException());
		}
	}

	@Test
	public void testDoorsConstructor() {
		new Doors("dummy", indexOfCarDoor, numberOfDoors);
	}

}
