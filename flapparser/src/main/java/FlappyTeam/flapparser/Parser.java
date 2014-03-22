package FlappyTeam.flapparser;
import javax.lang.model.element.TypeParameterElement;

public class Parser {

	public static void main(String[] args) {
		//String parse ="{type=\"{}\" }| il y a des reponses la";
		String parse ="{La Suisse est la suisse. |type=\"[]\"} + TRUE. - FALSE.";
		Question q;
		q = parser(parse);
		if(q == null)
			System.out.println("Erreur dans la chaine de charactere");
		else
			System.out.println("Chaine parser");
	}


	/**
	 *@author Houpert
	 *
	 *@param parseStr, la chaine qui sera parser
	 *
	 *@return la chaine parser
	 **/
	public static Question parser(String parseStr) {
		Question q = new Question();
		String typeStr = null, rep = null;
		int value = 0, temp = 0;

		if(parseStr.indexOf("|") != -1 && parseStr.indexOf("{") != -1 && parseStr.indexOf("{") == 0 ){
			q.setQuestion(parseStr.substring(1,parseStr.indexOf("|")));
			value = parseStr.indexOf("|")+1;
		}else
			return null;

		if(parseStr.indexOf("}") != -1){
			temp = parseStr.indexOf("}");

			/*Si de type '}' on prend le 2nd '}'*/
			if(parseStr.charAt(temp) == parseStr.charAt(temp+2)){	
				typeStr = parseStr.substring(value,parseStr.indexOf("}")+2);
				value = parseStr.indexOf("}")+3;
			}else{
				if(parseStr.lastIndexOf("}") >= value){
					typeStr = parseStr.substring(value,parseStr.indexOf("}"));
					value = parseStr.indexOf("}")+1;
				}else
					return null;
			}
		}else
			return null;

		if(value < parseStr.length())
			rep = parseStr.substring(value, parseStr.length());
		else
			return null;

		if(q.getQuestion().equals(null) || typeStr.equals(null) || rep.equals(null))
			return null;
		else 
			q = takeResponse(q, typeStr, rep);

		return q;
	}


	/**
	 *@author Houpert
	 *
	 *@param q
	 *@param typeStr, permet d'identifier le sous parseur à appeler
	 *@rep rep,	la liste de reponse données au sous parseur
	 *
	 *@return la question mis à jour
	 **/
	private static Question takeResponse(Question q, String typeStr, String rep) {
		if(typeStr.equals("type=\"()\"")){
			/*Expression pour differencier le type de question*/
			if(rep.matches("(\\s*[+-]\\s*(true|false|TRUE|FALSE).\\s*)*")){
				q.setType(TypeQuestion.questions);
				//q.setListeRep(parserGuillaume(rep));
				System.out.println("Parser Guillaume");
			}else{
				q.setType(TypeQuestion.simple);
				//q.setListeRep(parserDamien(rep));
				System.out.println("Parser Damien");
			}	
		}else if(typeStr.equals("type=\"[]\"")){
			q.setType(TypeQuestion.multiple);
			//q.setListeRep(parserCedric(rep));
			System.out.println("Parser Cedric");

		}else if(typeStr.equals("type=\"{}\"")){
			q.setType(TypeQuestion.gapfil);
			//q.setListeRep(parserDax(rep));
			System.out.println("Parser Dax");
		}
		else
			return null;

		return q;
	}
}
