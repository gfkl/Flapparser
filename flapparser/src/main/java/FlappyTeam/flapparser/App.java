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
        strParse = "{La Suisse est membre de l'Union Europenne. |type=\"()\"}a";
       // strParse = "{Blablabla | type=\"{}\" il y a des reponses la";
        Parser p = new Parser(strParse);
        p.doParser();
        System.out.println(p.getQuestion());
        if (p.getQuestion() != null)
            System.out.println(p.getQuestion().getType());
        System.out.println("Hello World!");
    }
}

