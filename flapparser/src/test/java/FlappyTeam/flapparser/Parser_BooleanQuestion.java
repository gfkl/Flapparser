package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import FlappyTeam.flapparser.BooleanQuestion;
import FlappyTeam.flapparser.Reponse;

public class Parser_BooleanQuestion {
	protected BooleanQuestion	bq;

	@Before
	public void setUp() throws Exception {
		bq = new BooleanQuestion("+TRUE. -FALSE.");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void	testBooleanQuestion() {
		Reponse reponse1 = bq.parser().get(0);
		Reponse reponse2 = bq.parser().get(1);
		assertTrue(reponse1.equals(new Reponse(true, "TRUE.")));
		assertTrue(reponse2.equals(new Reponse(false, "FALSE.")));
	}

}
