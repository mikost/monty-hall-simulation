package com.monty.minisimulation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MontyShowTest {

	enum ContestantStrategy {KEEP, SWITCH}

	@Parameterized.Parameters(name ="{index}: test [strategy={0}',' choiceIndex={1}',' carIndex={2}',' numDoors={3}] => outcome={4}")
	public static Collection<Object[]> testParams() {
		return Arrays.asList(new Object[][] {
				{ContestantStrategy.KEEP, 0, 0, 3, ShowOutcome.WIN},
				{ContestantStrategy.KEEP, 0, 1, 3, ShowOutcome.LOSE},
				{ContestantStrategy.KEEP, 0, 2, 3, ShowOutcome.LOSE},

				{ContestantStrategy.KEEP, 1, 0, 3, ShowOutcome.LOSE},
				{ContestantStrategy.KEEP, 1, 1, 3, ShowOutcome.WIN},
				{ContestantStrategy.KEEP, 1, 2, 3, ShowOutcome.LOSE},

				{ContestantStrategy.KEEP, 2, 0, 3, ShowOutcome.LOSE},
				{ContestantStrategy.KEEP, 2, 1, 3, ShowOutcome.LOSE},
				{ContestantStrategy.KEEP, 2, 2, 3, ShowOutcome.WIN},

				{ContestantStrategy.SWITCH, 0, 0, 3, ShowOutcome.LOSE},
				{ContestantStrategy.SWITCH, 0, 1, 3, ShowOutcome.WIN},
				{ContestantStrategy.SWITCH, 0, 2, 3, ShowOutcome.WIN},

				{ContestantStrategy.SWITCH, 1, 0, 3, ShowOutcome.WIN},
				{ContestantStrategy.SWITCH, 1, 1, 3, ShowOutcome.LOSE},
				{ContestantStrategy.SWITCH, 1, 2, 3, ShowOutcome.WIN},

				{ContestantStrategy.SWITCH, 2, 0, 3, ShowOutcome.WIN},
				{ContestantStrategy.SWITCH, 2, 1, 3, ShowOutcome.WIN},
				{ContestantStrategy.SWITCH, 2, 2, 3, ShowOutcome.LOSE},

		});
	}

	private final ContestantStrategy strategy;;
	private final int initialDoorChoice;
	private final int indexOfDoorWithCar;
	private final int numberOfDoors;
	private ShowOutcome expectedShowOutcome;

	public MontyShowTest(ContestantStrategy strategy,
	                     int initialDoorChoice,
	                     int indexOfDoorWithCar,
	                     int numberOfDoors,
	                     ShowOutcome expectedShowOutcome) {
		this.strategy = strategy;
		this.initialDoorChoice = initialDoorChoice;
		this.indexOfDoorWithCar = indexOfDoorWithCar;
		this.numberOfDoors = numberOfDoors;
		this.expectedShowOutcome = expectedShowOutcome;
	}

	@Test
	public void testThatContestantLosesIfHeChoosesAndKeepsDoorZeroWhenCarIsBehindDoorTwo() {

		ShowContestant testContestant = new TestShowContestant(strategy, initialDoorChoice);

		ShowHelper testShowHelper = new TestShowHelper(indexOfDoorWithCar);

		Show show = new DefaultShowImpl(testShowHelper, numberOfDoors);

		ShowOutcome showOutcome = show.runWith(testContestant);

		assertThat(showOutcome, is(equalTo(expectedShowOutcome)));
	}

	private class TestShowHelper implements ShowHelper {
		private int indexOfDoorWithCar;

		public TestShowHelper(int indexOfDoorWithCar) {
			this.indexOfDoorWithCar = indexOfDoorWithCar;
		}

		@Override
		public Doors setupDoors(int numberOfDoors) {
			return new Doors(indexOfDoorWithCar, numberOfDoors);
		}
	}

	private class TestShowContestant implements ShowContestant {

		private ContestantStrategy strategy;
		private int initialDoorChoice;

		public TestShowContestant(ContestantStrategy strategy, int initialDoorChoice) {
			this.strategy = strategy;
			this.initialDoorChoice = initialDoorChoice;
		}

		@Override
		public int makeInitialDoorChoice(List<Integer> doorIndexes) {
			return doorIndexes.get(initialDoorChoice);
		}

		@Override
		public int makeSecondDoorChoice(int initialDoorChoice, List<Integer> doorIndexesAllowedToChangeTo) {
			return strategy == ContestantStrategy.KEEP ? initialDoorChoice : doorIndexesAllowedToChangeTo.get(0);
		}
	}
}
