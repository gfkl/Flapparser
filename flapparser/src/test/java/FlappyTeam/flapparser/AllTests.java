package FlappyTeam.flapparser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Gapfill_Test.class, Parser_BooleanQuestion.class,
		Simple_Test.class, Parser_Test.class, Multiple_Test.class,
		ReponseTest.class })
public class AllTests {

}
