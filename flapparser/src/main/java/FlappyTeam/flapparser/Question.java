package FlappyTeam.flapparser;

import java.util.ArrayList;

/***/
public class Question {
    /***/private String question;
    /***/private TypeQuestion type;
    /***/private ArrayList<Reponse> listeRep;

    /**@param questionNew la question
     * @param typeNew le type */
    public Question(final String questionNew, final TypeQuestion typeNew) {
        super();
        this.question = questionNew;
        this.type = typeNew;
        this.listeRep = new ArrayList<Reponse>();
    }

    /***/
    public Question() {
        super();
        this.question = "";
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
