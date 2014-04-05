package flappyteam.flapparser;

import java.util.ArrayList;

/**
 *
 * @author Guillaume
 *
 */
public class Question {
    /** */
    private String question;
    /** */
    private TypeQuestion type;
    /** */
    private ArrayList<Reponse> listeRep;

    /**
     * Constructeur
     */
    public Question() {
        super();
        this.question = null;
        this.type = TypeQuestion.undetermined;
        this.listeRep = new ArrayList<Reponse>();
    }

    /**@return la question*/
    public final String getQuestion() {
        return question;
    }

    /**@param questionNew update de la question*/
    public final void setQuestion(final String questionNew) {
        this.question = questionNew;
    }

    /**@return le type*/
    public final TypeQuestion getType() {
        return type;
    }

    /**@param typeNew update type question*/
    public final void setType(final TypeQuestion typeNew) {
        this.type = typeNew;
    }

    /**@return la liste de reponse*/
    public final ArrayList<Reponse> getListeRep() {
        return listeRep;
    }
    /**@param listeRepNew update liste de reponse*/
    public final void setListeRep(final ArrayList<Reponse> listeRepNew) {
        this.listeRep = listeRepNew;
    }
}

