package flappyteam.flapparser;

import java.util.ArrayList;

/**
 *
 * @author Damien
 *
 */
public class Simple {

    /** */
    private String reponse;
    /** */
    private ArrayList<Reponse> listReponse;

    /** */
    public Simple() {
        reponse = null;
        listReponse = new ArrayList<Reponse>();
    }

    /**
     *
     * @param reponseAParser : string a parser
     * @return : une arraylist de reponse si le string a parser est bien forme
     */
    public final ArrayList<Reponse> parser(final String reponseAParser) {
        if (reponseAParser == null) {
            return null;
        }
        reponse = reponseAParser;
        if (reponse.matches("([\\s]*[\\+-][\\s\\w]+\\.)+")) {
            while (!(reponse.isEmpty())) {
                convertChaineToReponse(
                        reponse.substring(0, reponse.indexOf(".")));
                reponse = reponse.substring(reponse.indexOf(".") + 1);
            }
            return listReponse;
        }
        return null;
    }

    /**
     *
     * @param chaine : contient un string contenant une reponse a transformer
     * ajoute la reponse a notre arrayList
     */
    private void convertChaineToReponse(final String chaine) {
        if (chaine.contains("+")) {
            String simpleReponse = chaine.substring(chaine.indexOf("+") + 1);
            simpleReponse = supprimerEspaceDebutFinLigne(simpleReponse);
            listReponse.add(new Reponse(true, simpleReponse));
            } else {
            String simpleReponse = chaine.substring(chaine.indexOf("-") + 1);
            simpleReponse = supprimerEspaceDebutFinLigne(simpleReponse);
            listReponse.add(new Reponse(false, simpleReponse));
        }
    }

    /**
     *
     * @param chaine : chaine de caractere avec espaces en trop
     * @return string sans caractere d'espacement avant et apres intitule
     */
    private String supprimerEspaceDebutFinLigne(final String chaine) {
        String str = chaine;
        while (str.substring(0, 1).equals(" ")) {
            str = str.substring(1);
        }
        while (str.substring(str.length() - 1).equals(" ")) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    /**
     * @return listReponse : ArrayList des objets Reponse a la question
     */
    public final ArrayList<Reponse> getListReponse() {
        return listReponse;
    }
}
