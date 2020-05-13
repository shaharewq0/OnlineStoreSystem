package Domain.info;

import java.util.LinkedList;
import java.util.List;

public class Question {

	private String question = "";
	private int id = -1;
	private List<String> Ansewers = new LinkedList<String>();

	public Question(int id, String q) {
		this.id = (id);
		question = (q);
	}

	public String getQuestion() {
		return question;
	}

	public int getId() {
		return id;
	}

	public List<String> getAnsewers() {
		return Ansewers;
	}

	public void addAnsewers(String ansewer) {
		Ansewers.add(ansewer);
	}

}
