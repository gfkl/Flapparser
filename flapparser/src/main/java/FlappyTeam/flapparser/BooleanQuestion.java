package FlappyTeam.flapparser;

import java.util.ArrayList;

public class BooleanQuestion {
    
	private String				reponses;
	private ArrayList<Reponse>	reponseList;
	
    public BooleanQuestion(String rep) {
    	this.reponses = rep;
    	reponseList = new ArrayList<Reponse>();
    }
    
	private	int	createReponse(int index, boolean value) {
    	Reponse newReponse;
    	int 	lastIndex;

    	lastIndex = this.reponses.indexOf(' ', index);
    	if (lastIndex > index)
    		newReponse = new Reponse(value, this.reponses.substring(index, lastIndex));    	
    	else
    		newReponse = new Reponse(value, this.reponses.substring(index, this.reponses.length()));    	
    	this.reponseList.add(newReponse);
    	return 1;
    }
        
    public ArrayList<Reponse>	parser() {
    	int	index = 0;
    	for (char c : this.reponses.toCharArray()) {
    		if (c == '+') {
    	    	index += createReponse(index + 1, true);
    		} else if (c == '-') {
    			index += createReponse(index + 1, false);
    		} else {
    			index++;
    		}
    	}
    	return this.reponseList;
    }
    
}
