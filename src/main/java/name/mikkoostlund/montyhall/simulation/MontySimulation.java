package name.mikkoostlund.montyhall.simulation;

import name.mikkoostlund.montyhall.show.MontyShow;
import name.mikkoostlund.montyhall.show.ShowContestant;
import name.mikkoostlund.montyhall.show.ShowResult;

public class MontySimulation {

	MontyShow montyShow = new MontyShow(new RandomizingShowHelper());

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
