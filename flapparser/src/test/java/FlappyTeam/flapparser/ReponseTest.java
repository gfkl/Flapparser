package FlappyTeam.flapparser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ReponseTest {
	protected Reponse r;
	
	@Before
	public void setUp() throws Exception {
		r = new Reponse(true, "Je suis la reponse");
	}
	
	@Test
	public void valueIsTrue() {
		assertEquals(r.isValue(), true);
		assertEquals(r.getLibele(), "Je suis la reponse");
	}
	
	@Test
	public void valueIsFalse() {
		r.setValue(false);
		assertEquals(r.isValue(), false);
	}
	
	@Test
	public void valueIsNotEquals() {
		Reponse r2 = new Reponse(true, "Je suis la reponse");	;
		r2.setLibele("blabla");
		assertEquals(r.equals(r2), false);
	}
	
	@Test
	public void valueIsEquals() {
		Reponse r2 = r;
		assertEquals(r.equals(r2), true);
	}
}
