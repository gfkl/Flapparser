
package flappyteam.flapparser;

/***
 *
 * @author Guillaume
 *
 */
public final class App {

    /**
     *
     */
    private App() {
    }

    /**
     *
     */
    public static void main() {
        String strParse;
        strParse = "{Blablabla |";
        Parser p = new Parser(strParse);
        p.doParser();
        System.out.println(p.getQuestion());
        if (p.getQuestion() != null) {
            System.out.println(p.getQuestion().getType());
        }
        System.out.println("Hello World!");
    }
}

