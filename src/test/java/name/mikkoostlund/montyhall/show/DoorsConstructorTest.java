package name.mikkoostlund.montyhall.show;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DoorsConstructorTest {
	private final static Door GOAT_DOOR_A = Door.withGoat("GOAT_DOOR_A");
	private final static Door GOAT_DOOR_B = Door.withGoat("GOAT_DOOR_B");
	private final static Door GOAT_DOOR_C = Door.withGoat("GOAT_DOOR_C");
	private final static Door CAR_DOOR_D  = Door.withCar("CAR_DOOR_D");
	private final static Door CAR_DOOR_E  = Door.withCar("CAR_DOOR_E");
	private final static Door CAR_DOOR_F  = Door.withCar("CAR_DOOR_F");

	private Door door0;
	private Door door1;
	private Door door2;
	private Class<? extends Throwable> expectedThrowable;

	@Parameterized.Parameters(name ="{index}: [{0}|{1}|{2}] => {3}")
	   public static Collection<Object[]> constructorParameters() {
	      return Arrays.asList(new Object[][] {
	  	         {GOAT_DOOR_A, GOAT_DOOR_B, CAR_DOOR_F,  null /* no exception */       },
	 	         {GOAT_DOOR_A, CAR_DOOR_E,  GOAT_DOOR_C, null /* no exception */       },
	 	         {CAR_DOOR_D,  GOAT_DOOR_B, GOAT_DOOR_C, null /* no exception */       },

	 	         {null,        GOAT_DOOR_B, CAR_DOOR_F,  NullPointerException.class    },
		         {GOAT_DOOR_A, null,        CAR_DOOR_F,  NullPointerException.class    },
		         {GOAT_DOOR_A, GOAT_DOOR_B, null,        NullPointerException.class    },
		         {null,        null,        CAR_DOOR_F,  NullPointerException.class    },
		         {GOAT_DOOR_A, null,        null,        NullPointerException.class    },
		         {null,        GOAT_DOOR_B, null,        NullPointerException.class    },
		         {null,        null,        null,        NullPointerException.class    },

		         {GOAT_DOOR_A, GOAT_DOOR_B, GOAT_DOOR_C, IllegalArgumentException.class},
		         {CAR_DOOR_D,  CAR_DOOR_E,  GOAT_DOOR_C, IllegalArgumentException.class},
		         {GOAT_DOOR_A, CAR_DOOR_E,  CAR_DOOR_F,  IllegalArgumentException.class},
		         {CAR_DOOR_D,  GOAT_DOOR_B, CAR_DOOR_F,  IllegalArgumentException.class},
		         {CAR_DOOR_D,  CAR_DOOR_E,  CAR_DOOR_F,  IllegalArgumentException.class},

		         {CAR_DOOR_D,  GOAT_DOOR_B, GOAT_DOOR_B, IllegalArgumentException.class},
		         {GOAT_DOOR_A, CAR_DOOR_E,  GOAT_DOOR_A, IllegalArgumentException.class},
		         {GOAT_DOOR_A, GOAT_DOOR_A, CAR_DOOR_F,  IllegalArgumentException.class},
	      });
	   }

	public DoorsConstructorTest(Door door0, Door door1, Door door2, Class<? extends Throwable> expectedThrowable) {
		this.door0 = door0;
		this.door1 = door1;
		this.door2 = door2;
		this.expectedThrowable = expectedThrowable;
	}

	@Test
	public void testConstructDoors() {
		try {
			new Doors(door0, door1, door2);
		} catch (Throwable throwable) {
			if (throwable.getClass().equals(expectedThrowable)) {
				return;
			}
			String expected = (expectedThrowable != null ? expectedThrowable.getSimpleName() : "no exception");
			throw new AssertionError("Expected "+ expected +" to be thrown, but "+throwable+" was thrown.");
		}
		if (expectedThrowable != null) {
			throw new AssertionError("Expected "+ expectedThrowable.getSimpleName() +" to be thrown, but no exception was thrown.");
		}
	}

}
