package FlappyTeam.flapparser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Gapfill_Test.class, Parser_BooleanQuestion.class,
		Parser_Simple.class, Parser_Test.class, ParserMultiple.class,
		ReponseTest.class })
public class AllTests {

}
