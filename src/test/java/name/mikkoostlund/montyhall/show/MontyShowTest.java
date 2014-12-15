package name.mikkoostlund.montyhall.show;

import static org.junit.Assert.*;
import name.mikkoostlund.montyhall.show.MontyShow;
import name.mikkoostlund.montyhall.show.Door;
import name.mikkoostlund.montyhall.show.Doors;
import name.mikkoostlund.montyhall.show.ShowHelper;
import name.mikkoostlund.montyhall.show.ShowContestant;
import name.mikkoostlund.montyhall.show.ShowResult;

import org.junit.Test;

public class MontyShowTest {

	@Test
	public void testThatContestantLosesIfHeChoosesAndKeepsDoorZeroWhenCarIsBehindDoorTwo() {
		ShowContestant contestant = new KeepingShowContestant(0); 
		ShowHelper showHelper = createFixed(Door.withGoat(), Door.withGoat(), Door.withCar());
		MontyShow show = new MontyShow(showHelper);

		ShowResult result = show.runShow(contestant);

		assertTrue(result == ShowResult.LOSE);
	}

	@Test
	public void testThatContestantWinsIfHeChoosesAndKeepsDoorZeroWhenCarIsBehindDoorZero() {
		ShowContestant contestant = new KeepingShowContestant(0); 
		ShowHelper showHelper = createFixed(Door.withCar(), Door.withGoat(), Door.withGoat());
		MontyShow show = new MontyShow(showHelper);

		ShowResult result = show.runShow(contestant);

		assertTrue(result == ShowResult.WIN);
	}

	@Test
	public void testThatContestantWinsIfHeChoosesDoorZeroAndThenSwitchesWhenCarIsBehindDoorTwo() {
		ShowContestant contestant = new SwitchingShowContestant(0); 
		ShowHelper showHelper = createFixed(Door.withGoat(), Door.withGoat(), Door.withCar());
		MontyShow show = new MontyShow(showHelper);

		ShowResult result = show.runShow(contestant);

		assertTrue(result == ShowResult.WIN);
	}

	@Test
	public void testThatContestantLosesIfHeChoosesDoorZeroAndThenSwitchesWhenCarIsBehindDoorZero() {
		ShowContestant contestant = new SwitchingShowContestant(0); 

		ShowHelper showHelper = createFixed(Door.withCar(), Door.withGoat(), Door.withGoat());

		MontyShow show = new MontyShow(showHelper);
		ShowResult result = show.runShow(contestant);

		assertTrue(result == ShowResult.LOSE);
	}

	private ShowHelper createFixed(final Door door0, final Door door1, final Door door2) {
		return new ShowHelper() {
			@Override
			public Doors setupDoors() {
				return new Doors(door0, door1, door2);
			}
		};
	}


	private abstract class TestShowContestantBase implements ShowContestant {

		private int initialDoorChoice;

		public TestShowContestantBase(int initialDoorChoice) {
			this.initialDoorChoice = initialDoorChoice;
		}

		public int chooseDoor(int numberOfDoors) {
			return initialDoorChoice;
		}

	}

	private class KeepingShowContestant extends TestShowContestantBase {
		public KeepingShowContestant(int initialDoorChoice) {
			super(initialDoorChoice);			
		}

		@Override
		public boolean keepDoor() {
			return true;
		}
	}

	private class SwitchingShowContestant extends TestShowContestantBase {
		public SwitchingShowContestant(int initialDoorChoice) {
			super(initialDoorChoice);			
		}

		@Override
		public boolean keepDoor() {
			return false;
		}
	}

}
