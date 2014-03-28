package FlappyTeam.flapparser;

public class Parser {
	private	Question	question;	
	private String 		questionType;
	private String		response;
	private	String		strToParse;

	public	Parser(String strToParse) {
		
		this.question = new Question();
		this.questionType = null;
		this.response = null;
		this.strToParse = strToParse;
	}
	
	public void setStrToParse(String strToParse) {
		this.strToParse = strToParse;
	}

	public Question getQuestion() {
		return question;
	}

	public String getQuestionType() {
		return questionType;
	}

	public String getResponse() {
		return response;
	}

	public String getStrToParse() {
		return strToParse;
	}

	/**
	 *@author Houpert
	 *
	 *@param parseStr, la chaine qui sera parser
	 *
	 *@return la chaine parser
	 **/
	public Question	doParser() {
		int value = 0, temp = 0;

		if(this.strToParse.indexOf("|") != -1 && this.strToParse.indexOf("{") != -1 && this.strToParse.indexOf("{") == 0 ){
			this.question.setQuestion(this.strToParse.substring(1, this.strToParse.indexOf("|")));
			value = this.strToParse.indexOf("|")+1;
		}else
			return null;
		if(this.strToParse.indexOf("}") != -1){
			temp = this.strToParse.indexOf("}");
			/*Si de type '}' on prend le 2nd '}'*/
			if(this.strToParse.charAt(temp) == this.strToParse.charAt(temp+2)){	
				this.questionType = this.strToParse.substring(value, this.strToParse.indexOf("}")+2);
				value = this.strToParse.indexOf("}")+3;
			}else{
				if(this.strToParse.lastIndexOf("}") >= value){
					this.questionType = this.strToParse.substring(value, this.strToParse.indexOf("}"));
					value = this.strToParse.indexOf("}")+1;
				}else
					return null;
			}
		}else
			return null;

		if(value < this.strToParse.length())
			this.response = this.strToParse.substring(value, this.strToParse.length());
		else
			return null;

		if(this.question.getQuestion().equals(null) || this.questionType.equals(null) || this.response.equals(null))
			return null;
		else 
			this.question = takeResponse(this.question, this.questionType, this.response);
		return this.question;
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
	private Question takeResponse(Question q, String typeStr, String rep) {
		if(typeStr.equals("type=\"()\"")){
			/*Expression pour differencier le type de question*/
			if(rep.matches("(\\s*[+-]\\s*(true|false|TRUE|FALSE).\\s*)*")){
				q.setType(TypeQuestion.bool);
				//q.setListeRep(BooleanQuestion(rep));
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
			q.setType(TypeQuestion.gapfill);
			//q.setListeRep(parserDax(rep));
			System.out.println("Parser Dax");
		}
		else
			return null;

		return q;
	}
}
