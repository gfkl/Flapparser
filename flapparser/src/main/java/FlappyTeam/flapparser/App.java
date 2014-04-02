
package FlappyTeam.flapparser;

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
     * @param args
     */
    public static void main(final String[] args) {
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

