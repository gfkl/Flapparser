package FlappyTeam.flapparser;

/**@author Houpert*/
public class Parser {

    /***/private Question question;
    /***/private String questionTypeStr;
    /***/private String responseStr;
    /***/private String strToParse;

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
     * @return L'objet question*/
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
     *@return la chaine de question
     **/
    private int takeQuestion() {
        String resQ;
        if (this.strToParse.indexOf("|") != -1
                && this.strToParse.indexOf("{") == 0) {
            resQ = this.strToParse.substring(1, this.strToParse.indexOf("|"));
            this.question.setQuestion(resQ);
            return (this.strToParse.indexOf("|") + 1);
        } else {
            return -1;
        }
    }

    /**
     *@author Houpert
     *@param value ,l'emplacement séparateur question/type '|'
     *@return la chaine de type
     **/
    private int takeQuestionType(final int value) {
        int temp, temp2;

        if (this.strToParse.indexOf("}") != -1) {
            temp = this.strToParse.indexOf("}");
            temp2 = this.strToParse.indexOf("}", temp + 1);

            if (temp < temp2) {
                String str;
                for (int i = temp; i <= temp2; i++) {
                    str = Character.toString(this.strToParse.charAt(i));
                    if (!(str.matches("[\\s\\n\\t]|\\}|\""))) {
                        return -1;
                    }
                }
                this.questionTypeStr = this.strToParse.substring(value, temp2);
                return (temp2);
            } else if (this.strToParse.lastIndexOf("}") >= value) {
                temp = this.strToParse.indexOf("}");
                this.questionTypeStr = this.strToParse.substring(value, temp);
                return (this.strToParse.indexOf("}") + 1);
            } else {
                return -1;
            }
        } else {
            return -1;
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

    /**
     *@author Houpert
     **/
    public final void doParser() {
        int value = 0;

        value = takeQuestion();
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
        if (this.question.getQuestion() != null
                && this.questionTypeStr != null
                && this.responseStr != null) {
            getResponseByType();
        } else {
            setQuestion(null);
            return;
        }
    }


    /**
     *@author Houpert
     *@param typeStr ,permet de generer la bonne expression reguliere
     *@return l'expression régulière complete pour le type
     **/
    private String matchedExpr(final String typeStr) {
        String str = "[\\s\\n\\t]*";
        String matche = str + "(type" + str + "=" + str + "\"" + str;
        //\\s*(type=\"\\(\\)\")\\s*
        if (typeStr.equals("{}")) {
            matche += "\\{" + str + "\\}" + str + "\")" + str;
        } else if (typeStr.equals("[]")) {
            matche += "\\[" + str + "\\]" + str + "\")" + str;
        } else if (typeStr.equals("()")) {
            matche += "\\(" + str + "\\)" + str + "\")" + str;
        } else {
            return null;
        }
        return matche;
    }

    /**@author Houpert*/
    private void getResponseByType() {
        if (this.questionTypeStr.matches(matchedExpr("()"))) {
            /*Expression pour differencier le type de question*/
            String matcheBool = "(\\s*[+-]\\s*(true|false|TRUE|FALSE).\\s*)*";
            if (this.responseStr.matches(matcheBool)) {
                this.question.setType(TypeQuestion.bool);
                BooleanQuestion bq = new BooleanQuestion(this.responseStr);
                bq.parser();
                //this.question.setListeRep(BooleanQuestion(rep).);
            } else {
                this.question.setType(TypeQuestion.simple);
                //this.question.setListeRep(parserDamien(rep));
                System.out.println("Parser Damien");
            }
        } else if (this.questionTypeStr.matches(matchedExpr("[]"))) {
            this.question.setType(TypeQuestion.multiple);
            //this.question.setListeRep(parserCedric(rep));
            System.out.println("Parser Cedric");
        } else if (this.questionTypeStr.matches(matchedExpr("{}"))) {
            this.question.setType(TypeQuestion.gapfill);
            //this.question.setListeRep(Gapfill.parser(rep));
            System.out.println("Parser Dax");
        }
    }
}
