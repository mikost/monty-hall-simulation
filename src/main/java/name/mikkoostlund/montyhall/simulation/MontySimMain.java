package name.mikkoostlund.montyhall.simulation;

public class MontySimMain {

	public static void main(String[] args) {
		MontySimulationsRunner montySimulationsRunner = new MontySimulationsRunner();
		RunStatistics stat = montySimulationsRunner.run(1000);
		presentStatistics(stat);
	}

	private static void presentStatistics(RunStatistics stat) {
		System.out.println("The simulation was run "+ stat.getRunCount() + " times.");
		System.out.println("Using the \"switch door strategy\", the contestant won "+ stat.getSwitchWinCount() + " times.");
		System.out.println("Using the \"keep door strategy\", the contestant won "+ stat.getKeepWinCount() + " times.");
	}
}
