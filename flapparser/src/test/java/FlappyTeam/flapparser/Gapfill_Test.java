package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.Test;

import flappyteam.flapparser.Gapfill;
import flappyteam.flapparser.Reponse;

public class Gapfill_Test {

	@Test
	public void testConstructor(){
		Gapfill g = new Gapfill();
		assertNotNull(g);
	}
	
	@Test
	public void testParser() {
		assertEquals(Gapfill.parser("octopus {teeth _5}").size(), 1);
		assertEquals(Gapfill.parser("octopus {teeth _5}").get(0), new Reponse (true, "teeth"));
		assertEquals(Gapfill.parser("octopus {teeth_5}").get(0), new Reponse (true, "teeth"));
	}

}
