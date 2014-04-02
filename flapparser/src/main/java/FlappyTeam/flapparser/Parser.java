package FlappyTeam.flapparser;

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
        int value;
        if (this.strToParse.indexOf("{") == 0) {
            value = this.getStrToParse().indexOf("|");
                if (value != -1) {
                resQ = this.strToParse.substring(1, value);
                this.getQuestion().setQuestion(resQ);
                return (this.strToParse.indexOf("|") + 1);
            }
            return -1;
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
            } else if (this.strToParse.indexOf("}") >= value) {
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

    /**@author Houpert*/
    public final void doParser() {
        if (this.getStrToParse() != null) {
            int value = takeQuestion();
            System.out.println(value);
            if (value == -1) {
                setQuestion(null);
                return;
            }

            value = takeQuestionType(value);
            System.out.println(value);
            if (value == -1) {
                setQuestion(null);
                return;
            }
            value = takeReponseStr(value);
            System.out.println(value);
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
        //\\s*(type=\"\\(\\)\")\\s*
        if (typeStr.equals("{}")) {
            matche += "\\{" + str + "\\}" + str + "\")" + str;
        } else if (typeStr.equals("[]")) {
            matche += "\\[" + str + "\\]" + str + "\")" + str;
        } else if (typeStr.equals("()")) {
            matche += "\\(" + str + "\\)" + str + "\")" + str;
        }
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
                bq.parser();
                //this.question.setListeRep(BooleanQuestion(rep).);
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
            this.getQuestion().setType(TypeQuestion.undetermined);
            return;
        }
    }
}


