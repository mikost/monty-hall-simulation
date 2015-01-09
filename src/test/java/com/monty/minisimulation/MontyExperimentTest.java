package com.monty.minisimulation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MontyExperimentTest {

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
