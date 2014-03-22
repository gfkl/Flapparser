package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Parser_Test {
	protected Parser p;
	
	@Before
	public void setUp() throws Exception {
		p = new Parser();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void failString_1() {
		assertEquals( p.parser("{La Sui"), null);
	}
	@Test
	public void failString_2() {
		assertEquals( p.parser("{La Suisse est membre de l'Union Europenne. |"), null);
	}
	
	@Test
	public void failString_3() {
		assertEquals( p.parser("{La Suisse est membre de l'Union Europenne. | il y a des reponses la"), null);
	}
	
	@Test
	public void failString_4() {
		assertEquals( p.parser("{type=\"()\" | il y a des reponses la"), null);
	}
	
	@Test
	public void failString_5() {
		assertEquals( p.parser("{type=\"{}\" | il y a des reponses la"), null);
	}
	
	@Test 
	 public void goodStringGapfil(){
		// given a good string
		String strGood = "{La Suisse est membre de l'Union Europenne. |type=\"{}\"}blablabla" ;
		// when parsing the string 
		Question quest = p.parser(strGood);
		// then Question.getType() = TypeQuestion.gapfil
		assertEquals(quest.getType(), TypeQuestion.gapfil);
		// then Question.getQuestion() = La Suisse est membre de l'Union Europenne. 
		assertEquals(quest.getQuestion(), "La Suisse est membre de l'Union Europenne. ");
	}
	
	@Test 
	 public void goodStringSimple(){
		// given a good string
		String strGood = "{La Suisse est la suisse. |type=\"()\"}blablabla" ;
		// when parsing the string 
		Question quest = p.parser(strGood);
		// then Question.getType() = TypeQuestion.simple
		assertEquals(quest.getType(), TypeQuestion.simple);
		// then Question.getQuestion() = La Suisse est la suisse. 
		assertEquals(quest.getQuestion(), "La Suisse est la suisse. ");
	}
	
	@Test 
	 public void goodStringQuestion(){
		// given a good string
		String strGood = "{La Suisse est la suisse. |type=\"()\"} +TRUE. -FALSE." ;
		// when parsing the string 
		Question quest = p.parser(strGood);
		// then Question.getType() = TypeQuestion.bool
		assertEquals(quest.getType(), TypeQuestion.bool);
		// then Question.getQuestion() = La Suisse est la suisse. 
		assertEquals(quest.getQuestion(), "La Suisse est la suisse. ");
	}

	
	@Test 
	 public void goodStringMultiple(){
		// given a good string
		String strGood = "{La Suisse est la suisse. |type=\"[]\"} +TRUE. -FALSE." ;
		// when parsing the string 
		Question quest = p.parser(strGood);
		// then Question.getType() = TypeQuestion.multiple
		assertEquals(quest.getType(), TypeQuestion.multiple);
		// then Question.getQuestion() = La Suisse est la suisse. 
		assertEquals(quest.getQuestion(), "La Suisse est la suisse. ");
	}
}
