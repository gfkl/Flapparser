package flappyteam.flapparser;

/**
 *
 * @author Guillaume
 *
 */
public class Parser {
    /** */
    private Question question;
    /** */
    private String questionTypeStr;
    /** */
    private String responseStr;
    /** */
    private String strToParse;

    /**@author Houpert
     * @param strToParseNew ,la chaine de charactère a parser*/
    public Parser(final String strToParseNew) {
        this.question = new Question();
        this.questionTypeStr = null;
        this.responseStr = null;
        this.strToParse = strToParseNew;
    }

    /**@author Houpert
     * @param strToParseNew ,new chaine a parser*/
    public final void setStrToParse(final String strToParseNew) {
        this.strToParse = strToParseNew;
    }

    /**@author Houpert
     * @param questionNew ,nouvelleValeur de la question*/
    public final void setQuestion(final Question questionNew) {
        this.question = questionNew;
    }

    /**@author Houpert
     * @return la question*/
    public final Question getQuestion() {
        return this.question;
    }

    /**@author Houpert
     * @return La chaine a parser*/
    public final String getStrToParse() {
        return this.strToParse;
    }

    /**
     *@author Houpert
     *@return l'index de debut du type
     **/
    private int takeQuestion() {
        String resQ;
        int nbChar1 = 0, nbChar2 = 0, nbChar3 = 0, posChar1, posChar2;
        for (int i = 0; i < strToParse.length(); i++) {
        	if (strToParse.toCharArray()[i] == '{')
        		nbChar1++;
        	if (strToParse.toCharArray()[i] == '}')
        		nbChar2++;
        	if (strToParse.toCharArray()[i] == '|')
        		nbChar3++;
        }
        /** Erreur à traiter au propre */
        if (nbChar1 == 0 || nbChar2 == 0 || 
        		nbChar1 != nbChar2 || nbChar3 != 1)
        	return -1;
    	posChar1 = this.getStrToParse().indexOf("{");
    	posChar2 = this.getStrToParse().indexOf("|");
        if (posChar2 < posChar1 + 1)
        	return -1;
        resQ = this.strToParse.substring(posChar1 + 1, posChar2);
        this.getQuestion().setQuestion(resQ);
        return (this.strToParse.indexOf("|") + 1);
    }

    /**
     *@author Houpert
     *@param value ,l'emplacement séparateur question/type '|'
     *@return la chaine de type
     **/
    private int takeQuestionType(final int value) {
        int temp, temp2;

        temp = this.strToParse.indexOf("}");
        temp2 = this.strToParse.indexOf("}", temp + 1);

        
        if (strToParse.indexOf("type") == -1)
        	return -1;
        if (temp < temp2) {
            this.questionTypeStr = this.strToParse.substring(value, temp2);
            return (temp2);
        } else {
            this.questionTypeStr = this.strToParse.substring(value, temp);
            return (this.strToParse.indexOf("}") + 1);
        }
    }

    /**
     *@author Houpert
     *
     *@param value ,l'emplacement séparateur question-type/reponse '}'
     *
     *@return la chaine de reponse
     **/
    private int takeReponseStr(final int value) {
        String tmpParse;
        int sizeStr = this.strToParse.length();
        if (value < this.strToParse.length()) {
            tmpParse = this.strToParse.substring(value, sizeStr);
            this.responseStr = tmpParse;
            return 0;
        }
        return -1;
    }

    /**@author Houpert*/
    public final void doParser() {
        if (this.getStrToParse() != null) {
            int value = takeQuestion();
            if (value == -1) {
                setQuestion(null);
                return;
            }
            value = takeQuestionType(value);
            if (value == -1) {
                setQuestion(null);
                return;
            }
            value = takeReponseStr(value);
            if (value == -1) {
                setQuestion(null);
                return;
            }
            getResponseByType();
            return;
        }
        setQuestion(null);
    }


    /**
     *@author Houpert
     *@param typeStr ,permet de generer la bonne expression reguliere
     *@return l'expression régulière complete pour le type
     **/
    private String matchedExpr(final String typeStr) {
        String str = "[\\s\\n\\t]*";
        String matche = str + "(type" + str + "=" + str + "\"" + str;

        if (typeStr.equals("{}"))
            matche += "\\{" + str + "\\}" + str + "\")" + str;
        if (typeStr.equals("[]"))
            matche += "\\[" + str + "\\]" + str + "\")" + str;
        if (typeStr.equals("()"))
            matche += "\\(" + str + "\\)" + str + "\")" + str;
        return matche;
    }

    /**@author Houpert*/
    private void getResponseByType() {
        if (this.questionTypeStr.matches(matchedExpr("()"))) {
            /*Expression pour differencier le type de question*/
            String matcheBool = "(\\s*[+-]\\s*(true|false|TRUE|FALSE).\\s*)*";
            if (this.responseStr.matches(matcheBool)) {
                this.getQuestion().setType(TypeQuestion.bool);
                BooleanQuestion bq = new BooleanQuestion(this.responseStr);
                this.question.setListeRep(bq.parser());
            } else {
                this.getQuestion().setType(TypeQuestion.simple);
                //this.question.setListeRep(parserDamien(rep));
                System.out.println("Parser Damien");
            }
        } else if (this.questionTypeStr.matches(matchedExpr("[]"))) {
            this.getQuestion().setType(TypeQuestion.multiple);
            //this.question.setListeRep(parserCedric(rep));
            System.out.println("Parser Cedric");
        } else if (this.questionTypeStr.matches(matchedExpr("{}"))) {
            this.getQuestion().setType(TypeQuestion.gapfill);
            //this.question.setListeRep(Gapfill.parser(rep));
            System.out.println("Parser Dax");
        } else {
            return;
        }
    }
}


