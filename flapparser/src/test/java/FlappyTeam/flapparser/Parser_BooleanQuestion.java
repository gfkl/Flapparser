package FlappyTeam.flapparser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Parser_BooleanQuestion {
	protected Parser	p;

	@Before
	public void setUp() throws Exception {
		p = new Parser("");
	}

	@After
	public void tearDown() throws Exception {
	}

	
	public void	booleanQuestion() {
		p.setStrToParse("{Bulgaria and Rumania joined the European Union in 2007. | type=\"()\"} + TRUE. - FALSE.");
	}

}
