package FlappyTeam.flapparser;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		String strToParse ="{La Suisse est la suisse. |type=\"()\"}blablabla";
		Parser p = new Parser(strToParse);
		p.doParser();
		/*System.out.println(p.getQuestion());*/
		System.out.println( "Hello World!" );
	}
}
