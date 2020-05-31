package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import Domain.info.Question;

import java.util.List;
import java.util.Objects;

public class QustionListResponse extends Server2ClientMessage {

    private List<Question> questions;

    public QustionListResponse(byte opcode, long replayForID, List<Question> questions) {
        super(opcode, replayForID);

        this.questions = questions;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QustionListResponse that = (QustionListResponse) o;
        return getQuestions().equals(that.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestions());
    }

    @Override
    public String toString() {
        return "QustionListResponse{" +
                "questions=" + questions +
                '}';
    }
}
