package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import FlappyTeam.flapparser.Parser;
import FlappyTeam.flapparser.TypeQuestion;

public class Parser_Test {
	protected Parser p;
	
	@Before
	public void setUp() throws Exception {
		p = new Parser(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void failString_1() {
		p.setStrToParse("{La Sui");
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.undetermined);
	}
	@Test
	public void failString_2() {
		p.setStrToParse("{La Suisse est membre de l'Union Europenne. |");
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.undetermined);
	}
	
	@Test
	public void failString_3() {
		p.setStrToParse("{La Suisse est membre de l'Union Europenne. | il y a des reponses la");
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.undetermined);
	}
	
	@Test
	public void failString_4() {
		p.setStrToParse("{type=\"()\" | il y a des reponses la");
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.undetermined);
	}
	
	@Test
	public void failString_5() {
		p.setStrToParse("{type=\"{}\" | il y a des reponses la");
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.undetermined);
	}
	
	@Test
	public void failString_6() {
		p.setStrToParse("{Blablabla | type=\"{}\" il y a des reponses la");
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.undetermined);
	}
	
	@Test 
	 public void valideGapfillString(){
		String valideStr = "{La Suisse est membre de l'Union Europenne. |type=\"{}\"}blablabla" ;
		p.setStrToParse(valideStr);
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.gapfill);
		assertEquals(p.getQuestion().getQuestion(), "La Suisse est membre de l'Union Europenne. ");
	}
	
	@Test 
	 public void valideSimpleString(){
		String valideStr = "{La Suisse est la suisse. |type=\"()\"}blablabla" ;
		p.setStrToParse(valideStr);
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.simple);
		assertEquals(p.getQuestion().getQuestion(), "La Suisse est la suisse. ");	
	}
	
	@Test 
	 public void valideBooleanQuestionString(){
		String valideStr = "{La Suisse est la suisse. |type=\"()\"} +TRUE. -FALSE." ;
		p.setStrToParse(valideStr);
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.bool);
		assertEquals(p.getQuestion().getQuestion(), "La Suisse est la suisse. ");
	}

	
	@Test 
	 public void valideMultipleString(){
		String valideStr = "{La Suisse est la suisse. |type=\"[]\"} +TRUE. -FALSE." ;
		p.setStrToParse(valideStr);
		p.doParser();
		assertEquals(p.getQuestion().getType(), TypeQuestion.multiple);
		assertEquals(p.getQuestion().getQuestion(), "La Suisse est la suisse. ");
	}
}
