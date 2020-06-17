package Domain.info;

import java.util.LinkedList;
import java.util.List;

public class Question {

	private String question = "";
	private int id = -1;
	private List<String> Answers = new LinkedList<String>();

	public Question(String q) {
		question = (q);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswers(List<String> answers) {
		Answers = answers;
	}

	public String getQuestion() {
		return question;
	}

	public int getId() {
		return id;
	}

	public List<String> getAnswers() {
		return Answers;
	}

	public void addAnswers(String answer) {
		Answers.add(answer);
	}

}
