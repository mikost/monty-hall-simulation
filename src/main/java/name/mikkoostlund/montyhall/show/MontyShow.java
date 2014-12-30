package name.mikkoostlund.montyhall.show;

/**
 * A MontyShow is an 'engine' for a Monty Hall show. 
 * A single show is run by invocation of method {@link #runShow(ShowContestant)}.   
 * @author mikko
 * 
 */
public class MontyShow {

	private ShowHelper showHelper;

	/**
	 * Creates an instance of {@link MontyShow}.
	 * @param showHelper
	 */
	public MontyShow(ShowHelper showHelper) {
		this.showHelper = showHelper;
	}

	/**
	 * Runs a single show.
	 * @param contestant the contestant taking part in the show.
	 * @param showHelper the guy that is responsible for setting up a set of doors; 
	 * one door with a car behind, the remaing doors with goats behind.
	 * @return the result of the show - either a {@link ShowResult#WIN} (meaning that the 
	 * contestant won a car) or a {@link ShowResult#LOSE} (meaning that the contestant 
	 * lost - although he "won" a goat).
	 */
	public ShowResult runShow(ShowContestant contestant) {
		/*
		 *  Prepare for show time - set up the doors! 
		 */
		Doors doors = showHelper.setupDoors();

		// Let contestant choose a door.
		int index = contestant.chooseDoor(doors.numberOfDoors());
		Door doorChosenByContestant = doors.getDoor(index);

		//  Host opens another door (than the one chosen by the contestant), which
		//+ has a goat behind it.
		Door doorOpenedByHost = doors.getDoor(DoorMatcher.doorWithGoat()
		                                                 .not(doorChosenByContestant));

		//  The remaining door - the one not chosen by the contestant nor opened by the host - 
		//+ is the one that the contestant may choose to switch to.
		Door doorThatContestantMaySwitchTo = doors.getDoor(DoorMatcher.anyDoor()
		                                                              .not(doorChosenByContestant)
		                                                              .not(doorOpenedByHost));

		// Let the contestant switch to the other door, if he wants.
		doorChosenByContestant = contestant.keepDoor() ? doorChosenByContestant : doorThatContestantMaySwitchTo;

		// If there is a goat behind the chosen door, the contestant lost.
		if (false == doorChosenByContestant.hasCar()) {
			return ShowResult.LOSE;
		}

		// The contestant won a car!
		return ShowResult.WIN;
	}

}
