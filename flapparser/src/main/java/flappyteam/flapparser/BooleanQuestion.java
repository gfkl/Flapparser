package flappyteam.flapparser;

import java.util.ArrayList;

/**
 *
 * @author Guillaume
 *
 */
public class BooleanQuestion {
    /** */
    private String                reponses;
    /** */
    private ArrayList<Reponse>    reponseList;

    /**
     * @param repNew comment here
     */
    public BooleanQuestion(final String repNew) {
        this.reponses = repNew;
        reponseList = new ArrayList<Reponse>();
    }

    /**
     * @param index comment here
     * @param value comment here
     * @return comment here
     */
    private    int    createReponse(final int index, final boolean value) {
        Reponse newReponse;
        int     lastIndex;

        lastIndex = this.reponses.indexOf(' ', index);
        if (lastIndex > index) {
            newReponse = new Reponse(value,
                    this.reponses.substring(index, lastIndex));
        } else {
            newReponse = new Reponse(value, this.reponses.substring(index,
                    this.reponses.length()));
        }
        this.reponseList.add(newReponse);
        return 1;
    }

    /**@return la liste de reponse*/
    public final ArrayList<Reponse>    parser() {
        int    index = 0;
        for (char c : this.reponses.toCharArray()) {
            if (c == '+') {
                index += createReponse(index + 1, true);
            } else if (c == '-') {
                index += createReponse(index + 1, false);
            } else {
                index++;
            }
        }
        return this.reponseList;
    }

}


