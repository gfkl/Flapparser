package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import flappyteam.flapparser.Parser;
import flappyteam.flapparser.Reponse;
import flappyteam.flapparser.TypeQuestion;

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
    public void failStringSetAsNull() {
        p.setStrToParse(null);
        p.doParser();
        assertEquals(p.getQuestion(), null);
    }
    
    @Test
    public void failNoQuestion() {
        p.setStrToParse("{");
        p.doParser();
        assertEquals(p.getQuestion(), null);
    }
    
    @Test
    public void failNoType() {
        p.setStrToParse("{Blablabla |");
        p.doParser();
        assertEquals(p.getQuestion(), null);
    }
    
    @Test
    public void failNoRep() {
        p.setStrToParse("{Blablabla |type=\"()\"}");
        p.doParser();
        assertEquals(p.getQuestion(), null);
    }
    
    @Test
    public void good() {
        p.setStrToParse("{Blablabla |type=\"()\"}et la rep");
        p.doParser();
        assertEquals(p.getQuestion().getType(), TypeQuestion.simple);
    }
    

    
    @Test
    public void failString() {
        p.setStrToParse("");
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test
    public void failString_1() {
        p.setStrToParse("{La Sui");
        p.doParser();
        assertNull(p.getQuestion());
    }
    @Test
    public void failString_2() {
        p.setStrToParse("{La Suisse est membre de l'Union Europenne. |");
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test
    public void failString_3() {
        p.setStrToParse("{La Suisse est membre de l'Union Europenne. | il y a des reponses la");
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test
    public void failString_4() {
        p.setStrToParse("{type=\"()\" | il y a des reponses la");
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test
    public void failString_5() {
        p.setStrToParse("{type=\"{}\" | il y a des reponses la");
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test
    public void failString_6() {
        p.setStrToParse("{Blablabla | type=\"{}\" il y a des reponses la");
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test 
    public void failString_7(){
        String valideStr = "La Suisse est la suisse. |type=\"()\"}blablabla" ;
        p.setStrToParse(valideStr);
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test
    public void failString_8() {
        p.setStrToParse("Blablabla |{type=\"()\"}et la rep");
        p.doParser();
        assertNull(p.getQuestion());
    }

    @Test
    public void failString_9() {
        p.setStrToParse("{Blablabla |type=\"()\"}et la rep}");
        p.doParser();
        assertNull(p.getQuestion());
    }

    @Test
    public void failString_10() {
        p.setStrToParse("{Bla |blabla |type=\"()\"}et la rep");
        p.doParser();
        assertNull(p.getQuestion());
    }

    @Test
    public void failString_11() {
        p.setStrToParse("{Blablabla| }et la rep");
        p.doParser();
        assertNull(p.getQuestion());
    }
    
    @Test
    public    void    StringWithTabAndEnterChar() {
        String    valideStr = "\n \t{La Suisse est la suisse. \n|\ttype=\"()\"} +TRUE. -FALSE.";
        p.setStrToParse(valideStr);
        p.doParser();
        assertEquals(p.getQuestion().getType(), TypeQuestion.bool);
        assertEquals(p.getQuestion().getQuestion(), "La Suisse est la suisse. \n");
        assertTrue(p.getQuestion().getListeRep().get(0).equals(new Reponse(true, "TRUE.")));
        assertTrue(p.getQuestion().getListeRep().get(1).equals(new Reponse(false, "FALSE.")));
    }
    
    @Test
    public void getStrToParse(){
        String valideStr = "{La Suisse est membre de l'Union Europenne. |type=\"{}\"}blablabla" ;
        p.setStrToParse(valideStr);
        p.doParser();
        assertEquals(p.getStrToParse(), valideStr);
    }
    
    @Test
    public void getStrToParse1(){
        String valideStr = "{La Suisse est membre de l'Union Europenne. |type=\"()\"}a" ;
        p.setStrToParse(valideStr);
        p.doParser();
        assertEquals(p.getQuestion().getQuestion(), "La Suisse est membre de l'Union Europenne. ");
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
        String valideStr = "{La Suisse est la suisse. |type=\"()\"} +blablabla. -bla. -bla." ;
        p.setStrToParse(valideStr);
        p.doParser();
        assertEquals(p.getQuestion().getType(), TypeQuestion.simple);
        assertEquals(p.getQuestion().getQuestion(), "La Suisse est la suisse. ");
        assertTrue(p.getQuestion().getListeRep().get(0).equals(new Reponse(true, "blablabla")));
        assertTrue(p.getQuestion().getListeRep().get(1).equals(new Reponse(false, "bla")));
        assertTrue(p.getQuestion().getListeRep().get(2).equals(new Reponse(false, "bla")));
    }
    
    @Test 
     public void valideBooleanQuestionString(){
        String valideStr = "{La Suisse est la suisse. |type=\"()\"} +TRUE. -FALSE." ;
        p.setStrToParse(valideStr);
        p.doParser();
        assertEquals(p.getQuestion().getType(), TypeQuestion.bool);
        assertEquals(p.getQuestion().getQuestion(), "La Suisse est la suisse. ");
        assertTrue(p.getQuestion().getListeRep().get(0).equals(new Reponse(true, "TRUE.")));
        assertTrue(p.getQuestion().getListeRep().get(1).equals(new Reponse(false, "FALSE.")));
    }

    
    @Test 
     public void valideMultipleString(){
        String valideStr = "{La Suisse est la suisse. |type=\"[]\"} + Clojure. - Java. + Groovy. - Scala." ;
        p.setStrToParse(valideStr);
        p.doParser();
        assertEquals(p.getQuestion().getType(), TypeQuestion.multiple);
        assertEquals(p.getQuestion().getQuestion(), "La Suisse est la suisse. ");
        assertTrue(p.getQuestion().getListeRep().get(0).equals(new Reponse(true, "Clojure")));
        assertTrue(p.getQuestion().getListeRep().get(1).equals(new Reponse(false, "Java")));
        assertTrue(p.getQuestion().getListeRep().get(2).equals(new Reponse(true, "Groovy")));
        assertTrue(p.getQuestion().getListeRep().get(3).equals(new Reponse(false, "Scala")));
    }
    
    @Test 
     public void invalideTypeString(){
        String valideStr = "{La Suisse est la suisse. |type=\"[@\"} +TRUE. -FALSE." ;
        p.setStrToParse(valideStr);
        p.doParser();
        assertEquals(p.getQuestion().getType(), TypeQuestion.undetermined);
    }
}
