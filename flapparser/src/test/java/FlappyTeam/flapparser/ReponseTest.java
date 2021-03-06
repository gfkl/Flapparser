package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import flappyteam.flapparser.Reponse;

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
		r.setValue(false);
		assertEquals(r.isValue(), false);
		r.setLibele("blabla");
		assertEquals(r.getLibele().equals("Je suis la reponse"), false);
		assertEquals(r.getLibele().equals("blabla"), true);
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
	
	@Test
	public void	equalsTest() {
		assertFalse(r.equals(r.getLibele()));
		assertFalse(r.equals(new Reponse(false, "Je ne suis pas la reponse")));
		assertFalse(r.equals(new Reponse(true, "Je ne suis pas la reponse")));
		assertFalse(r.equals(new Reponse(false, "Je suis la reponse")));
	}
	
	@Test
	public void	hashCodeTest() {
		assertEquals(r.hashCode(), r.getLibele().hashCode());
	}
}
