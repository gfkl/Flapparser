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
        //strParse = "{type=\"()\"}a";
        //strParse = "{";
        strParse = "{Blablabla |";
        //strParse = "{Blablabla |type=\"()\"}";
        //strParse = "{Blablabla |type=\"()\"}et la rep";
        Parser p = new Parser(strParse);
       // p.setStrToParse(null);
        p.doParser();
        System.out.println(p.getQuestion());
        if (p.getQuestion() != null) {
            System.out.println(p.getQuestion().getType());
        }
        System.out.println("Hello World!");
    }
}

