package flappyteam.flapparser;

import java.util.ArrayList;

/** */
public final class Gapfill {

    /**
     * Just something useless for useless rules
     */
    public Gapfill() {
    }

    /**
     * @param response the response partially completed
     * @param responsePart a char we need to add to the response
     * @return the augmented response
     */
    private static final String createReponse(String response, char responsePart){
        return response + responsePart;
    }
    
    /**
     * Only used to parse the size of a response
     */
    public static final int DIX = 10;

    /**
     * @param chaine : the string to parse without the head
     * @return The list of good response (because the string to parse only contains those)
     */
    public static ArrayList<Reponse> parser(final String chaine) {
        int taille = chaine.length();
        char[] tableau = chaine.toCharArray();

        ArrayList<Reponse> liste = new ArrayList<Reponse>();

        int i, j;

        for (i = 0; i < taille; i++) {
            if (tableau[i] == '{') {
                String reponse = "";
                int tailleReponse = 0;
                for (j = (i + 1); j < taille; j++) {
                    if (tableau[j] != ' ') {
                        break;
                    }
                }
                for (; j < taille; j++) {
                    if (tableau[j] == ' ' || tableau[j] == '_') {
                        break;
                    }
                    reponse = createReponse(reponse, tableau[j]);
                }
                for (j++; j < taille; j++) {
                    if (tableau[j] == '}') {
                        break;
                    }
                    if (tableau[j] >= '0' && tableau[j] <= '9') {
                        tailleReponse *= DIX;
                        tailleReponse += (tableau[j] - '0');
                    }
                }
                if (tailleReponse >= reponse.length() && reponse != "") {
                    //pas d'erreur
                    liste.add(new Reponse(true, reponse));
                    i = j;
                }
            }
        }
        return liste;
    }
}

