package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import java.util.Objects;

public class StringResponse extends Server2ClientMessage {

    private final String msg;

    public StringResponse(String msg) {
        super((byte)-33, 0);
        this.msg = msg;
    }

    public StringResponse(byte opcode, long replayRorID, String msg) {
        super(opcode, replayRorID);
        this.msg = msg;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "StringResponse{" +
                "msg='" + msg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringResponse that = (StringResponse) o;
        return getMsg().equals(that.getMsg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMsg());
    }
}
