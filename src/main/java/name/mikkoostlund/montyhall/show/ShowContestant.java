package name.mikkoostlund.montyhall.show;

/**
 * A <code>ShowContestant</code> represents a contestant who takes part in Monty Hall show.
 * @author mikko
 *
 */
public interface ShowContestant {

	/**
	 * @param numberOfDoors the number of doors in the Monty Hall show.
	 * @return the index (0 <= index < numberOfDoors) of the <code>Door</code> 
	 * that the contestant chooses. 
	 */
	int chooseDoor(int numberOfDoors);

	/**
	 * @return <code>true</code> if the contestant wants to stick with his initial choice; 
	 * false if the contestant wants to change to the other door (the one that he did not 
	 * initially choose and that the host did not open).
	 */
	abstract boolean keepDoor();

}
