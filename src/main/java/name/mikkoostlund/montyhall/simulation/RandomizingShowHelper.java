package name.mikkoostlund.montyhall.simulation;

import java.util.ArrayList;
import java.util.List;

import name.mikkoostlund.montyhall.show.Door;
import name.mikkoostlund.montyhall.show.Doors;
import name.mikkoostlund.montyhall.show.ShowHelper;

public class RandomizingShowHelper implements ShowHelper {

	private static final int NUMBER_OF_DOORS = 3;

	@Override
	public Doors setupDoors() {
		List<Door> doorList = new ArrayList<>(NUMBER_OF_DOORS);
		int indexOfDoorWithCar = (int)(Math.random() * NUMBER_OF_DOORS);

		for (int i = 0; i < NUMBER_OF_DOORS; i++) { 
			doorList.add(i == indexOfDoorWithCar ? Door.withCar() : Door.withGoat());
		}

		return new Doors(doorList.get(0), doorList.get(1), doorList.get(2));
	}

}
