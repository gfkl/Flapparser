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

    public Multiple(final String rep) {
    	
    	reponses = rep;
    	setListeReponses(new ArrayList<Reponse>());

    }
    
    public final void parser() {
        int indexDebut = 1;
        int indexFin = 0;
        int i = 0;
        boolean value = false;
        
        for (char c : reponses.toCharArray()) {
        	
        	if (c == '+') {
        		value = true;
        		indexDebut = i+2;
        	} else if (c == '-') {
        		value = false;
        		indexDebut = i+2;
        	}

        	if (c == '.') {
        		indexFin = i;
        		createReponse(reponses.substring(indexDebut, indexFin),value);
        	}
        	
        	i++;
        }

    }

	private void createReponse(String reponse, boolean value) {

		Reponse newReponse = new Reponse(value,reponse);
		
		listeReponses.add(newReponse);
		
	}

	public ArrayList<Reponse> getListeReponses() {
		return listeReponses;
	}

	public void setListeReponses(ArrayList<Reponse> listeReponses) {
		this.listeReponses = listeReponses;
	}

}

