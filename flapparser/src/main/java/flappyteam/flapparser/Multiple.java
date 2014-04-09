package flappyteam.flapparser;

import java.util.ArrayList;

/**
 * Cette classe traite les questions de type Multiple-Choice.
 */

/**
 * @author Cédric
 *
 */
public class Multiple {


    /**
     * la chaine de reponses.
     */

    private String reponses;

    /**
     * liste des reponses correspondantes a la chaine de reponses.
     */
    private ArrayList<Reponse> listeReponses;

    /**
     * @param rep : chaine de reponses
     */

    public Multiple(final String rep) {

        reponses = rep;
        setListeReponses(new ArrayList<Reponse>());

    }

    /**
     * methode principal qui parcourt la chaine de reponses.
     */

    public final void parser() {
        int indexDebut = 1;
        int indexFin = 0;
        int i = 0;
        boolean value = false;

        for (char c : reponses.toCharArray()) {

            if (c == '+') {
                value = true;
                indexDebut = i + 2;
            } else if (c == '-') {
                value = false;
                indexDebut = i + 2;
            }

            if (c == '.') {
                indexFin = i;
                createReponse(reponses.substring(indexDebut, indexFin),
                        value);
            }

            i++;
        }

    }

    /**
     * @param reponse : le libele de la reponse a creer
     * @param value : la valeur de cette reponse
     */

     private void createReponse(final String reponse, final boolean value) {

        Reponse newReponse = new Reponse(value, reponse);

        listeReponses.add(newReponse);

     }

     /**
      * @return la liste des reponses
      */

    public final ArrayList<Reponse> getListeReponses() {
        return listeReponses;
    }

    /**
     * @param liste : la liste des reponses
     */

    public final void setListeReponses(
            final ArrayList<Reponse> liste) {
        this.listeReponses = liste;
    }

}

