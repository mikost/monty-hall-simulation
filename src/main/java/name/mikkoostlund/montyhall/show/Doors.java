package name.mikkoostlund.montyhall.show;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A Doors instance abstracts the set of doors in a Monty Hall show.
 * A Doors instance contains three instances of {@link Door}, where 
 * exactly one Door "has a car" (its {@link Door#hasCar() hasCar} method returns <code>true</code>) 
 * and each of the remaining ones "has a goat" (its {@link Door#hasCar() hasCar} method 
 * returns <code>false</code>).
 * @author mikko
 *
 */
public class Doors  {

	private final List<Door> doors;

	/**
	 * Constructs a <code>Doors</code> instance
	 * @param door0 the <code>Door</code> at index 0.
	 * @param door1 the <code>Door</code> at index 1.
	 * @param door2 the <code>Door</code> at index 2.
	 * @throws NullPointerException if any argument is null
	 * @throws IllegalArgumentException if any two arguments 
	 * refer to the same <code>Door</code> instance or if not
	 * exactly one of the <code>Door</code>s "has a car behind it".
	 */
	public Doors(Door door0, Door door1, Door door2) {
		doors = Collections.unmodifiableList(Arrays.asList(door0, door1, door2));
		validate();
	}

	private void validate() {
		validateNotNull();
		validateDistinct();
		validateExactlyOneHasCar();
	}

	private void validateNotNull() {
		for (Door door: doors) {
			if (door == null) throw new NullPointerException(); 
		}
	}

	private void validateDistinct() {
		HashSet<Door> hashSet = new HashSet<Door>();
		hashSet.addAll(doors);
		if (hashSet.size() != doors.size()) {
			throw new IllegalArgumentException("not all were distinct");
		}
	}

	private void validateExactlyOneHasCar() {
		int cars = 0;
		for (Door door : doors) {
			if (door.hasCar()) {
				cars++;
			}
		}
		if (cars != 1) {
			throw new IllegalArgumentException("tried to create "+ this.getClass().getSimpleName() +" with "+ cars +" cars; there must be exactly one car");
		}
	}

	/**
	 * Returns the lowest-indexed <code>Door</code> instance within this <code>Doors</code>, 
	 * which is matched by the specified <code>DoorMatcher</code>. If there is no instance 
	 * matched by the specified <code>DoorMatcher</code>, a <code>NoSuchElementException</code> 
	 * is thrown.  
	 * @param matcher a <code>DoorMatcher</code> which specifies the <code>Door</code> within 
	 * this <code>Doors</code> to retrieve. 
	 * @return a <code>Door</code> instance, which is contained in this <code>Doors</code> 
	 * and which is matched by the specified <code>DoorMatcher<code>.
	 * @throws NoSuchElementException if no <code>Door</code> instance within
	 * this <code>Doors</code> instance is matched by the specified <code>DoorMatcher</code>.
	 */
	Door getDoor(DoorMatcher matcher) {
		for (Door door: doors) {
			if (matcher.matches(door)) {
				return door;
			}
		}
		throw new NoSuchElementException();
	}

	/**
	 * @return the number of {@link Door} instances contained by this <code>Doors</code> instance.
	 */
	public int numberOfDoors() {
		return doors.size();
	}

	/**
	 * @param index the index of the <code>Door</code> to retrieve.
	 * @return the <code>Door</code> instance at index <code>index</code> within this 
	 * <code>Doors</code> instance.
	 */
	public Door getDoor(int index) {
		return doors.get(index);
	}
}
