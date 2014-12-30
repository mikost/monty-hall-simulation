package name.mikkoostlund.montyhall.simulation;

import name.mikkoostlund.montyhall.show.MontyShow;
import name.mikkoostlund.montyhall.show.ShowContestant;
import name.mikkoostlund.montyhall.show.ShowResult;

/**
 * A MontySimulationsRunner (when its {@link #run(int numberOfTimes)} method is called) runs a {@link MontyShow} 
 * a number of times, with two kinds of {@link ShowContestant ShowContestants} - one which always
 * keeps the door he first chooses and one which always switches door. For each kind of contestant 
 * the {@link MontySimulationsRunner} keeps track of how many times the contestant wins a car. 
 * The results are then returned as a {@link RunStatistics}.
 * @author mikko
 *
 */
public class MontySimulationsRunner {

	MontyShow montyShow = new MontyShow(new RandomizingShowHelper());

	/**
	 * Runs a {@link MontyShow} the number of times specified by {@code numberOfTimes}, with two 
	 * kinds of {@link ShowContestant ShowContestants} - one which always keeps the door he first
	 * chooses and one which always switches door. For each kind of contestant, the number of 
	 * times the contestant wins a car is recorded in the returned {@link RunStatistics}.
	 * @param numberOfTimes the number of times to run the {@link MontyShow} for each contestant.
	 * @return a {@link RunStatistics} with information about how many times each kind of contestant 
	 * has won.
	 */
	public RunStatistics run(int numberOfTimes) {

		int keepWinCount = runWithContestant(numberOfTimes, CONTESTANT_THAT_ALWAYS_KEEPS_DOOR);

		int switchWinCount = runWithContestant(numberOfTimes, CONTESTANT_THAT_ALWAYS_SWITCHES_DOOR);

		return new RunStatistics.Builder().withTotalRuns(numberOfTimes)
		                                  .withKeepWinCount(keepWinCount)
		                                  .withSwitchWinCount(switchWinCount)
		                                  .build();
	}

	private int runWithContestant(int numberOfTimes, ShowContestant showContestant) {
		int winCount = 0;

		for (int runCount = 0; runCount < numberOfTimes; runCount++) {
			ShowResult showResult = montyShow.runShow(showContestant);

			if (showResult == ShowResult.WIN) {
				winCount++;
			}
		}

		return winCount;
	}

	private static final ShowContestant CONTESTANT_THAT_ALWAYS_KEEPS_DOOR
				= new RandomizingShowContestant() {
					@Override
					public boolean keepDoor() {
						return true;
					}
				};

	private static final ShowContestant CONTESTANT_THAT_ALWAYS_SWITCHES_DOOR
				= new RandomizingShowContestant() {
					@Override
					public boolean keepDoor() {
						return false;
					}
				};
}
