package FlappyTeam.flapparser;

/***
 * 
 * @author Guillaume
 *
 */
public class App {
	
	public App() {
		
	}
	
    public static void main(final String[] args) {
        String strToParse = "{La Suisse est la suisse. |type=\"{    }\"    }blablabla";
        Parser p = new Parser(strToParse);
        p.doParser();
        System.out.println(p.getQuestion());
        System.out.println("Hello World!");
    }
}

