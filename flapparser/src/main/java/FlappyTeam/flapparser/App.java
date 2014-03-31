/***/
package FlappyTeam.flapparser;

/***
 *
 * @author Guillaume
 *
 */
public class App {
    /**@author Houpert
     * @param args*/
    public static void main(final String[] args) {
        String strParse;
        strParse = "{La Suisse est la suisse. |type=\"{    }\"   }blablabla";
        Parser p = new Parser(strParse);
        p.doParser();
        System.out.println(p.getQuestion());
        System.out.println("Hello World!");
    }
}

