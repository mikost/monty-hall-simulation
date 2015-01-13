package com.monty.minisimulation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.monty.minisimulation.MontyExperiment.ShowStatistics;

public class MontyMain {

	private static final int DEFAULT_TIMES = 1000;

	public static void main(String[] args) {
		int timesToRunShow = parse(args).getTimesToRunShow();

		Collection<ShowContestantBase> showContestants = Arrays.asList(
				new KeepingContestant("KEEPING_CONTESTANT"),
				new SwitchingContestant("SWITCHING_CONTESTANT")
		);

		MontyExperiment montyExperiment = new MontyExperiment(timesToRunShow, showContestants);

		ShowStatistics showStatistics = montyExperiment.run();

		presentStatistics(showStatistics, showContestants);
	}

	private interface Args {

		int getTimesToRunShow();
		
	}

	private static Args parse(String[] args) {
		final int timesToRunShow;

		switch (args.length) {
		case 0: timesToRunShow = DEFAULT_TIMES;                break;
		case 1:	timesToRunShow = parseNonNegativeInt(args[0]); break;

		default:
			throw new IllegalArgumentException("Found "+ args.length +" arguments; expected 0 or 1 argument.");
		}

		return new Args() {
			@Override
			public int getTimesToRunShow() {
				// TODO Auto-generated method stub
				return timesToRunShow;
			}
		};
	}

	private static int parseNonNegativeInt(String intString) {
		final int timesToRunShow;
		try {	
			timesToRunShow = Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Found "+ intString +"; expected an integer.", e);
		}
		if (timesToRunShow < 0) {
			throw new IllegalArgumentException("Found "+ intString +"; expected a non-negative integer.");
		}
		return timesToRunShow;
	}

	private static abstract class ShowContestantBase implements ShowContestant {
		Random random = new Random();
		private final String description;

		ShowContestantBase(String description) {
			this.description = description;
		}

		public String getDescription() {
			return this.description;
		}

		@Override
		public int makeInitialDoorChoice(List<Integer> doorIndexes) {
			int i = random.nextInt(doorIndexes.size());
			return doorIndexes.get(i);
		}
	}

	private static class KeepingContestant extends ShowContestantBase {

		KeepingContestant(String description) {
			super(description);
		}

		@Override
		public int makeSecondDoorChoice(int doorIndexPreviouslyChosen, List<Integer> doorIndexesAvailable) {
			return doorIndexPreviouslyChosen;
		}		
	}

	private static class SwitchingContestant extends ShowContestantBase {

		SwitchingContestant(String description) {
			super(description);
		}

		@Override
		public int makeSecondDoorChoice(int doorIndexPreviouslyChosen, List<Integer> doorIndexesAvailable) {
			return pickOneAtRandomFrom(doorIndexesAvailable);
		}

		private int pickOneAtRandomFrom(List<Integer> doorIndexesAvailable) {
			return doorIndexesAvailable.get(random.nextInt(doorIndexesAvailable.size()));
		}		
	}

	private static void presentStatistics(ShowStatistics showStatistics, Collection<ShowContestantBase> showContestants) {

		int maxNumberOfWins = -1;
		ShowContestantBase bestContestant = null;
		for (ShowContestantBase showContestant : showContestants) {
			int numberOfWins = showStatistics.getOutcomeCount(ShowOutcome.WIN, showContestant);
			if (numberOfWins > maxNumberOfWins) {
				maxNumberOfWins = numberOfWins;
				bestContestant = showContestant;
			}

			System.out.println("Result for contestant \""+ showContestant.getDescription() +"\":");
			System.out.println("  - took part in "+ showStatistics.getShowCount(showContestant) +" shows");
			System.out.println("  - won in "+ numberOfWins +" shows");
		}
		System.out.println("Best result was obtained by contestant \""+ bestContestant.getDescription());
	}
}
