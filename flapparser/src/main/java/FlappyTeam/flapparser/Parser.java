package FlappyTeam.flapparser;

public class Parser {
	private    Question      question;
	private    String        questionTypeStr;
	private    String        responseStr;
	private    String        strToParse;

	public    Parser(String strToParse) {
		this.question = new Question();
		this.questionTypeStr = null;
		this.responseStr = null;
		this.strToParse = strToParse;
	}

	public void setStrToParse(String strToParse) {
		this.strToParse = strToParse;
	}

	public Question getQuestion() {
		return question;
	}

	public String getStrToParse() {
		return strToParse;
	}


	/**
	 *@author Houpert
	 *
	 *@return la chaine de question
	 **/
	private int takeQuestion() {
		if (this.strToParse.indexOf("|") != -1 && this.strToParse.indexOf("{") == 0 ) {
			this.question.setQuestion(this.strToParse.substring(1, this.strToParse.indexOf("|")));
			return (this.strToParse.indexOf("|")+1);
		} else 
			return -1;
	}

	/**
	 *@author Houpert
	 *
	 *@param value, l'emplacement séparateur question/type '|'
	 *
	 *@return la chaine de type
	 **/
	private int takeQuestionType(int value) {
		int temp, temp2;

		if (this.strToParse.indexOf("}") != -1) {
			temp = this.strToParse.indexOf("}");
			if(this.strToParse.indexOf("}", temp+1) != -1);
			temp2 = this.strToParse.indexOf("}", temp+1);

			if (temp < temp2) {
				for(int i = temp; i <= temp2;i++){
					if(!(Character.toString(this.strToParse.charAt(i)).matches("[\\s\\n\\t]|\\}|\"")))
						return -1;
				}
				this.questionTypeStr = this.strToParse.substring(value, temp2);	
				return (temp2);
			} 
			else if (this.strToParse.lastIndexOf("}") >= value) {
				this.questionTypeStr = this.strToParse.substring(value, this.strToParse.indexOf("}"));
				return (this.strToParse.indexOf("}")+1);
			} else 
				return -1;
		} else 
			return -1;

	}

	/**
	 *@author Houpert
	 *
	 *@param value, l'emplacement séparateur question-type/reponse '}'
	 *
	 *@return la chaine de reponse
	 **/
	private int takeReponseStr(int value) {
		if (value < this.strToParse.length()) {
			this.responseStr = this.strToParse.substring(value, this.strToParse.length());
			return 0;
		}
		return -1;
	}

	/**
	 *@author Houpert
	 *
	 *@param parseStr, la chaine qui sera parser
	 *
	 *@return la chaine parser
	 **/
	public void    doParser() {
		int value = 0;

		if((value = takeQuestion()) == -1)
			return;

		if((value = takeQuestionType(value)) == -1)
			return ;

		if((value = takeReponseStr(value)) == -1)
			return ;

		if (this.question.getQuestion() != null && this.questionTypeStr != null
				&& this.responseStr != null) {
			getResponseByType();
		}else return;
	}

	
	/**
	 *@author Houpert
	 *
	 *@param typeStr, permet de generer la bonne expression reguliere
	 *
	 *@return l'expression régulière complete pour le type
	 **/
	private String matchedExpr(String typeStr){
		String matche;
		String str ="[\\s\\n\\t]*";
		//\\s*(type=\"\\(\\)\")\\s*
		if(typeStr.equals("{}"))
			matche = str+"(type"+str+"="+str+"\""+str+"\\{"+str+"\\}"+str+"\")"+str;
		else if(typeStr.equals("[]"))
			matche = str+"(type"+str+"="+str+"\""+str+"\\["+str+"\\]"+str+"\")"+str;
		else if(typeStr.equals("()"))
			matche = str+"(type"+str+"="+str+"\""+str+"\\("+str+"\\)"+str+"\")"+str;
		else 
			return null;
		return matche;
	}

	/**
	 *@author Houpert
	 *
	 *@param q
	 *@param typeStr, permet d'identifier le sous parseur à appeler
	 *@param rep,    la liste de reponse données au sous parseur
	 *
	 *@return la question mis à jour
	 **/
	private void getResponseByType() {
		if (this.questionTypeStr.matches(matchedExpr("()"))) {
			/*Expression pour differencier le type de question*/
			if (this.responseStr.matches("(\\s*[+-]\\s*(true|false|TRUE|FALSE).\\s*)*")) {
				this.question.setType(TypeQuestion.bool);
				BooleanQuestion bq = new BooleanQuestion(this.responseStr);
				bq.parser();
				//                this.question.setListeRep(BooleanQuestion(rep).);
			} else {
				this.question.setType(TypeQuestion.simple);
				//this.question.setListeRep(parserDamien(rep));
				System.out.println("Parser Damien");
			}    
		} else if (this.questionTypeStr.matches(matchedExpr("[]"))) {
			this.question.setType(TypeQuestion.multiple);
			//this.question.setListeRep(parserCedric(rep));
			System.out.println("Parser Cedric");
		}//Si seulement celui qui a fait getResponseByType avait donné un sens à rep
		else if (this.questionTypeStr.matches(matchedExpr("{}"))) {
			this.question.setType(TypeQuestion.gapfill);
			//this.question.setListeRep(Gapfill.parser(rep));
			System.out.println("Parser Dax");
		}
	}
}
