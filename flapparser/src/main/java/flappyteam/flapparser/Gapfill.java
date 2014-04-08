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
                    reponse += tableau[j];
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
                if (tailleReponse == reponse.length()) {
                    //pas d'erreur
                    liste.add(new Reponse(true, reponse));
                    i = j;
                }
            }
        }
        return liste;
    }
}

