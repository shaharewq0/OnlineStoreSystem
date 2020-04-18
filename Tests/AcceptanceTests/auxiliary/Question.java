package AcceptanceTests.auxiliary;

public class Question {
    private String question;
    private String answer;

    public Question(String question) {
        this.question = question;
        this.answer = null;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
