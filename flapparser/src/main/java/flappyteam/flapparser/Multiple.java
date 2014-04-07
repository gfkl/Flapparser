package flappyteam.flapparser;

import java.util.ArrayList;

/** 
 * Cette classe traite les questions de type Multiple-Choice 
 */
public class Multiple {

	/**
    * @param reponses : les reponses de la question 
    * @param listeReponses : liste des reponses
    */

	private String reponses;
	private ArrayList<Reponse> listeReponses;

    Multiple(final String rep) {
    	
    	reponses = rep;
    	listeReponses = new ArrayList<Reponse>();

    }
    
    public final void parser() {
        int indexDebut = 1;
        int indexFin;
        String tabChar[] = reponses.split(".");
        String reponse;
        
        for (int i = 0 ; i < tabChar[i].length() ; i++) {
        	if (tabChar[i].charAt(0) == '+') {
        		indexFin = tabChar[i].length();
        		reponse = tabChar[i].substring(indexDebut, indexFin);
        		createReponse(reponse, true);
        	} else {
        		indexFin = tabChar[i].length();
        		reponse = tabChar[i].substring(indexDebut, indexFin);
        		createReponse(reponse, false);        	}
        	
        }

    }

	private void createReponse(String reponse, boolean value) {

		Reponse newReponse = new Reponse(value,reponse);
		
		listeReponses.add(newReponse);
		
	}

}

