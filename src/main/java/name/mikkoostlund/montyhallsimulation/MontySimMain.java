package name.mikkoostlund.montyhallsimulation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import name.mikkoostlund.montyhallsimulation.MontyExperiment.ShowStatistics;

public class MontySimMain {

	public static void main(String[] args) {
		Map<String, ShowContestant> showContestants = new HashMap<String, ShowContestant>();
		showContestants.put("switch door strategy", new SwitchingShowContestant());
		showContestants.put("keep door strategy", new KeepingShowContestant());

		MontyExperiment montyExperiment = new MontyExperiment(1000, showContestants.values());
		ShowStatistics showStatistics = montyExperiment.run();
		presentStatistics(showStatistics, showContestants);
	}

	private static void presentStatistics(ShowStatistics showStatistics, Map<String, ShowContestant> showContestants) {
		assert (showContestants != null && showContestants.size() > 0);
		int showCount = showStatistics.getShowCount(anyOf(showContestants.values()));

		System.out.println("The simulation was run "+ showCount +" times.");
		showContestants.entrySet().stream().forEach(entry -> {
				int nTimesWon = showStatistics.getOutcomeCount(ShowOutcome.WIN, entry.getValue());
				System.out.println("Using the \""+ entry.getKey() +"\", the contestant won "+ nTimesWon +" times.");
			}
		);
	}

	private static <T> T anyOf(Collection<T> collection) {
		return collection.iterator().next();
	}
}
