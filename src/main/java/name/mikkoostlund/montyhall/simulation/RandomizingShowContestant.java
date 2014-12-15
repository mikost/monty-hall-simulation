package name.mikkoostlund.montyhall.simulation;

import name.mikkoostlund.montyhall.show.ShowContestant;

public abstract class RandomizingShowContestant implements ShowContestant {
	@Override
	public int chooseDoor(int numberOfDoors) {
		return (int)(numberOfDoors * Math.random());
	}
}
