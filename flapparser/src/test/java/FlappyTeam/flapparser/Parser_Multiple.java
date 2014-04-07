package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import flappyteam.flapparser.Parser;

public class Parser_Multiple {
	
	protected Parser p;

	@Before
	public void setUp() throws Exception {
		p = new Parser("{Selectionnez les langages dynamiques |type=\"[]\"} + Clojure. - Java. + Groovy. - Scala.");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
