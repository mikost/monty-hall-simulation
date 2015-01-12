package com.monty.minisimulation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.monty.minisimulation.MontyExperiment.ShowStatistics;

public class MontyExperimentIT {

	@Test
	public void testThatItRunsShowWithAllPassedInContestantsTheCorrectNumberOfTimes() {
		ShowStub showStub = new ShowStub();
		showStub.setStubbedOutcome(ShowOutcome.WIN);

		int numberOfTimesToRunShowForEachContestant = 123;
		int numberOfContestants = 456;
		
		List<ContestantStub> contestantStubs = generateContestants(numberOfContestants); 
				
		MontyExperiment montyExperiment = new MontyExperiment(
				showStub,
				numberOfTimesToRunShowForEachContestant,
				contestantStubs
		);

		montyExperiment.run();

		for (ContestantStub contestantStub: contestantStubs) {
			assertThat("number of shows run with contestant "+ contestantStub.id, showStub.numberOfShowsRunWith(contestantStub), is(equalTo(numberOfTimesToRunShowForEachContestant)));
		}
	}

	@Test
	public void testIt() {
		final int wantedNumberOfDoors = 3;
		int timesToRunShow = 999;

		Show show = new DefaultShowImpl(new ShowHelper() {

			private int indexOfDoorWithCar = 0;

			@Override
			public Doors setupDoors(int numberOfDoors) {
				assertThat(numberOfDoors, is(equalTo(wantedNumberOfDoors)));
				Doors doors = new Doors(null, indexOfDoorWithCar, numberOfDoors);
				indexOfDoorWithCar ++;
				if (indexOfDoorWithCar == wantedNumberOfDoors) {
					indexOfDoorWithCar = 0;
				}
				return doors;
			}
			
		}, wantedNumberOfDoors);

		ShowContestant switching = new ShowContestant() {

			@Override
			public int makeInitialDoorChoice(List<Integer> doorIndexes) {
				return 0;
			}

			@Override
			public int makeSecondDoorChoice(int initialDoorChoice, List<Integer> doorIndexesAllowedToChangeTo) {
				assertThat(doorIndexesAllowedToChangeTo.size(), is(equalTo(1)));
				return doorIndexesAllowedToChangeTo.get(0);
			}
			
		};

		ShowContestant keeping = new ShowContestant() {

			@Override
			public int makeInitialDoorChoice(List<Integer> doorIndexes) {
				return 0;
			}

			@Override
			public int makeSecondDoorChoice(int initialDoorChoice, List<Integer> doorIndexesAllowedToChangeTo) {
				return 0;
			}
			
		};

		Collection<ShowContestant> showContestants = Arrays.asList(
				switching,
				keeping
		);

		MontyExperiment montyExperiment = new MontyExperiment(show, timesToRunShow, showContestants);

		ShowStatistics showStatistics = montyExperiment.run();

		assertThat("Number of times switching contestant took part in show", showStatistics.getShowCount(switching), is(equalTo(timesToRunShow)));
		assertThat("Number of times switching contestant won", showStatistics.getOutcomeCount(ShowOutcome.WIN, switching), is(equalTo(666)));
		assertThat("Number of times keeping contestant took part in show", showStatistics.getShowCount(keeping), is(equalTo(timesToRunShow)));
		assertThat("Number of times keeping contestant won", showStatistics.getOutcomeCount(ShowOutcome.WIN, keeping), is(equalTo(333)));
	}

	private List<ContestantStub> generateContestants(int numberOfContestants) {
		List<ContestantStub> contestants = new ArrayList<>(numberOfContestants);
		for (int i = 0; i < numberOfContestants; i++) {
			contestants.add(new ContestantStub(i));
		}
		return contestants;
	}

	private static class ShowStub implements Show {
		private ShowOutcome stubbedOutcome;

		private Map<ShowContestant, Integer> contestantMap = new HashMap<>();

		void setStubbedOutcome(ShowOutcome stubbedOutcome) {
			this.stubbedOutcome = stubbedOutcome;
		}

		public int numberOfShowsRunWith(ContestantStub showContestant) {
			Integer count = contestantMap.get(showContestant);
			return (count == null) ? 0 : count;
		}

		@Override
		public ShowOutcome runWith(ShowContestant showContestant) {
			Integer count = contestantMap.get(showContestant);
			contestantMap.put(showContestant, (count == null) ? 1 : count + 1);
			return stubbedOutcome;
		}
	}

	private static class ContestantStub implements ShowContestant {
		private int id;
		public ContestantStub(int id) {
			this.id = id;
		}
		@Override
		public int makeInitialDoorChoice(List<Integer> doorIndexes) {
			return doorIndexes.get(0);
		}
		@Override
		public int makeSecondDoorChoice(int initialDoorChoice, List<Integer> doorIndexesAllowedToChangeTo) {
			return initialDoorChoice;
		}
	}
}
