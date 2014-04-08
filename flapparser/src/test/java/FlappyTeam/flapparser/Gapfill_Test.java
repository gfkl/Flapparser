package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.Test;

import flappyteam.flapparser.Gapfill;
import flappyteam.flapparser.Reponse;

public class Gapfill_Test {

	@Test
	public void test() {
		assertEquals(Gapfill.parser("octopus {teeth _5}").size(), 1);
		assertEquals(Gapfill.parser("octopus {teeth _5}").get(0), new Reponse (true, "teeth"));
	}

}
