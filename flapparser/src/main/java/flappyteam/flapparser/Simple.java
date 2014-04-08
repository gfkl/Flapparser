package FlappyTeam.flapparser;

import java.util.ArrayList;

/**
 * 
 * @author Damien
 *
 */
public class Simple {
	
	private String reponse ;
	private ArrayList<Reponse> listReponse ;
	
	public Simple(){
		reponse = null ;
		listReponse = new ArrayList<Reponse>();
	}
	
	/**
	 * 
	 * @param reponseAParser : string a parser 
	 * @return : une arraylist de reponse si le string a parser est bien forme
	 */
	public ArrayList<Reponse> doParse(String reponseAParser){
		if(reponseAParser==null){
			return null ;
		}
		reponse = reponseAParser ;
		if (reponse.matches("([\\s]*[\\+-][\\s\\w]+\\.)+")){
			while(!(reponse.isEmpty())){
				convertChaineToReponse(reponse.substring(0, reponse.indexOf(".")));
				reponse = reponse.substring(reponse.indexOf(".")+1);
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
	private void convertChaineToReponse(String chaine){
		if(chaine.contains("+")){
			String simpleReponse = chaine.substring(chaine.indexOf("+")+1) ;
			simpleReponse = supprimerEspaceDebutFinLigne(simpleReponse);
			listReponse.add(new Reponse(true,simpleReponse));
			
		}
		else{
			String simpleReponse = chaine.substring(chaine.indexOf("-")+1) ;
			simpleReponse = supprimerEspaceDebutFinLigne(simpleReponse);
			listReponse.add(new Reponse(false,simpleReponse));
			
		}
		
	}
	
	/**
	 * 
	 * @param chaine : chaine de caractere ou l'on veut supprimer les eventuels espaces en trop
	 * @return un string sans caractere d'espacement avant et apres intitule de la reponse
	 */
	private String supprimerEspaceDebutFinLigne(String chaine){
		while(chaine.substring(0, 1).equals(" ")){
			chaine = chaine.substring(1);
		}
		while(chaine.substring(chaine.length()-1).equals(" ")){
			chaine = chaine.substring(0, chaine.length()-2);
		}
		return chaine ;
	}
	
	
	public ArrayList<Reponse> getListReponse(){
		return listReponse ;
	}
}
