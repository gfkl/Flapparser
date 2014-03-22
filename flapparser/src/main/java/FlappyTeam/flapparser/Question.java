package FlappyTeam.flapparser;

import java.util.ArrayList;

public class Question {
	String question;
	TypeQuestion type;
	ArrayList<Reponse> listeRep;

	public Question(String question, TypeQuestion type) {
		super();
		this.question = question;
		this.type = type;
		this.listeRep = new ArrayList<Reponse>();
	}
	
	public Question() {
		super();
		this.question = null;
		this.type = null;
		this.listeRep = new ArrayList<Reponse>();
	}

	@Override
	public String toString() {
		return "Question [question=" + question + "\n type=" + type
				+ "\n listeRep=" + listeRep + "]";
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public TypeQuestion getType() {
		return type;
	}

	public void setType(TypeQuestion type) {
		this.type = type;
	}

	public ArrayList<Reponse> getListeRep() {
		return listeRep;
	}

	public void setListeRep(ArrayList<Reponse> listeRep) {
		this.listeRep = listeRep;
	}
	
	
}
