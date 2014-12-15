package name.mikkoostlund.montyhall.show;

/**
 * An instances of this class represents a door in a Monty Hall show. The door either "has 
 * a goat behind it" or "has a car behind it", as determined by the {@link #hasCar} method.
 * @author mikko
 *
 */
public class Door {

	private final boolean hasCar;
	private final String label;

	
	/**
	 * @param hasCar if <code>true</code>, this <code>Door</code> "has a car behind it".
	 * Otherwise, it "has a goat behind it".
	 */
	private Door(boolean hasCar, String label) {
		this.hasCar = hasCar;
		this.label = label;
	}

	private Door(boolean hasCar) {
		this.hasCar = hasCar;
		this.label = super.toString();
	}

	/**
	 * @return <code>true</code> if, and only if, this <code>Door</code> "has a car behind it".
	 */
	boolean hasCar() {
		return this.hasCar;
	}

	/**
	 * @return a newly created <code>Door</code> that "has a goat behind it".
	 */
	public static Door withGoat() {
		return new Door(false);
	}

	/**
	 * @return a newly created <code>Door</code> that "has a goat behind it".
	 */
	public static Door withGoat(String label) {
		return new Door(false, label);
	}

	/**
	 * @return a newly created <code>Door</code> that "has a car behind it".
	 */
	public static Door withCar() {
		return new Door(true);
	}

	/**
	 * @return a newly created <code>Door</code> that "has a car behind it".
	 */
	public static Door withCar(String label) {
		return new Door(true, label);
	}

	@Override
	public String toString() {
		return label;
	}
}
