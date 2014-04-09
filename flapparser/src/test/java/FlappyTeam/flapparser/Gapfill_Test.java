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
        assertEquals(Gapfill.parser("").size(), 0);
        assertEquals(Gapfill.parser("{").size(), 0);
        assertEquals(Gapfill.parser("{ ").size(), 0);
        assertEquals(Gapfill.parser("{ a").size(), 0);
        assertEquals(Gapfill.parser("{ a _").size(), 0);
        assertEquals(Gapfill.parser("{ a _}").size(), 0);
        assertEquals(Gapfill.parser("{ a }").size(), 0);

        assertEquals(Gapfill.parser("octopus {teeth_5}").size(), 1);
        assertEquals(Gapfill.parser("octopus {teeth _5}").size(), 1);
        assertEquals(Gapfill.parser("octopus { teeth _5}").size(), 1);
        assertEquals(Gapfill.parser("octopus { teeth _5 }").size(), 1);
        assertEquals(Gapfill.parser("octopus { teeth _5 } miranda { lime _8}").size(), 2);
        assertEquals(Gapfill.parser("octopus { teeth _5 } miranda { lime _2}").size(), 1);

        assertEquals(Gapfill.parser("octopus {teeth_5}").get(0), new Reponse (true, "teeth"));
        assertEquals(Gapfill.parser("octopus {teeth _5}").get(0), new Reponse (true, "teeth"));
        assertEquals(Gapfill.parser("octopus { teeth _5}").get(0), new Reponse (true, "teeth"));
        assertEquals(Gapfill.parser("octopus { teeth _5 }").get(0), new Reponse (true, "teeth"));
        assertEquals(Gapfill.parser("octopus { teeth _5 } miranda { lime _8}").get(0), new Reponse (true, "teeth"));
        assertEquals(Gapfill.parser("octopus { teeth _5 } miranda { lime _8}").get(1), new Reponse (true, "lime"));
        assertEquals(Gapfill.parser("octopus {teeth _5 } miranda { lime _2}").get(0), new Reponse (true, "teeth"));
    }
}
