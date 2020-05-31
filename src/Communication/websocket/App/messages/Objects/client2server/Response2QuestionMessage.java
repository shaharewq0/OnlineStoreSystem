package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class Response2QuestionMessage extends Client2ServerMessage {

    private final String answer;
    private final int qustionID;

    public Response2QuestionMessage(long id, String answer, int qustionID) {
        super(Opcodes.Response2Qustion, id);
        this.answer = answer;
        this.qustionID = qustionID;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getAnswer() {
        return answer;
    }

    public int getQustionID() {
        return qustionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response2QuestionMessage that = (Response2QuestionMessage) o;
        return getQustionID() == that.getQustionID() &&
                getAnswer().equals(that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnswer(), getQustionID());
    }

    @Override
    public String toString() {
        return "Response2QuestionMessage{" +
                "answer='" + answer + '\'' +
                ", qustionID=" + qustionID +
                '}';
    }
}
