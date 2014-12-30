package name.mikkoostlund.montyhall.simulation;

/**
 * Contains information about a number of Monty Hall simulations run by 
 * {@link MontySimulationsRunner}: the total number of simulations run for each kind of 
 * contestant (method {@link #getRunCount()}) and the number of times each contestant has won 
 * (methods {@link #getKeepWinCount()} and {@link #getSwitchWinCount()}). 
 * @author mikko
 *
 */
public class RunStatistics {
	private final int totalRunCount;
	private final int keepWinCount;
	private final int switchWinCount;

	public RunStatistics(int totalRunCount, int switchWinCount, int keepWinCount) {
		this.totalRunCount = totalRunCount;
		this.keepWinCount = keepWinCount;
		this.switchWinCount = switchWinCount;		
	}

	public static class Builder {
		private int totalRuns;
		private int keepWinCount;
		private int switchWinCount;

		public Builder withTotalRuns(int totalRuns) {
			this.totalRuns = totalRuns;
			return this;
		}

		public Builder withKeepWinCount(int keepWinCount) {
			this.keepWinCount = keepWinCount;
			return this;
		}

		public Builder withSwitchWinCount(int switchWinCount) {
			this.switchWinCount = switchWinCount;
			return this;
		}

		public RunStatistics build() {
			return new RunStatistics(totalRuns, switchWinCount, keepWinCount);
		}
	}

	/**
	 * @return the number of times a Monty Hall simulation has been run for each kind of 
	 * contestant.
	 */
	public int getRunCount() {
		return totalRunCount;
	}

	/**
	 * @return the number of times that the contestant who always keeps his door has won. 
	 */
	public int getKeepWinCount() {
		return keepWinCount;
	}

	/**
	 * @return the number of times that the contestant who always switches door has won. 
	 */
	public int getSwitchWinCount() {
		return switchWinCount;
	}

}
