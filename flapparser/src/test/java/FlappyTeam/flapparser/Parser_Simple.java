package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Damien
 *
 */
public class Parser_Simple {
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDoParseNull(){
		Simple simpleReponse = new Simple();
		assertEquals(simpleReponse.doParse(null), null);
	}
	
	@Test
	public void testDoParseGoodFormatSize1TrueFormat(){
		Simple simpleReponse = new Simple();
		simpleReponse.doParse("+ma reponse true.") ;
		java.util.ArrayList<Reponse> listReponse = simpleReponse.getListReponse();
		assertEquals(listReponse.size(),1 );
		assertEquals(listReponse.get(0).isValue(),true);
		assertEquals(listReponse.get(0).getLibele(),"ma reponse true");
		assertTrue(listReponse.get(0).equals(new Reponse(true, "ma reponse true")));
	}

	@Test
	public void testDoParseGoodFormatSize1FalseFormat(){
		Simple simpleReponse = new Simple();
		simpleReponse.doParse("-ma reponse false.") ;
		java.util.ArrayList<Reponse> listReponse = simpleReponse.getListReponse();
		assertEquals(listReponse.size(),1 );
		assertEquals(listReponse.get(0).isValue(),false);
		assertEquals(listReponse.get(0).getLibele(),"ma reponse false");
		assertTrue(listReponse.get(0).equals(new Reponse(false, "ma reponse false")));
	}
	
	@Test
	public void testDoParseWrongFormatSize1(){
		Simple simpleReponse = new Simple();
		simpleReponse.doParse("ma reponse .");
		java.util.ArrayList<Reponse> listReponse = simpleReponse.getListReponse();
		assertEquals(listReponse.size(),0);
		assertTrue( listReponse.isEmpty());
	}
	
	@Test
	public void testDoParseWrongFormatSizeN(){
		Simple simpleReponse = new Simple();
		simpleReponse.doParse("+ The correct answer.- Distractor1.  Distractor3.+   The cor   rect answer. - Distractor 8.") ;
		java.util.ArrayList<Reponse> listReponse = simpleReponse.getListReponse();
		assertEquals(listReponse.size(),0);
		assertTrue(listReponse.isEmpty());
		
	}
	@Test
	public void testSizeOfLibeleTrue(){
		Simple simpleReponse = new Simple();
		simpleReponse.doParse("+ma reponse 1        .") ;
		java.util.ArrayList<Reponse> listReponse = simpleReponse.getListReponse();
		assertEquals(listReponse.get(0).getLibele(),"ma reponse 1");
		
	}
	
	@Test
	public void testDoParseGoodFormatSize2(){
		Simple simpleReponse = new Simple();
		simpleReponse.doParse("-ma reponse 1.+ La reponse 2.") ;
		java.util.ArrayList<Reponse> listReponse = simpleReponse.getListReponse();
		assertEquals(listReponse.size(),2 );
		assertEquals(listReponse.get(0).isValue(),false);
		assertEquals(listReponse.get(0).getLibele(),"ma reponse 1");
		assertEquals(listReponse.get(1).isValue(),true);
		assertEquals(listReponse.get(1).getLibele(),"La reponse 2");
	}
	
	@Test
	public void testDoParseSizeN(){
		Simple simpleReponse = new Simple();
		simpleReponse.doParse("+ The correct answer.- Distractor1. - Distractor3.+   The cor   rect answer. - Distractor 8.") ;
		java.util.ArrayList<Reponse> listReponse = simpleReponse.getListReponse();
		assertEquals(listReponse.size(), 5);
		assertEquals(listReponse.get(0),new Reponse(true, "The correct answer"));
		assertEquals(listReponse.get(1),new Reponse(false, "Distractor1"));
		assertEquals(listReponse.get(2),new Reponse(false, "Distractor3"));
		assertEquals(listReponse.get(3),new Reponse(true, "The cor   rect answer"));
		assertEquals(listReponse.get(4),new Reponse(false, "Distractor 8"));
		
	}

}
