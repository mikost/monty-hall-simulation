package name.mikkoostlund.montyhall.simulation;

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

	public int getRunCount() {
		return totalRunCount;
	}

	public int getKeepWinCount() {
		return keepWinCount;
	}

	public int getSwitchWinCount() {
		return switchWinCount;
	}

}
